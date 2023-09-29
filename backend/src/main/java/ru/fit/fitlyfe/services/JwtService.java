package ru.fit.fitlyfe.services;

import io.jsonwebtoken.Claims;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import ru.fit.fitlyfe.models.UserProfile;

public interface JwtService {
	public String getUsername(String jwt);
	public <T> T getClaim(String token, Function<Claims, T> claimsTFunction);
	public String generateToken(UserDetails userProfile);
	public String generateToken(Map<String, Object> extraClaims, UserDetails userProfile);
	public boolean isTokenValid(String token, UserDetails userProfile);
}
