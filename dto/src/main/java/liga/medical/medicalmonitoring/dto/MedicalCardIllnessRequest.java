package liga.medical.medicalmonitoring.dto;

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
