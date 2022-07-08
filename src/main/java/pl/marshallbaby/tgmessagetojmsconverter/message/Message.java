package pl.marshallbaby.tgmessagetojmsconverter.message;

import com.pengrad.telegrambot.model.Update;
import java.util.Date;
import lombok.Data;

@Data
public abstract class Message {

  private Date creatureDateTime;
  private Integer messageId;
  private Long chatId;
  private String username;
  private String firstName;
  private String lastName;

  Message(Update update) {
    creatureDateTime = new Date();
    this.messageId = update.message().messageId();
    this.chatId = update.message().chat().id();
    this.username = update.message().chat().username();
    this.firstName = update.message().chat().firstName();
    this.lastName = update.message().chat().lastName();
  }

  public Message() {
  }
}
