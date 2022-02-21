package market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@PropertySource("classpath:social_media.properties")
@PropertySource("classpath:vk.properties")
public class SocialMediaStarter {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaStarter.class, args);
    }

}
