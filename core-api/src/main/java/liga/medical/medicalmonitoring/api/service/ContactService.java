package liga.medical.medicalmonitoring.api.service;

import liga.medical.medicalmonitoring.dto.rest.contact.ContactRequest;
import liga.medical.medicalmonitoring.dto.rest.contact.ContactResponse;

import java.util.List;

public interface ContactService {

    Long create(ContactRequest request);

    ContactResponse getById(Long id);

    List<ContactResponse> getAll();

    boolean deleteById(Long id);
}
