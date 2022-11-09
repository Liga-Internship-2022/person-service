package liga.medical.medicalmonitoring.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liga.medical.medicalmonitoring.core.aop.annotations.DbLog;
import liga.medical.medicalmonitoring.core.facade.PersonDataFacade;
import liga.medical.medicalmonitoring.dto.PersonDataRequest;
import liga.medical.medicalmonitoring.dto.PersonDataResponse;
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
@RequestMapping("/person-data")
@RequiredArgsConstructor
@Api(value = "API для работы с персональными данными пользователей")
public class PersonDataController {

    private final PersonDataFacade personDataFacade;

    @DbLog
    @PostMapping
    @ApiOperation(value = "Создание персональных данных")
    public PersonDataResponse createPersonData(@RequestBody PersonDataRequest request) {
        return personDataFacade.create(request);
    }

    @DbLog
    @GetMapping("/{id}")
    @ApiOperation(value = "Получение персональных данных по id")
    public PersonDataResponse getPersonDataById(@PathVariable Long id) {
        return personDataFacade.getById(id);
    }

    @DbLog
    @GetMapping
    @ApiOperation(value = "Получение всех персональных данных")
    public List<PersonDataResponse> getAllPersonData() {
        return personDataFacade.getAll();
    }

    @DbLog
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаление персональных данных по id")
    public boolean deletePersonDataById(@PathVariable Long id) {
        return personDataFacade.deleteById(id);
    }
}
