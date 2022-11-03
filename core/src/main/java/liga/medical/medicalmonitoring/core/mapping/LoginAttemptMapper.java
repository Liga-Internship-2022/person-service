package liga.medical.medicalmonitoring.core.mapping;

import liga.medical.medicalmonitoring.core.model.LoginAttempt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginAttemptMapper {

    void save(LoginAttempt loginAttempt);
}
