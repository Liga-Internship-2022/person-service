package liga.medical.medicalmonitoring.core.mapping;

import liga.medical.medicalmonitoring.core.dto.ContactResponse;
import liga.medical.medicalmonitoring.core.model.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContactMapper {

    long save(Contact contact);

    ContactResponse findById(@Param("id") Long id);

    List<ContactResponse> findAll();

    void deleteById(@Param("id") Long id);
}
