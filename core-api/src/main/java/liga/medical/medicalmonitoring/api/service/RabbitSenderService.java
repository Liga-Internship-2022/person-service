package liga.medical.medicalmonitoring.api.service;

import model.RabbitMessageDto;

public interface RabbitSenderService {
    void sendMessage(RabbitMessageDto messageDto, String queue);

    void sendError(String message);
}
