package liga.medical.medicalmonitoring.api.service;

import liga.medical.medicalmonitoring.dto.PersonDataRequest;
import liga.medical.medicalmonitoring.dto.PersonDataResponse;

import java.util.List;

public interface PersonDataService {

    Long create(PersonDataRequest request);

    PersonDataResponse getById(Long id);

    List<PersonDataResponse> getAll();

    boolean deleteById(Long id);
}
