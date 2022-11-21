package liga.medical.medicalmonitoring.dto.rest.medical_card;

import liga.medical.medicalmonitoring.dto.rest.illness.IllnessResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardResponseDto {
    private MedicalCardResponse medicalCard;
    private List<IllnessResponseDto> illnesses;
}
