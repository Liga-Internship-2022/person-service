package liga.medical.medicalmonitoring.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import liga.medical.medicalmonitoring.core.aop.annotations.DbLog;
import liga.medical.medicalmonitoring.core.facade.ContactFacade;
import liga.medical.medicalmonitoring.dto.rest.ContactAddressRequest;
import liga.medical.medicalmonitoring.dto.rest.ContactAddressResponse;
import liga.medical.medicalmonitoring.dto.rest.ContactAddressSoftResponse;
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
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Api(value = "API для работы с контактами")
public class ContactController {

    private final ContactFacade contactFacade;

    @DbLog
    @PostMapping
    @ApiOperation(value = "Создание контакта")
    public ContactAddressSoftResponse createContact(@RequestBody ContactAddressRequest request) {
        return contactFacade.createContactWithAddresses(request);
    }

    @DbLog
    @GetMapping("/{id}")
    @ApiOperation(value = "Получение контакта по id")
    public ContactAddressResponse getContactById(@PathVariable Long id) {
        return contactFacade.getById(id);
    }

    @DbLog
    @GetMapping
    @ApiOperation(value = "Получение всех контактов")
    public List<ContactAddressResponse> getAllContacts() {
        return contactFacade.getAll();
    }

    @DbLog
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаление контакта по id")
    public boolean deleteContactById(@PathVariable Long id) {
        return contactFacade.deleteContactWithAddressesById(id);
    }
}
