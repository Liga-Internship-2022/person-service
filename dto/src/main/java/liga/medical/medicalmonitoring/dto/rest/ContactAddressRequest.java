package liga.medical.medicalmonitoring.dto.rest;

import liga.medical.medicalmonitoring.dto.rest.address.AddressRequest;
import liga.medical.medicalmonitoring.dto.rest.contact.ContactRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactAddressRequest {
    private ContactRequest contact;
    private List<AddressRequest> addresses;
}
