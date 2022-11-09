package liga.medical.medicalmonitoring.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liga.medical.medicalmonitoring.core.facade.PersonFacade;
import liga.medical.medicalmonitoring.dto.PersonRequest;
import liga.medical.medicalmonitoring.dto.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
@Api(value = "Центральный API для работы с пользователями")
public class PersonController {

    private final PersonFacade personFacade;

    @PostMapping
    @ApiOperation(value = "Создание пользователя. Возвращает его id")
    public Long createPerson(@RequestBody PersonRequest request) {
        return personFacade.createPerson(request);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получение пользователя по id")
    public PersonResponse getPersonById(@PathVariable Long id) {
        return personFacade.getPersonById(id);
    }
}
