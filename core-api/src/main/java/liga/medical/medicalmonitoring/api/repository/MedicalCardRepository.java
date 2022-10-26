package liga.medical.medicalmonitoring.api.repository;

import liga.medical.medicalmonitoring.core.model.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {
}
