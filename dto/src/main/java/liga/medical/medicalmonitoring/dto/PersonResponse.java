package liga.medical.medicalmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private PersonDataResponseDto personData;
    private MedicalCardResponseDto medicalCard;
    private ContactResponseDto contact;
}
