package market.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvInfoForPostDto {
    private String description;
    private String link;
    private Set<Long> idImages;
}
