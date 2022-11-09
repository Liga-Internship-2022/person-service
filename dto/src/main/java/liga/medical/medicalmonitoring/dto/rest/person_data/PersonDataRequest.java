package liga.medical.medicalmonitoring.dto.rest.person_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDataRequest {
    private String lastName;
    private String firstName;
    private java.sql.Date birthDt;
    private Long age;
    private String sex;
    private Long medicalCardId;
    private Long contactId;
    private Long parentId;
}
