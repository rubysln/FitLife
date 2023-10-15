package ru.fit.fitlyfe.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.services.JwtService;

@Service
public class JwtServiceImpl implements JwtService {

	@Value("${secret.key}")
	private String SECRET_KEY;
	@Override
	public String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}

	@Override
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
		final Claims claims = getAllClaims(token);
		return claimsTFunction.apply(claims);
	}

	@Override
	public String generateToken(UserDetails userProfile){
		return generateToken(new HashMap<>(), userProfile);
	}
	@Override
	public String generateToken(
			Map<String, Object> extraClaims,
			UserDetails userProfile
	) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userProfile.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isTokenValid(String token, UserDetails userProfile){
		if(userProfile == null) return false;
		final String username = getUsername(token);
		return (username.equals(userProfile.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	private Claims getAllClaims(String token){
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInKey() {
		 byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		 return Keys.hmacShaKeyFor(keyBytes);
	}
}
