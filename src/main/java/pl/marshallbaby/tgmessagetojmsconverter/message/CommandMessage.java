package pl.marshallbaby.tgmessagetojmsconverter.message;

import com.pengrad.telegrambot.model.Update;

public class CommandMessage extends TextMessage{
    public CommandMessage(Update update){
      super(update);
    }
}
