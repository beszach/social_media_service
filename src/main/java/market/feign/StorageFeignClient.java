package market.feign;

import market.dto.ItemImageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Set;

@FeignClient(name = "market-storage")
public interface StorageFeignClient {

    @PostMapping("/api/file/getItemImageDtos")
    List<ItemImageDto> getItemImageDtos(Set<Long> ids);
}
