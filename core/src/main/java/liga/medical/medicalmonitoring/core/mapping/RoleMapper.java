package liga.medical.medicalmonitoring.core.mapping;

import liga.medical.medicalmonitoring.core.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    void save(Role role);

    Role findById(Long id);

    Role findByName(String name);

    List<Role> findAll();

    void deleteById(Long id);
}
