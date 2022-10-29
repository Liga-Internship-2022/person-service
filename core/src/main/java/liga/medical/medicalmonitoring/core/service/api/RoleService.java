package liga.medical.medicalmonitoring.core.service.api;

import liga.medical.medicalmonitoring.dto.auth.RoleDto;

import java.util.List;

public interface RoleService {

    void saveByName(String name);

    RoleDto findByName(String name);

    List<RoleDto> getAll();

    boolean deleteById(Long id);
}
