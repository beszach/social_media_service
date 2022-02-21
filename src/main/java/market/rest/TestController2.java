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
public class TestController2 {


    private final StorageFeignClient storageFeignClient;
    private final SocialMediaService socialMediaService;

    public TestController2(StorageFeignClient storageFeignClient, SocialMediaService socialMediaService) {
        this.storageFeignClient = storageFeignClient;
        this.socialMediaService = socialMediaService;
    }

    @PostMapping("/push-info")
    public void getInfoForPost(@RequestBody AdvInfoForPostDto advInfoForPostDto){
        socialMediaService.pushPost(advInfoForPostDto);
    }

//    @GetMapping
//    public void getTest(){
//        socialMediaService.test();
//    try {
//        Properties props = new Properties();
//        props.setProperty("vk.test.prop", "a4");
//
//        // get or create the file
//        File f = new File("social-media-service\\src\\main\\resources\\vk.properties");
//        OutputStream out = new FileOutputStream( f );
//        // write into it
//        DefaultPropertiesPersister p = new DefaultPropertiesPersister();
//        p.store(props, out, "");
//    } catch (Exception e ) {
//        e.printStackTrace();
//    }
//        socialMediaService.test2("a4");
//        socialMediaService.test();
//    }

}
