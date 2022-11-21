package liga.medical.medicalmonitoring.core.service.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.api.service.RabbitSenderService;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import model.RabbitMessageDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import static model.QueueNames.ERROR_QUEUE_NAME;
import static model.SystemType.PERSON_SERVICE;

@Service
@RequiredArgsConstructor
public class RabbitSenderServiceImpl implements RabbitSenderService {

    private final LoggingService loggingService;

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void sendMessage(RabbitMessageDto messageDto, String queue) {
        String messageFromJson = objectMapper.writeValueAsString(messageDto);
        amqpTemplate.convertAndSend(queue, messageFromJson);

        loggingService.logQueueMessageSending(messageDto, queue, PERSON_SERVICE);
    }

    @Override
    public void sendError(String message) {
        amqpTemplate.convertAndSend(ERROR_QUEUE_NAME, message);

        loggingService.logQueueSendingException(message, ERROR_QUEUE_NAME, PERSON_SERVICE);
    }
}
