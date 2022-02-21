package market.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostFB {
    private String message;
    private String link;
    private String access_token;
    private List<AttachmentPostFb> child_attachments;

}
