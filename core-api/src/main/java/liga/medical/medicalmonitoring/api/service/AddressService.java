package liga.medical.medicalmonitoring.api.service;

import liga.medical.medicalmonitoring.dto.AddressRequest;
import liga.medical.medicalmonitoring.dto.AddressResponse;

import java.util.List;

public interface AddressService {

    Long create(AddressRequest request, Long contactId);

    AddressResponse getById(Long id);

    List<AddressResponse> getAll();

    boolean deleteById(Long id);
}
