package market.service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.photos.responses.GetWallUploadServerResponse;
import com.vk.api.sdk.objects.photos.responses.SaveWallPhotoResponse;
import com.vk.api.sdk.objects.photos.responses.WallUploadResponse;
import com.vk.api.sdk.objects.wall.responses.PostResponse;
import market.dto.ItemImageDto;
import market.service.util.ConverterByteArrayToFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.DefaultPropertiesPersister;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class VkService {

    @Value("${vk.access.token}")
    private String ACCESS_TOKEN;

    @Value("${vk.group.id}")
    private Integer GROUP_ID;


    public void pushPostToGroup(List<ItemImageDto> itemImageDtoList, String description, String link){
        List<File> files = ConverterByteArrayToFile.convert(itemImageDtoList);

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        UserActor actor = new UserActor(GROUP_ID, ACCESS_TOKEN);
        try {
            List<String> attachments = new ArrayList<>();
            for(File file : files){
                GetWallUploadServerResponse serverResponse = vk.photos().getWallUploadServer(actor).groupId(GROUP_ID).execute();
                WallUploadResponse uploadResponse = vk.upload().photoWall(serverResponse.getUploadUrl().toString(), file).execute();
                List<SaveWallPhotoResponse> photoList = vk.photos().saveWallPhoto(actor, uploadResponse.getPhoto())
                        .server(uploadResponse.getServer())
                        .groupId(GROUP_ID)
                        .hash(uploadResponse.getHash())
                        .execute();

                SaveWallPhotoResponse photo = photoList.get(0);
                String attachId = "photo" + photo.getOwnerId() + "_" + photo.getId();
                attachments.add(attachId);
            }
            attachments.add(link);

            PostResponse getResponse = vk.wall().post(actor)
                    .ownerId(-GROUP_ID)
                    .fromGroup(true)
                    .message(description)
                    .attachments(attachments)
                    .execute();
        } catch (ApiException | ClientException e){
            System.out.println(e.getMessage());
        }

        files.forEach(File::delete);
    }

    public void changeProperty(String ACCESS_TOKEN, Integer GROUP_ID){
        this.ACCESS_TOKEN=ACCESS_TOKEN;
        this.GROUP_ID=GROUP_ID;
        System.out.println(this.ACCESS_TOKEN);
        System.out.println(this.GROUP_ID);
    try {
        Properties props = new Properties();
        props.setProperty("vk.access.token", ACCESS_TOKEN);
        props.setProperty("vk.group.id", String.valueOf(GROUP_ID));

        File file = new File("social-media-service\\src\\main\\resources\\vk.properties");
        OutputStream out = new FileOutputStream(file);
        DefaultPropertiesPersister p = new DefaultPropertiesPersister();
        p.store(props, out, "");
    } catch (Exception e ) {
        e.printStackTrace();
    }
    }

}
