package market.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemImageDto {
    private String name;
    private byte[] bytes;
    private String URL;
}
