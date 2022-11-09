package liga.medical.medicalmonitoring.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liga.medical.medicalmonitoring.core.aop.annotations.DbLog;
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
@Api(value = "API для работы с медицинскими картами")
public class MedicalCardController {

    private final MedicalCardFacade medicalCardFacade;

    @DbLog
    @PostMapping
    @ApiOperation(value = "Создание медицинской карты")
    public MedicalCardIllnessSoftResponse createMedicalCardWithIllnesses(@RequestBody MedicalCardIllnessRequest request) {
        return medicalCardFacade.createMedicalCardWithIllnesses(request);
    }

    @DbLog
    @GetMapping("/{id}")
    @ApiOperation(value = "Получение медицинской карты по id")
    public MedicalCardIllnessResponse getMedicalCardById(@PathVariable Long id) {
        return medicalCardFacade.getById(id);
    }

    @DbLog
    @GetMapping
    @ApiOperation(value = "Получение всех медицинских карт")
    public List<MedicalCardIllnessResponse> getAllMedicalCards() {
        return medicalCardFacade.getAll();
    }

    @DbLog
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаление медицинской карты по id")
    public boolean deleteMedicalCardWithIllnessesById(@PathVariable Long id) {
        return medicalCardFacade.deleteMedicalCardWithIllnessesByIdWith(id);
    }
}
