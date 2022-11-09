package liga.medical.medicalmonitoring.dto.rest;

import liga.medical.medicalmonitoring.dto.rest.address.AddressResponse;
import liga.medical.medicalmonitoring.dto.rest.contact.ContactResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactAddressResponse {
    private ContactResponse contact;
    private List<AddressResponse> addresses;
}
