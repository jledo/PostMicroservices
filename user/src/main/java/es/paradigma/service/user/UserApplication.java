package es.paradigma.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.mongodb.core.MongoOperations;

import com.paradigma.arquitecture.ParadigmaApplication;
import com.paradigma.arquitecture.model.DataAccessMode;
import com.paradigma.arquitecture.model.SecurityMode;
import com.paradigma.arquitecture.repository.AdvancedSearchRepositoryHelper;
import com.paradigma.arquitecture.repository.AdvancedSearchRepositoryHelperImpl;

@ParadigmaApplication(securityMode = SecurityMode.ACCESS_TOKEN, dataAccessMode = DataAccessMode.MONGO, enableAmqpConfig = true, enableCommander=true)
@EnableDiscoveryClient
public class UserApplication {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private ConversionService conversionService;

	@Bean
	public AdvancedSearchRepositoryHelper advancedSearchRepositoryHelper(){
		return new AdvancedSearchRepositoryHelperImpl(mongoOperations, conversionService);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class,args);
	}
}
