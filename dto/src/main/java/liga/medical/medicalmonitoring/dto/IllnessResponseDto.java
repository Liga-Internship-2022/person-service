package liga.medical.medicalmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IllnessResponseDto {
    private Long id;
    private Long typeId;
    private String heaviness;
    private java.sql.Timestamp appearanceDttm;
    private java.sql.Date recoveryDt;
}
