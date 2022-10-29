package liga.medical.medicalmonitoring.core.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    private List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return getName();
    }
}
