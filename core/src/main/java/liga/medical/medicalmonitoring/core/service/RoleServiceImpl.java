package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.exception.AlreadyExistException;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.core.mapping.RoleMapper;
import liga.medical.medicalmonitoring.core.model.Role;
import liga.medical.medicalmonitoring.core.service.api.RoleService;
import liga.medical.medicalmonitoring.dto.auth.RoleDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final ModelMapper modelMapper;

    @Override
    public void saveByName(String name) {
        if (roleMapper.findByName(name) != null) {
            throw new AlreadyExistException("role already exists with name: " + name);
        }
        Role role = new Role();
        role.setName(name);
        roleMapper.save(role);
    }

    @Override
    public RoleDto findByName(String name) {
        Role role = roleMapper.findByName(name);
        if (role == null) {
            throw new NotFoundException("role not found by name: " + name);
        }
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleMapper.findAll().stream()
                .filter(Objects::nonNull)
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (roleMapper.findById(id) == null) {
            throw new NotFoundException("role not found by id: " + id);
        }
        roleMapper.deleteById(id);
        return true;
    }
}
