package liga.medical.medicalmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private Long countryId;
    private String city;
    private Integer index;
    private String street;
    private String building;
    private String flat;
}
