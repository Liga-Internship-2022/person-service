package liga.medical.medicalmonitoring.core.facade;

import liga.medical.medicalmonitoring.dto.rest.ContactAddressRequest;
import liga.medical.medicalmonitoring.dto.rest.ContactAddressResponse;
import liga.medical.medicalmonitoring.dto.rest.ContactAddressSoftResponse;
import liga.medical.medicalmonitoring.dto.rest.contact.ContactRequestDto;
import liga.medical.medicalmonitoring.dto.rest.contact.ContactResponseDto;
import liga.medical.medicalmonitoring.dto.rest.MedicalCardIllnessRequest;
import liga.medical.medicalmonitoring.dto.rest.MedicalCardIllnessResponse;
import liga.medical.medicalmonitoring.dto.rest.MedicalCardIllnessSoftResponse;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardRequestDto;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardResponseDto;
import liga.medical.medicalmonitoring.dto.rest.person_data.PersonDataRequest;
import liga.medical.medicalmonitoring.dto.rest.person_data.PersonDataRequestDto;
import liga.medical.medicalmonitoring.dto.rest.person_data.PersonDataResponse;
import liga.medical.medicalmonitoring.dto.rest.person_data.PersonDataResponseDto;
import liga.medical.medicalmonitoring.dto.rest.person_data.PersonRequest;
import liga.medical.medicalmonitoring.dto.rest.person_data.PersonResponse;
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
