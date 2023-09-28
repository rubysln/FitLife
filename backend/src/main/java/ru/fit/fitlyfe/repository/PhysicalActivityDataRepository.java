package ru.fit.fitlyfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fit.fitlyfe.models.PhysicalActivityData;

@Repository
public interface PhysicalActivityDataRepository extends JpaRepository<PhysicalActivityData, Long> {
}
