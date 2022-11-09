package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.model.Signal;
import liga.medical.medicalmonitoring.core.repository.SignalRepository;
import liga.medical.medicalmonitoring.core.service.api.SignalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignalServiceImpl implements SignalService {

    private final SignalRepository signalRepository;

    @Override
    public void save(Signal signal) {
        signalRepository.save(signal);
    }
}
