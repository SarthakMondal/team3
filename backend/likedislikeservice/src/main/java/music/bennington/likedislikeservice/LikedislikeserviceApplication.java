package music.bennington.likedislikeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LikedislikeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikedislikeserviceApplication.class, args);
	}

}
