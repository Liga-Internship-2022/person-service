package liga.medical.medicalmonitoring.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginAttemptDto {
    private java.sql.Timestamp dttm;
    private String username;
    private String comment;
}
