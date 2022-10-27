package liga.medical.medicalmonitoring.core.facade;

import liga.medical.medicalmonitoring.api.service.IllnessService;
import liga.medical.medicalmonitoring.api.service.MedicalCardService;
import liga.medical.medicalmonitoring.dto.IllnessRequest;
import liga.medical.medicalmonitoring.dto.IllnessResponse;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessRequest;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessResponse;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessSoftResponse;
import liga.medical.medicalmonitoring.dto.MedicalCardRequest;
import liga.medical.medicalmonitoring.dto.MedicalCardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class MedicalCardFacade {

    private final MedicalCardService medicalCardService;
    private final IllnessService illnessService;

    public MedicalCardIllnessSoftResponse createMedicalCardWithIllnesses(MedicalCardIllnessRequest request) {
        log.info("got request: {}", request);
        MedicalCardRequest medicalCard = request.getMedicalCard();
        Long mcId = medicalCardService.create(medicalCard);
        List<IllnessRequest> illnesses = request.getIllnesses();
        List<Long> createdIllnessIds = new ArrayList<>();
        illnesses.forEach(illness -> {
                    Long illnessId = illnessService.create(illness, mcId);
                    createdIllnessIds.add(illnessId);
                }
        );
        return MedicalCardIllnessSoftResponse.builder()
                .medicalCardId(mcId)
                .illnessesIds(createdIllnessIds)
                .build();
    }

    public MedicalCardIllnessResponse getById(Long id) {
        MedicalCardResponse medicalCard = medicalCardService.getById(id);
        List<IllnessResponse> allIllnesses = illnessService.getAll();
        List<IllnessResponse> mcIllnesses = getMedicalCardIllnesses(id, allIllnesses);
        return MedicalCardIllnessResponse.builder()
                .medicalCard(medicalCard)
                .illnesses(mcIllnesses)
                .build();
    }

    public List<MedicalCardIllnessResponse> getAll() {
        List<MedicalCardIllnessResponse> result = new ArrayList<>();
        List<MedicalCardResponse> medicalCards = medicalCardService.getAll();
        List<IllnessResponse> allIllnesses = illnessService.getAll();
        medicalCards.forEach(mc -> {
            List<IllnessResponse> mcIllnesses = getMedicalCardIllnesses(mc.getId(), allIllnesses);
            result.add(MedicalCardIllnessResponse.builder()
                    .medicalCard(mc)
                    .illnesses(mcIllnesses)
                    .build());
        });
        return result;
    }

    public boolean deleteMedicalCardWithIllnessesByIdWith(Long id) {
        MedicalCardResponse medicalCard = medicalCardService.getById(id);
        List<IllnessResponse> allIllnesses = illnessService.getAll();
        List<IllnessResponse> medicalCardIllnesses = getMedicalCardIllnesses(id, allIllnesses);
        medicalCardIllnesses.forEach(illness -> illnessService.deleteById(illness.getId()));
        medicalCardService.deleteById(medicalCard.getId());
        return true;
    }

    private List<IllnessResponse> getMedicalCardIllnesses(Long id, List<IllnessResponse> illnesses) {
        return illnesses.stream()
                .filter(illness -> illness.getMedicalCardId().equals(id))
                .collect(Collectors.toList());
    }
}
