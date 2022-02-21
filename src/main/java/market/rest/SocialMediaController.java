package market.rest;

import market.dto.AdvInfoForPostDto;
import market.dto.ItemImageDto;
import market.feign.StorageFeignClient;
import market.service.SocialMediaService;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/social-media")
public class SocialMediaController {


    private final StorageFeignClient storageFeignClient;
    private final SocialMediaService socialMediaService;

    public SocialMediaController(StorageFeignClient storageFeignClient, SocialMediaService socialMediaService) {
        this.storageFeignClient = storageFeignClient;
        this.socialMediaService = socialMediaService;
    }

    @PostMapping("/push-info")
    public void getInfoForPost(@RequestBody AdvInfoForPostDto advInfoForPostDto){
        socialMediaService.pushPost(advInfoForPostDto);
    }

}
