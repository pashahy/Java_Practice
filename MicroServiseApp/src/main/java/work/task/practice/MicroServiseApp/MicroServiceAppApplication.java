package work.task.practice.MicroServiseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MicroServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceAppApplication.class, args);

		String url = "http://localhost:8081/api/users";
		WebClient.Builder builder = WebClient.builder();
		String usersList = builder.build()
				.get()
				.uri(url)
				.retrieve()
				.bodyToMono(String.class)
				.block();

		System.out.println(usersList);
	}
}