package co.motorq.motorq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MotorqApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotorqApplication.class, args);
	}

//	@GetMapping("/root")
//	public String apiRoot(){
//		return "Ashish Yadav";
//	}

}
