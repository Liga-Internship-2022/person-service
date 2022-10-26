package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.api.service.IllnessService;
import liga.medical.medicalmonitoring.dto.IllnessRequest;
import liga.medical.medicalmonitoring.dto.IllnessResponse;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.core.model.Illness;
import liga.medical.medicalmonitoring.api.repository.IllnessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IllnessServiceImpl implements IllnessService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final IllnessRepository illnessRepository;

    @Override
    public Long create(IllnessRequest request) {
        log.info("got request: {}", request);
        Illness illness = modelMapper.map(request, Illness.class);
        log.info("converted to entity: {}", illness);
        return illnessRepository.save(illness).getId();
    }

    @Override
    public IllnessResponse getById(Long id) {
        Illness illness = illnessRepository.findById(id).orElseThrow(
                () -> new NotFoundException("illness not found by id: " + id)
        );
        return modelMapper.map(illness, IllnessResponse.class);
    }

    @Override
    public List<IllnessResponse> getAll() {
        return illnessRepository.findAll()
                .stream()
                .map(illness -> modelMapper.map(illness, IllnessResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (!existsById(id)) {
            throw new NotFoundException("illness does not exist with id: " + id);
        }
        illnessRepository.deleteById(id);
        return true;
    }

    private boolean existsById(Long id) {
        return illnessRepository.existsById(id);
    }
}
