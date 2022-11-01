package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.exception.AlreadyExistException;
import liga.medical.medicalmonitoring.core.exception.BadLoginDataException;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.core.mapping.UserMapper;
import liga.medical.medicalmonitoring.core.mapping.UserRoleMapper;
import liga.medical.medicalmonitoring.core.model.User;
import liga.medical.medicalmonitoring.core.service.api.UserService;
import liga.medical.medicalmonitoring.dto.UserRequest;
import liga.medical.medicalmonitoring.dto.auth.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public void save(UserRequest userRequest) {
        if (userMapper.findByUsername(userRequest.getUsername()) != null) {
            throw new AlreadyExistException("user already exists with username: " + userRequest.getUsername());
        }
        if (userRequest.getUsername().length() < 8 || userRequest.getUsername().length() > 32 ||
                userRequest.getPassword().length() < 8 || userRequest.getPassword().length() > 32
        ) {
            throw new BadLoginDataException("username and password length should be between 8 and 32 characters");
        }
        String password = bCryptPasswordEncoder.encode(userRequest.getPassword());
        User user = new User(userRequest.getUsername(), password);
        long userId = userMapper.save(user);
        userRoleMapper.addRole(userId, 1);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("user not found by username: " + username);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.findAll().stream()
                .filter(Objects::nonNull)
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (userMapper.findById(id) == null) {
            throw new NotFoundException("user not found by id: " + id);
        }
        userMapper.deleteById(id);
        return true;
    }
}
