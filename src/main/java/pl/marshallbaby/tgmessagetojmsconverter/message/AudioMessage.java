package pl.marshallbaby.tgmessagetojmsconverter.message;

import com.pengrad.telegrambot.model.Audio;
import com.pengrad.telegrambot.model.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AudioMessage extends Message {

  private String fileId;
  private String fileUniqueId;
  private Integer duration;
  private String performer;
  private String title;
  private String fileName;
  private String mimeType;
  private Integer fileSize;

  private String text;

  public AudioMessage(Update update) {
    super(update);

    Audio audio = update.message().audio();
    this.fileId = audio.fileId();
    this.fileUniqueId = audio.fileUniqueId();
    this.duration = audio.duration();
    this.performer = audio.performer();
    this.title = audio.title();
    this.fileName = audio.fileName();
    this.mimeType = audio.mimeType();
    this.fileSize = audio.fileSize();
    this.text = update.message().caption();

  }

}
