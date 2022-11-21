package liga.medical.medicalmonitoring.api.service;

import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardRequest;
import liga.medical.medicalmonitoring.dto.rest.medical_card.MedicalCardResponse;

import java.util.List;

public interface MedicalCardService {

    Long create(MedicalCardRequest request);

    MedicalCardResponse getById(Long id);

    List<MedicalCardResponse> getAll();

    boolean deleteById(Long id);
}
