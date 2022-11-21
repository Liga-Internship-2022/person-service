package liga.medical.medicalmonitoring.dto.rest.contact;

import liga.medical.medicalmonitoring.dto.rest.address.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto {
    private ContactResponse contact;
    private List<AddressResponseDto> addresses;
}
