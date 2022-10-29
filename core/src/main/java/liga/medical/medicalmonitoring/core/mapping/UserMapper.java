package liga.medical.medicalmonitoring.core.mapping;

import liga.medical.medicalmonitoring.core.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(User user);

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    void deleteById(Long id);
}
