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
public class MedicalCardRequestDto {
    private String clientStatus;
    private String medStatus;
    private String comment;
    private List<IllnessRequest> illnesses;
}
