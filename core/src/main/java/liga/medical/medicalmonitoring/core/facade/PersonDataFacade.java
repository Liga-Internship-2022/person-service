package liga.medical.medicalmonitoring.core.facade;

import liga.medical.medicalmonitoring.api.service.ContactService;
import liga.medical.medicalmonitoring.api.service.MedicalCardService;
import liga.medical.medicalmonitoring.api.service.PersonDataService;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.dto.PersonDataRequest;
import liga.medical.medicalmonitoring.dto.PersonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonDataFacade {

    private final PersonDataService personDataService;
    private final MedicalCardService medicalCardService;
    private final ContactService contactService;

    private final ModelMapper modelMapper = new ModelMapper();

    public PersonDataResponse create(PersonDataRequest request) {
        Long medicalCardId = request.getMedicalCardId();
        Long contactId = request.getContactId();
        Long parentId = request.getParentId();

        if (medicalCardService.getById(medicalCardId) == null) {
            throw new NotFoundException("medical card does not exist by id: " + medicalCardId);
        }
        if (contactService.getById(contactId) == null) {
            throw new NotFoundException("contact does not exist by id: " + contactId);
        }
        if (parentId != null && personDataService.getById(parentId) == null) {
            throw new NotFoundException("parent person data does not exist by id: " + parentId);
        }

        Long pdId = personDataService.create(request);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PersonDataResponse response = modelMapper.map(request, PersonDataResponse.class);
        response.setId(pdId);
        return response;
    }

    public PersonDataResponse getById(Long id) {
        return personDataService.getById(id);
    }

    public List<PersonDataResponse> getAll() {
        return personDataService.getAll();
    }

    public boolean deleteById(Long id) {
        List<PersonDataResponse> allPersonData = personDataService.getAll();
        List<PersonDataResponse> descendants = allPersonData.stream()
                .filter(pd -> id.equals(pd.getParentId()))
                .collect(Collectors.toList());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        descendants.forEach(child -> {
            child.setParentId(null);
            PersonDataRequest updateRequest = modelMapper.map(child, PersonDataRequest.class);
            personDataService.update(updateRequest, child.getId());
        });
        return personDataService.deleteById(id);
    }
}
