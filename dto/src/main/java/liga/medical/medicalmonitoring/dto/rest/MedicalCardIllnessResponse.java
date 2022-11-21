package liga.medical.medicalmonitoring.dto.rest;

import liga.medical.medicalmonitoring.dto.rest.illness.IllnessResponse;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardIllnessResponse {
    private MedicalCardResponse medicalCard;
    private List<IllnessResponse> illnesses;
}
