package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.api.service.AddressService;
import liga.medical.medicalmonitoring.core.exception.NotFoundException;
import liga.medical.medicalmonitoring.core.model.Address;
import liga.medical.medicalmonitoring.core.repository.AddressRepository;
import liga.medical.medicalmonitoring.dto.rest.address.AddressRequest;
import liga.medical.medicalmonitoring.dto.rest.address.AddressResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long create(AddressRequest request, Long contactId) {
        log.info("got request: {}", request);
        Address address = modelMapper.map(request, Address.class);
        address.setContactId(contactId);
        log.info("converted to entity: {}", address);
        return addressRepository.save(address).getId();
    }

    @Override
    public AddressResponse getById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("address not found by id: " + id));
        return modelMapper.map(address, AddressResponse.class);
    }

    @Override
    public List<AddressResponse> getAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> modelMapper.map(address, AddressResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (!existsById(id)) {
            throw new NotFoundException("address does not exist with id: " + id);
        }
        addressRepository.deleteById(id);
        return true;
    }

    private boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }
}
