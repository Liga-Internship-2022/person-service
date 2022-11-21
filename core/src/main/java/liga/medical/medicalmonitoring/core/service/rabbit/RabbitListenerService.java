package liga.medical.medicalmonitoring.core.service.rabbit;

import liga.medical.medicalmonitoring.api.service.RabbitService;
import lombok.RequiredArgsConstructor;
import model.QueueNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitListenerService {

    private final RabbitService rabbitService;

    @RabbitListener(queues = {
            QueueNames.DAILY_QUEUE_NAME,
            QueueNames.ALERT_QUEUE_NAME,
            QueueNames.ERROR_QUEUE_NAME
    })
    public void receiveAndLogMessage(String message) {
        rabbitService.logSignal(message);
    }
}
