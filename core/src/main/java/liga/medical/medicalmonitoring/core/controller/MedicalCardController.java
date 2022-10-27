package liga.medical.medicalmonitoring.core.controller;

import liga.medical.medicalmonitoring.core.facade.MedicalCardFacade;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessRequest;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessResponse;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessSoftResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medical-cards")
@RequiredArgsConstructor
public class MedicalCardController {

    private final MedicalCardFacade medicalCardFacade;

    @PostMapping
    public MedicalCardIllnessSoftResponse createMedicalCardWithIllnesses(@RequestBody MedicalCardIllnessRequest request) {
        return medicalCardFacade.createMedicalCardWithIllnesses(request);
    }

    @GetMapping("/{id}")
    public MedicalCardIllnessResponse getMedicalCardById(@PathVariable Long id) {
        return medicalCardFacade.getById(id);
    }

    @GetMapping
    public List<MedicalCardIllnessResponse> getAllMedicalCards() {
        return medicalCardFacade.getAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleteMedicalCardWithIllnessesById(@PathVariable Long id) {
        return medicalCardFacade.deleteMedicalCardWithIllnessesByIdWith(id);
    }
}
