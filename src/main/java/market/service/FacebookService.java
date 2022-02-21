package market.service;

import market.dto.ItemImageDto;
import market.model.AttachmentPostFb;
import market.model.PostFB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacebookService {

    @Value("${fb.page.access.token}")
    private String FB_PAGE_ACCESS_TOKEN;

    @Value("${fb.page.id}")
    private Long FB_PAGE_ID;

    @Value("${fb.api.link}")
    private String FB_API_LINK;

    public void pushPostToPage(List<ItemImageDto> itemImageDtoList, String description, String link){
        List<AttachmentPostFb> attachmentPostFbList = new ArrayList<>();
        for(ItemImageDto imageDto : itemImageDtoList){
            attachmentPostFbList.add(new AttachmentPostFb(link, imageDto.getURL()));
        }
        PostFB postFB = PostFB.builder()
                .child_attachments(attachmentPostFbList)
                .access_token(FB_PAGE_ACCESS_TOKEN)
                .message(description)
                .link(link)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostFB> entity = new HttpEntity<>(postFB, headers);
        RestTemplate restTemplate = new RestTemplate();

        String URL_PUSH_POST = new StringBuilder(FB_API_LINK)
                .append(FB_PAGE_ID)
                .append("/feed")
                .toString();

        ResponseEntity<String> response = restTemplate.exchange(URL_PUSH_POST, HttpMethod.POST, entity, String.class);
    }
}
