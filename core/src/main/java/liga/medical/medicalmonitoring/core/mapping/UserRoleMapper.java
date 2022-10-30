package liga.medical.medicalmonitoring.core.mapping;

import liga.medical.medicalmonitoring.core.model.Role;
import liga.medical.medicalmonitoring.core.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    int addRole(@Param("userId") long userId, @Param("roleId") long roleId);

    int deleteRole(@Param("userId") long userId, @Param("roleId") long roleId);

    List<Role> getRoles(long userId);

    List<User> getUsers(long roleId);
}
