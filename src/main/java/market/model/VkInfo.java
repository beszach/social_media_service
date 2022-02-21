package market.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VkInfo {
    private String vk_access_token;
    private Integer vk_group_id;
}
