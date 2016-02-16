package es.paradigma.service.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@SpringBootApplication
@EnableEurekaServer
public class RegistrationServer {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RegistrationServer.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}
}
