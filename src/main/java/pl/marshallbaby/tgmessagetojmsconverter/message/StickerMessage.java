package pl.marshallbaby.tgmessagetojmsconverter.message;

import com.pengrad.telegrambot.model.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StickerMessage extends Message {

  private String fileId;
  private String fileUniqueId;
  private Integer width;
  private Integer height;
  private boolean isAnimated;
  private boolean isVideo;
  private String emoji;

  public StickerMessage(Update update) {
    super(update);
    this.fileId = update.message().sticker().fileId();
    this.fileUniqueId = update.message().sticker().fileUniqueId();
    this.width = update.message().sticker().width();
    this.height = update.message().sticker().height();
    this.isAnimated = update.message().sticker().isAnimated();
    this.isVideo = update.message().sticker().isVideo();
    this.emoji = update.message().sticker().emoji();
  }

}
