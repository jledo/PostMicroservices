package es.paradigma.service.agregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.paradigma.arquitecture.ParadigmaApplication;
import com.paradigma.arquitecture.model.DataAccessMode;
import com.paradigma.arquitecture.model.SecurityMode;

@ParadigmaApplication(securityMode = SecurityMode.ACCESS_TOKEN, dataAccessMode = DataAccessMode.MONGO, enableAmqpConfig=true, enableCommander=true)
@EnableDiscoveryClient
@EnableEurekaClient
public class AgregationApplication {

    public static void main(String[] args) {	 
		SpringApplication app = new SpringApplication(AgregationApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
    }
}
