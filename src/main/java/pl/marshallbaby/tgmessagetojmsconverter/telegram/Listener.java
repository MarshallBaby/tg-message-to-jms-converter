package pl.marshallbaby.tgmessagetojmsconverter.telegram;

import com.pengrad.telegrambot.model.Update;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pl.marshallbaby.tgmessagetojmsconverter.message.CommandMessage;
import pl.marshallbaby.tgmessagetojmsconverter.message.Message;
import pl.marshallbaby.tgmessagetojmsconverter.message.PhotoMessage;
import pl.marshallbaby.tgmessagetojmsconverter.message.StickerMessage;
import pl.marshallbaby.tgmessagetojmsconverter.message.TextMessage;

@Service
@Slf4j
public class Listener {

  final private Logger updatesLogger = LoggerFactory.getLogger("telegramUpdatesLogger");

  final private Logger jmsLogger = LoggerFactory.getLogger("jmsLogger");

  private <T extends Message> void sendAndLog(UUID uuid, T message) {
    jmsTemplate.convertAndSend(destination, message);
    jmsLogger.debug("{} Sent message of {}", uuid, message.getClass().getSimpleName());
  }

  @Autowired
  private ActiveMQDestination destination;

  @Autowired
  private JmsTemplate jmsTemplate;

  public void processUpdates(List<Update> updates) {

    for (Update update : updates) {

      UUID uuid = UUID.randomUUID();

      updatesLogger.debug("{} Received update: {}", uuid, update);

      if (update.message().text() != null) {
        if (update.message().text().startsWith("/")) {
          // Command
          sendAndLog(uuid, new CommandMessage(update));
        } else {
          // Text
          sendAndLog(uuid, new TextMessage(update));
        }
        continue;
      }

      if (update.message().photo() != null) {
        // Photo
        sendAndLog(uuid, new PhotoMessage(update));
        continue;
      }

      if (update.message().sticker() != null) {
        //Sticker
        sendAndLog(uuid, new StickerMessage(update));
      }

      updatesLogger.warn("Unknown type of telegram update received: {}", update);

    }
  }
}
