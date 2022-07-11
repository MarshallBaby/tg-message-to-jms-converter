package pl.marshallbaby.tgmessagetojmsconverter.message;

import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class PhotoMessage extends Message {

  private List<Photo> photos;
  private String text;

  public PhotoMessage(Update update) {
    super(update);
    photos = Arrays.stream(update.message().photo()).map(Photo::new).collect(Collectors.toList());
    this.text = update.message().caption();
  }

  @Data
  public static class Photo {

    private String fileId;
    private String fileUniqueId;
    private Integer width;
    private Integer height;

    public Photo(@NotNull PhotoSize photoSize) {
      this.fileId = photoSize.fileId();
      this.fileUniqueId = photoSize.fileUniqueId();
      this.width = photoSize.width();
      this.height = photoSize.height();
    }

  }

}
