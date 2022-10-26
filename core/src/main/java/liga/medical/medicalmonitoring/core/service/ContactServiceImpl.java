package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.api.service.ContactService;
import liga.medical.medicalmonitoring.core.model.Contact;
import liga.medical.medicalmonitoring.dto.ContactRequest;
import liga.medical.medicalmonitoring.dto.ContactResponse;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.api.mapping.ContactMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final ContactMapper contactMapper;

    @Override
    public Long create(ContactRequest request) {
        log.info("got request: {}", request);
        Contact contact = modelMapper.map(request, Contact.class);
        log.info("converted to entity: {}", contact);
        return contactMapper.save(contact);
    }

    @Override
    public ContactResponse getById(Long id) {
        ContactResponse contact = contactMapper.findById(id);
        if (contact == null) {
            throw new NotFoundException("contact not found by id: " + id);
        }
        return contact;
    }

    @Override
    public List<ContactResponse> getAll() {
        return contactMapper.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        if (getById(id) == null) {
            throw new NotFoundException("contact does not exist with id: " + id);
        }
        contactMapper.deleteById(id);
        return true;
    }
}
