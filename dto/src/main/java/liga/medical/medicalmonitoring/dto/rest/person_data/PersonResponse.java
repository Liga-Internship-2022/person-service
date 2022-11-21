package liga.medical.medicalmonitoring.dto.rest.person_data;

import liga.medical.medicalmonitoring.dto.rest.contact.ContactResponseDto;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardResponseDto;
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
