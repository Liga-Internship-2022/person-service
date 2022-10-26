package liga.medical.medicalmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDataResponse {
    private Long id;
    private String lastName;
    private String firstName;
    private java.sql.Date birthDt;
    private Long age;
    private String sex;
}
