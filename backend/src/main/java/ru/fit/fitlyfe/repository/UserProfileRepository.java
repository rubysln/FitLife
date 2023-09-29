package ru.fit.fitlyfe.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.fit.fitlyfe.models.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	UserDetails findUserProfileByUsername(String username);
}
