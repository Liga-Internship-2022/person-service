package liga.medical.medicalmonitoring.core.controller;

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
public class PersonDataController {

    private final PersonDataFacade personDataFacade;

    @PostMapping
    public PersonDataResponse createPersonData(@RequestBody PersonDataRequest request) {
        return personDataFacade.create(request);
    }

    @GetMapping("/{id}")
    public PersonDataResponse getPersonDataById(@PathVariable Long id) {
        return personDataFacade.getById(id);
    }

    @GetMapping
    public List<PersonDataResponse> getAllPersonData() {
        return personDataFacade.getAll();
    }

    @DeleteMapping("/{id}")
    public boolean deletePersonDataById(@PathVariable Long id) {
        return personDataFacade.deleteById(id);
    }
}
