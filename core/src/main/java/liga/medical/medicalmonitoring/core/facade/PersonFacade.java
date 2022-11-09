package liga.medical.medicalmonitoring.core.facade;

import liga.medical.medicalmonitoring.dto.ContactAddressRequest;
import liga.medical.medicalmonitoring.dto.ContactAddressResponse;
import liga.medical.medicalmonitoring.dto.ContactAddressSoftResponse;
import liga.medical.medicalmonitoring.dto.ContactRequestDto;
import liga.medical.medicalmonitoring.dto.ContactResponseDto;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessRequest;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessResponse;
import liga.medical.medicalmonitoring.dto.MedicalCardIllnessSoftResponse;
import liga.medical.medicalmonitoring.dto.MedicalCardRequestDto;
import liga.medical.medicalmonitoring.dto.MedicalCardResponseDto;
import liga.medical.medicalmonitoring.dto.PersonDataRequest;
import liga.medical.medicalmonitoring.dto.PersonDataRequestDto;
import liga.medical.medicalmonitoring.dto.PersonDataResponse;
import liga.medical.medicalmonitoring.dto.PersonDataResponseDto;
import liga.medical.medicalmonitoring.dto.PersonRequest;
import liga.medical.medicalmonitoring.dto.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PersonFacade {

    private final MedicalCardFacade medicalCardFacade;
    private final ContactFacade contactFacade;
    private final PersonDataFacade personDataFacade;

    private final ModelMapper modelMapper;

    @Transactional
    public Long createPerson(PersonRequest request) {
        MedicalCardRequestDto medicalCard = request.getMedicalCard();
        ContactRequestDto contact = request.getContact();
        PersonDataRequestDto personData = request.getPersonData();

        MedicalCardIllnessRequest medicalCardIllnessRequest =
                modelMapper.map(medicalCard, MedicalCardIllnessRequest.class);
        MedicalCardIllnessSoftResponse medicalCardWithIllnessesIds =
                medicalCardFacade.createMedicalCardWithIllnesses(medicalCardIllnessRequest);

        ContactAddressRequest contactAddressRequest = modelMapper.map(contact, ContactAddressRequest.class);
        ContactAddressSoftResponse contactWithAddressesIds =
                contactFacade.createContactWithAddresses(contactAddressRequest);

        PersonDataRequest personDataRequest = modelMapper.map(personData, PersonDataRequest.class);
        personDataRequest.setMedicalCardId(medicalCardWithIllnessesIds.getMedicalCardId());
        personDataRequest.setContactId(contactWithAddressesIds.getContactId());
        PersonDataResponse personDataResponse = personDataFacade.create(personDataRequest);

        return personDataResponse.getId();
    }

    public PersonResponse getPersonById(Long id) {
        PersonDataResponse personData = personDataFacade.getById(id);
        Long medicalCardId = personData.getMedicalCardId();
        Long contactId = personData.getContactId();
        MedicalCardIllnessResponse medicalCardIllness = medicalCardFacade.getById(medicalCardId);
        ContactAddressResponse contactAddresses = contactFacade.getById(contactId);

        return PersonResponse.builder()
                .personData(modelMapper.map(personData, PersonDataResponseDto.class))
                .medicalCard(modelMapper.map(medicalCardIllness, MedicalCardResponseDto.class))
                .contact(modelMapper.map(contactAddresses, ContactResponseDto.class))
                .build();
    }
}
