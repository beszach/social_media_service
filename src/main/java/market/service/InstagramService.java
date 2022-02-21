package market.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import market.dto.ItemImageDto;
import market.model.PostInstagram;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InstagramService {

    @Value("${instagram.page.id}")
    private Long INSTAGRAM_PAGE_ID;

    @Value("${fb.page.access.token}")
    private String FB_PAGE_ACCESS_TOKEN;

    @Value("${fb.api.link}")
    private String FB_API_LINK;

    public void pushPostToPage(List<ItemImageDto> itemImageDtoList, String description, String link) {
        String CREATE_CONTAINER_URL = new StringBuilder(FB_API_LINK)
                .append(INSTAGRAM_PAGE_ID)
                .append("/media")
                .toString();
        String caption = new StringBuilder(description)
                .append("\n")
                .append("Понравилась вещь и хочешь узнать подробности? Переходи сюда: ")
                .append(link)
                .toString();

        PostInstagram postInstagram = PostInstagram.builder()
                .image_url(itemImageDtoList.get(0).getURL())
                .access_token(FB_PAGE_ACCESS_TOKEN)
                .caption(caption)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostInstagram> entity = new HttpEntity<>(postInstagram, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(CREATE_CONTAINER_URL, HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            Long creation_id = jsonNode.get("id").asLong();

            String PUSH_POST_URL = new StringBuilder(FB_API_LINK)
                    .append(INSTAGRAM_PAGE_ID)
                    .append("/media_publish?")
                    .append("creation_id=")
                    .append(creation_id)
                    .append("&access_token=")
                    .append(FB_PAGE_ACCESS_TOKEN)
                    .toString();
            ResponseEntity<String> response2 = restTemplate.exchange(PUSH_POST_URL, HttpMethod.POST, null, String.class);
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
        }

    }
}
