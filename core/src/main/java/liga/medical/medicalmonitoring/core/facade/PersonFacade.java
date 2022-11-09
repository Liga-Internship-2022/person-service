package liga.medical.medicalmonitoring.core.facade;

import liga.medical.medicalmonitoring.dto.PersonRequest;
import liga.medical.medicalmonitoring.dto.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonFacade {

    public Long createPerson(PersonRequest request) {
        return 1L;
    }

    public PersonResponse getPersonById(Long id) {
        return null;
    }
}
