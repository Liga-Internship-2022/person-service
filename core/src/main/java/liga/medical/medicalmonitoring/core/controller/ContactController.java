package liga.medical.medicalmonitoring.core.controller;

import liga.medical.medicalmonitoring.core.facade.ContactFacade;
import liga.medical.medicalmonitoring.dto.ContactAddressRequest;
import liga.medical.medicalmonitoring.dto.ContactAddressResponse;
import liga.medical.medicalmonitoring.dto.ContactAddressSoftResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactFacade contactFacade;

    @PostMapping
    public ContactAddressSoftResponse createContact(@RequestBody ContactAddressRequest request) {
        return contactFacade.createContactWithAddresses(request);
    }

    @GetMapping("/{id}")
    public ContactAddressResponse getContactById(@PathVariable Long id) {
        return contactFacade.getById(id);
    }

    @GetMapping
    public List<ContactAddressResponse> getAllContacts() {
        return contactFacade.getAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleteContactById(@PathVariable Long id) {
        return contactFacade.deleteContactWithAddressesById(id);
    }
}
