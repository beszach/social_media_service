package market.controller;

import market.model.VkInfo;
import market.service.VkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/change-access-token")
public class VkController {

    @Value("${vk.client.id}")
    private Integer VK_CLIENT_ID;

    @Autowired
    private VkService vkService;

    @GetMapping
    public String test1(ModelMap modelMap){
        modelMap.addAttribute("new_vk_info", new VkInfo());
        return "test";
    }

    @PostMapping
    public String test2(@ModelAttribute VkInfo vkInfo){
        vkService.changeProperty(vkInfo.getVk_access_token(), vkInfo.getVk_group_id());
        return "redirect:/change-access-token/done";
    }

    @GetMapping("/test-redirect")
    public String testRedirect(){
        String GET_TOKEN_URL = new StringBuilder("https://oauth.vk.com/authorize?")
                .append("client_id=").append(VK_CLIENT_ID)
                .append("&display=").append("page")
                .append("&redirect_uri=").append("https://oauth.vk.com/blank.html")
                .append("&scope=").append("photos,wall,offline,groups")
                .append("&response_type=token")
                .append("&v=5.131")
                .toString();
        return "redirect:"+GET_TOKEN_URL;
    }

    @GetMapping("/done")
    public String complited(){
        return "good";
    }


}
