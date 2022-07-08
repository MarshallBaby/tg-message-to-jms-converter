package pl.marshallbaby.tgmessagetojmsconverter.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import pl.marshallbaby.tgmessagetojmsconverter.message.CommandMessage;
import pl.marshallbaby.tgmessagetojmsconverter.message.Message;
import pl.marshallbaby.tgmessagetojmsconverter.message.TextMessage;

@Configuration
@EnableJms
public class ActiveMQConfiguration {

  // Set your ActiveMQ destination here
  @Bean
  public ActiveMQDestination destination() {
    return new ActiveMQTopic("destination.placeholder");
  }

  @Bean
  public MappingJackson2MessageConverter messageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

    Map<String, Class<?>> typeIdMappings = new HashMap<>();
    typeIdMappings.put("Message", Message.class);
    typeIdMappings.put("TextMessage", TextMessage.class);
    typeIdMappings.put("CommandMessage", CommandMessage.class);

    converter.setTypeIdMappings(typeIdMappings);
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

}
