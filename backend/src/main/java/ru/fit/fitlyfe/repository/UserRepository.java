package ru.fit.fitlyfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.fit.fitlyfe.models.UserProfile;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {

}
