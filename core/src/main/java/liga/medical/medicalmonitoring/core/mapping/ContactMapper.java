package liga.medical.medicalmonitoring.core.mapping;

import liga.medical.medicalmonitoring.core.model.Contact;
import liga.medical.medicalmonitoring.dto.rest.contact.ContactResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContactMapper {

    long save(Contact contact);

    ContactResponse findById(Long id);

    List<ContactResponse> findAll();

    void deleteById(Long id);
}
