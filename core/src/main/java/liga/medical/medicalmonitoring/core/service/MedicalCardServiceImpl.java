package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.api.service.MedicalCardService;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardRequest;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardResponse;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.core.model.MedicalCard;
import liga.medical.medicalmonitoring.core.repository.MedicalCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicalCardServiceImpl implements MedicalCardService {

    private final MedicalCardRepository medicalCardRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long create(MedicalCardRequest request) {
        log.info("got request: {}", request);
        MedicalCard medicalCard = modelMapper.map(request, MedicalCard.class);
        medicalCard.setRegistryDt(new Date(System.currentTimeMillis()));
        log.info("converted to entity: {}", medicalCard);
        return medicalCardRepository.save(medicalCard).getId();
    }

    @Override
    public MedicalCardResponse getById(Long id) {
        MedicalCard medicalCard = medicalCardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("medical card not found by id: " + id));
        return modelMapper.map(medicalCard, MedicalCardResponse.class);
    }

    @Override
    public List<MedicalCardResponse> getAll() {
        return medicalCardRepository.findAll()
                .stream()
                .map(mc -> modelMapper.map(mc, MedicalCardResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (!existsById(id)) {
            throw new NotFoundException("medical card does not exist with id: " + id);
        }
        medicalCardRepository.deleteById(id);
        return true;
    }

    private boolean existsById(Long id) {
        return medicalCardRepository.existsById(id);
    }
}
