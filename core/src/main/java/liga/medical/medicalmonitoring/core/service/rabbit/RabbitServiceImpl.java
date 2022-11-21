package liga.medical.medicalmonitoring.core.service.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.api.service.PersonDataService;
import liga.medical.medicalmonitoring.api.service.RabbitSenderService;
import liga.medical.medicalmonitoring.api.service.RabbitService;
import liga.medical.medicalmonitoring.core.model.Signal;
import liga.medical.medicalmonitoring.core.service.api.SignalService;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import model.MessageType;
import model.QueueNames;
import model.RabbitMessageDto;
import org.springframework.stereotype.Service;

import static model.SystemType.PERSON_SERVICE;

@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    private final LoggingService loggingService;
    private final RabbitSenderService rabbitSenderService;
    private final SignalService signalService;
    private final PersonDataService personDataService;

    private final ObjectMapper objectMapper;

    @Override
    public void logSignal(String message) {
        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
            MessageType messageType = rabbitMessageDto.getMessageType();

            String fromQueue = "unknown_queue";
            switch (messageType) {
                case DAILY:
                    fromQueue = QueueNames.DAILY_QUEUE_NAME;
                    break;
                case ALERT:
                    fromQueue = QueueNames.ALERT_QUEUE_NAME;
                    break;
                case ERROR:
                    fromQueue = QueueNames.ERROR_QUEUE_NAME;
                    break;
                default:
                    rabbitSenderService.sendError("Не удалось получить сообщение [" +
                            rabbitMessageDto + "] - очередь неизвестна");
            }
            loggingService.logQueueMessageReceiving(rabbitMessageDto, fromQueue, PERSON_SERVICE);

            if (personDataService.getById(rabbitMessageDto.getId()) == null) {
                loggingService.logException("ФЛ с id=" + rabbitMessageDto.getId() + " не существует", PERSON_SERVICE);
            }
            Signal signal = Signal.builder()
                    .personDataId(rabbitMessageDto.getId())
                    .description(rabbitMessageDto.getContent())
                    .type(messageType)
                    .build();
            signalService.save(signal);
        } catch (Exception e) {
            loggingService.logQueueSendingException(e.getMessage(), QueueNames.ERROR_QUEUE_NAME, PERSON_SERVICE);
        }
    }
}
