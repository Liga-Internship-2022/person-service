package liga.medical.medicalmonitoring.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardIllnessSoftResponse {
    private Long medicalCardId;
    private List<Long> illnessesIds;
}
