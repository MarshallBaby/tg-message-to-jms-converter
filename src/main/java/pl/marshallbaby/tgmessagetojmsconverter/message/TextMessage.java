package pl.marshallbaby.tgmessagetojmsconverter.message;

import com.pengrad.telegrambot.model.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class TextMessage extends Message {

  private String text;

  public TextMessage(Update update) {
    super(update);
    this.text = update.message().text();
  }

}
