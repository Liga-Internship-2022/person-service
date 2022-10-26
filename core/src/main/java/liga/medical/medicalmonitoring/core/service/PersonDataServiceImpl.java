package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.api.service.PersonDataService;
import liga.medical.medicalmonitoring.dto.PersonDataRequest;
import liga.medical.medicalmonitoring.dto.PersonDataResponse;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.core.model.PersonData;
import liga.medical.medicalmonitoring.api.repository.PersonDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonDataServiceImpl implements PersonDataService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final PersonDataRepository personDataRepository;

    @Override
    public Long create(PersonDataRequest request) {
        log.info("got request: {}", request);
        PersonData personData = modelMapper.map(request, PersonData.class);
        log.info("converted to entity: {}", personData);
        return personDataRepository.save(personData).getId();
    }

    @Override
    public PersonDataResponse getById(Long id) {
        PersonData personData = personDataRepository.findById(id).orElseThrow(
                () -> new NotFoundException("person data not found by id: " + id)
        );
        return modelMapper.map(personData, PersonDataResponse.class);
    }

    @Override
    public List<PersonDataResponse> getAll() {
        return personDataRepository.findAll()
                .stream()
                .map(pd -> modelMapper.map(pd, PersonDataResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (!existsById(id)) {
            throw new NotFoundException("person data does not exist with id: " + id);
        }
        personDataRepository.deleteById(id);
        return true;
    }

    private boolean existsById(Long id) {
        return personDataRepository.existsById(id);
    }
}
