package liga.medical.medicalmonitoring.core.service.api;

import liga.medical.medicalmonitoring.dto.UserRequest;
import liga.medical.medicalmonitoring.dto.auth.UserDto;

import java.util.List;

public interface UserService {

    void save(UserRequest userRequest);

    UserDto findByUsername(String username);

    List<UserDto> getAll();

    boolean deleteById(Long id);
}
