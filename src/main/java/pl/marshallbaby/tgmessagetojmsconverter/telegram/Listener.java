package pl.marshallbaby.tgmessagetojmsconverter.telegram;

import com.pengrad.telegrambot.model.Update;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pl.marshallbaby.tgmessagetojmsconverter.message.CommandMessage;
import pl.marshallbaby.tgmessagetojmsconverter.message.TextMessage;

@Service
@Slf4j
public class Listener {

  @Autowired
  private ActiveMQDestination destination;

  @Autowired
  private JmsTemplate jmsTemplate;

  public void processUpdates(List<Update> updates) {

    for (Update update : updates) {

      if (update.message().text() != null) {
        if (update.message().text().startsWith("/")) {
          // Command
          jmsTemplate.convertAndSend(destination, new CommandMessage(update));
          continue;
        } else {
          // Text
          jmsTemplate.convertAndSend(destination, new TextMessage(update));
          continue;
        }
      }

      log.warn("Unknown type of telegram update received: {}", update);

    }
  }
}
