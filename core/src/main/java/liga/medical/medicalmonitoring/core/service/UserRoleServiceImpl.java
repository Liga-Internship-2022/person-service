package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.exception.AlreadyExistException;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.core.mapping.RoleMapper;
import liga.medical.medicalmonitoring.core.mapping.UserMapper;
import liga.medical.medicalmonitoring.core.mapping.UserRoleMapper;
import liga.medical.medicalmonitoring.core.model.Role;
import liga.medical.medicalmonitoring.core.model.User;
import liga.medical.medicalmonitoring.core.service.api.UserRoleService;
import liga.medical.medicalmonitoring.dto.auth.RoleDto;
import liga.medical.medicalmonitoring.dto.auth.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    private final ModelMapper modelMapper;

    @Override
    public boolean addRole(long userId, long roleId) {
        User user = userMapper.findById(userId);
        Role role = roleMapper.findById(roleId);

        validateUserRolesExistOrElseThrow(userId, roleId, user, role);

        user.getRoles().add(role);
        role.getUsers().add(user);
        userRoleMapper.addRole(userId, roleId);
        return true;
    }

    @Override
    public void deleteRole(long userId, long roleId) {
        User user = userMapper.findById(userId);
        Role role = roleMapper.findById(roleId);

        validateUserRolesExistOrElseThrow(userId, roleId, user, role);

        user.getRoles().remove(role);
        role.getUsers().remove(user);
        userRoleMapper.deleteRole(userId, roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> getRoles(long userId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new NotFoundException("user not found by id: " + userId);
        }
        return user.getRoles().stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getUsers(long roleId) {
        Role role = roleMapper.findById(roleId);
        if (role == null) {
            throw new NotFoundException("role not found by id: " + roleId);
        }
        return role.getUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    private void validateUserRolesExistOrElseThrow(long userId, long roleId, User user, Role role) {
        if (user == null) {
            throw new NotFoundException("user not found by id: " + userId);
        }
        if (role == null) {
            throw new NotFoundException("role not found by id: " + roleId);
        }
        if (user.getRoles().contains(role) || role.getUsers().contains(user)) {
            throw new AlreadyExistException("user already has this role: " + role.getName());
        }
    }
}
