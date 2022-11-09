package liga.medical.medicalmonitoring.dto;

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
    private Long id;
    private String phoneNumber;
    private String email;
    private String profileLink;
    private List<AddressResponseDto> addresses;
}
