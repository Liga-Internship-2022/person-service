package liga.medical.medicalmonitoring.core.repository;

import liga.medical.medicalmonitoring.core.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
