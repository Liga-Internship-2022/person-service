package liga.medical.medicalmonitoring.dto;

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
