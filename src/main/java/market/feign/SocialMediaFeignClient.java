package market.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "social-media-service", url = "http://localhost:8091")
public interface SocialMediaFeignClient {
}
