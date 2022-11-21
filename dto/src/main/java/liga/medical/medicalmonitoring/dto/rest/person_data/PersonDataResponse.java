package liga.medical.medicalmonitoring.dto.rest.person_data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDataResponse {
    private Long id;
    private String lastName;
    private String firstName;
    private java.sql.Date birthDt;
    private Long age;
    private String sex;
    private Long medicalCardId;
    private Long contactId;
    private Long parentId;
}
