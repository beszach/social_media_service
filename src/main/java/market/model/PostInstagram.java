package market.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostInstagram {
    private String image_url;
    private String access_token;
    private String caption;
}
