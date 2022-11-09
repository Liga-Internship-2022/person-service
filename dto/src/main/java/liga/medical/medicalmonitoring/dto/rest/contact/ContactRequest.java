package liga.medical.medicalmonitoring.dto.rest.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {
    private String phoneNumber;
    private String email;
    private String profileLink;
}
