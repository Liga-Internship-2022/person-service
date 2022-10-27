package liga.medical.medicalmonitoring.core.facade;

import liga.medical.medicalmonitoring.api.service.AddressService;
import liga.medical.medicalmonitoring.api.service.ContactService;
import liga.medical.medicalmonitoring.dto.AddressRequest;
import liga.medical.medicalmonitoring.dto.AddressResponse;
import liga.medical.medicalmonitoring.dto.ContactAddressRequest;
import liga.medical.medicalmonitoring.dto.ContactAddressResponse;
import liga.medical.medicalmonitoring.dto.ContactAddressSoftResponse;
import liga.medical.medicalmonitoring.dto.ContactRequest;
import liga.medical.medicalmonitoring.dto.ContactResponse;
import liga.medical.medicalmonitoring.dto.PersonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContactFacade {

    private final ContactService contactService;
    private final AddressService addressService;
    private final PersonDataFacade personDataFacade;

    public ContactAddressSoftResponse createContactWithAddresses(ContactAddressRequest request) {
        log.info("got request: {}", request);
        ContactRequest contact = request.getContact();
        Long contactId = contactService.create(contact);
        List<AddressRequest> addresses = request.getAddresses();
        List<Long> createdAddressIds = new ArrayList<>();
        addresses.forEach(address -> {
                    Long addressId = addressService.create(address, contactId);
                    createdAddressIds.add(addressId);
                }
        );
        return ContactAddressSoftResponse.builder()
                .contactId(contactId)
                .addressesIds(createdAddressIds)
                .build();
    }

    public ContactAddressResponse getById(Long id) {
        ContactResponse contact = contactService.getById(id);
        List<AddressResponse> allAddresses = addressService.getAll();
        List<AddressResponse> contactAddresses = getContactAddresses(id, allAddresses);
        return ContactAddressResponse.builder()
                .contact(contact)
                .addresses(contactAddresses)
                .build();
    }

    public List<ContactAddressResponse> getAll() {
        List<ContactAddressResponse> result = new ArrayList<>();
        List<ContactResponse> contacts = contactService.getAll();
        List<AddressResponse> allAddresses = addressService.getAll();
        contacts.forEach(contact -> {
            List<AddressResponse> contactAddresses = getContactAddresses(contact.getId(), allAddresses);
            result.add(ContactAddressResponse.builder()
                    .contact(contact)
                    .addresses(contactAddresses)
                    .build());
        });
        return result;
    }

    public boolean deleteContactWithAddressesById(Long contactId) {
        ContactResponse contact = contactService.getById(contactId);
        deleteRelatedAddresses(contactId);
        deleteRelatedPersonData(contactId);
        contactService.deleteById(contact.getId());
        return true;
    }

    private void deleteRelatedAddresses(Long contactId) {
        List<AddressResponse> relatedAddresses = addressService.getAll().stream()
                .filter(address -> address.getContactId().equals(contactId))
                .collect(Collectors.toList());
        relatedAddresses.forEach(address -> addressService.deleteById(address.getId()));
    }

    private void deleteRelatedPersonData(Long contactId) {
        List<PersonDataResponse> relatedPersonData = personDataFacade.getAll().stream()
                .filter(pd -> pd.getContactId().equals(contactId))
                .collect(Collectors.toList());
        relatedPersonData.forEach(pd -> personDataFacade.deleteById(pd.getId()));
    }

    private List<AddressResponse> getContactAddresses(Long contactId, List<AddressResponse> allAddresses) {
        return allAddresses.stream()
                .filter(address -> address.getContactId().equals(contactId))
                .collect(Collectors.toList());
    }
}
