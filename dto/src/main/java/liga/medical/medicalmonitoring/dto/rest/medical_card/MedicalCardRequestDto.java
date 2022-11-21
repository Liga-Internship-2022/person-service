package liga.medical.medicalmonitoring.dto.rest.medical_card;

import liga.medical.medicalmonitoring.dto.rest.illness.IllnessRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardRequestDto {
    private MedicalCardRequest medicalCard;
    private List<IllnessRequest> illnesses;
}
