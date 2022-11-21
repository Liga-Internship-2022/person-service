package liga.medical.medicalmonitoring.dto.rest.medical_card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardRequest {
    private String clientStatus;
    private String medStatus;
    private String comment;
}
