package liga.medical.medicalmonitoring.dto.rest.contact;

import liga.medical.medicalmonitoring.dto.rest.address.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDto {
    private ContactRequest contact;
    private List<AddressRequest> addresses;
}
