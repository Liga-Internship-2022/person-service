package liga.medical.medicalmonitoring.api.repository;

import liga.medical.medicalmonitoring.core.model.Illness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessRepository extends JpaRepository<Illness, Long> {
}
