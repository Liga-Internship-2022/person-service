package liga.medical.medicalmonitoring.dto.rest;

import liga.medical.medicalmonitoring.dto.rest.illness.IllnessRequest;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardIllnessRequest {
    private MedicalCardRequest medicalCard;
    private List<IllnessRequest> illnesses;
}
