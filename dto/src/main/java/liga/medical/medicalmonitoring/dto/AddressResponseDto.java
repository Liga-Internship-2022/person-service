package liga.medical.medicalmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {
    private Long id;
    private Long countryId;
    private String city;
    private Integer index;
    private String street;
    private String building;
    private String flat;
}
