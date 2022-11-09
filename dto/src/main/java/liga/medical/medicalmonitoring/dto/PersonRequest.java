package liga.medical.medicalmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private PersonDataRequestDto personData;
    private MedicalCardRequestDto medicalCard;
    private ContactRequestDto contact;
}
