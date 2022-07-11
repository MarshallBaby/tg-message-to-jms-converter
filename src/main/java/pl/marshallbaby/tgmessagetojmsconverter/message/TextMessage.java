package pl.marshallbaby.tgmessagetojmsconverter.message;

import com.pengrad.telegrambot.model.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextMessage extends Message {

  private String text;

  public TextMessage(Update update) {
    super(update);
    this.text = update.message().text();
  }

}
