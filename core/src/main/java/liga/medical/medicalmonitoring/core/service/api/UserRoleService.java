package liga.medical.medicalmonitoring.core.service.api;

import liga.medical.medicalmonitoring.dto.auth.RoleDto;
import liga.medical.medicalmonitoring.dto.auth.UserDto;

import java.util.List;

public interface UserRoleService {

    boolean addRole(long userId, long roleId);

    void deleteRole(long userId, long roleId);

    List<RoleDto> getRoles(long userId);

    List<UserDto> getUsers(long roleId);
}
