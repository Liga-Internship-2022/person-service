package liga.medical.medicalmonitoring.api.service;

import liga.medical.medicalmonitoring.dto.IllnessRequest;
import liga.medical.medicalmonitoring.dto.IllnessResponse;

import java.util.List;

public interface IllnessService {

    Long create(IllnessRequest request, Long medicalCardId);

    IllnessResponse getById(Long id);

    List<IllnessResponse> getAll();

    boolean deleteById(Long id);
}
