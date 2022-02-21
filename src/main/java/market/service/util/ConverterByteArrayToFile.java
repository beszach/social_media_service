package market.service.util;

import market.dto.ItemImageDto;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConverterByteArrayToFile {

    public static List<File> convert(List<ItemImageDto> itemImageDtoList){
        List<File> files = new ArrayList<>();
        try {
            for (ItemImageDto itemImageDto : itemImageDtoList) {
                File file = new File(itemImageDto.getName());
                FileUtils.writeByteArrayToFile(file, itemImageDto.getBytes());
                files.add(file);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return files;
    }

}
