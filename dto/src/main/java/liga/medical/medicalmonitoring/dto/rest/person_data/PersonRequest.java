package liga.medical.medicalmonitoring.dto.rest.person_data;

import liga.medical.medicalmonitoring.dto.rest.contact.ContactRequestDto;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardRequestDto;
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
