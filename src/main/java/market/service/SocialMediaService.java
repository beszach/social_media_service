package market.service;

import market.dto.AdvInfoForPostDto;
import market.dto.ItemImageDto;
import market.feign.StorageFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaService {

    private final FacebookService facebookService;
    private final InstagramService instagramService;
    private final StorageFeignClient storageFeignClient;
    private final VkService vkService;

    public SocialMediaService(FacebookService facebookService, InstagramService instagramService,
                              StorageFeignClient storageFeignClient, VkService vkService) {
        this.facebookService=facebookService;
        this.instagramService=instagramService;
        this.storageFeignClient = storageFeignClient;
        this.vkService = vkService;
    }

    public void pushPost(AdvInfoForPostDto infoForPost){
        List<ItemImageDto> itemImageDtoList = storageFeignClient.getItemImageDtos(infoForPost.getIdImages());
        vkService.pushPostToGroup(itemImageDtoList, infoForPost.getDescription(), infoForPost.getLink());
        facebookService.pushPostToPage(itemImageDtoList, infoForPost.getDescription(), infoForPost.getLink());
        instagramService.pushPostToPage(itemImageDtoList, infoForPost.getDescription(), infoForPost.getLink());
    }

//    public void test(){
//        vkService.change();
//    }
//
//    public void test2(String str, Integer integer){
//        vkService.setTest(str);
//        vkService.setInteger(integer);
//    }

}
