package pl.marshallbaby.tgmessagetojmsconverter.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bot {

  @Autowired
  Listener listener;

  @Bean
  public TelegramBot telegramBot(@Value("${telegram.bot.token}") String token) {
    TelegramBot bot = new TelegramBot(token);
    bot.setUpdatesListener(updates -> {
      listener.processUpdates(updates);
      return UpdatesListener.CONFIRMED_UPDATES_ALL;
    });
    return bot;
  }

}
