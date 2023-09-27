package ru.fit.fitlyfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fit.fitlyfe.models.HealthData;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, Long> {

}
