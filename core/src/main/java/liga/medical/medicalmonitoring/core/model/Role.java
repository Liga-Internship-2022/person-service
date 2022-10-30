package liga.medical.medicalmonitoring.core.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Data
public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    private Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return getName();
    }
}
