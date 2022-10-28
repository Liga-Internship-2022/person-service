package liga.medical.medicalmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCardResponse {
    private Long id;
    private String clientStatus;
    private String medStatus;
    private java.sql.Date registryDt;
    private String comment;
}
