package pl.marshallbaby.tgmessagetojmsconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class TgMessageToJmsConverterApplication {

  public static void main(String[] args) {
    SpringApplication.run(TgMessageToJmsConverterApplication.class, args);
  }

}
