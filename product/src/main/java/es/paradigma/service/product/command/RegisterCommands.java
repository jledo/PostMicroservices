package es.paradigma.service.product.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.paradigma.arquitecture.command.Commander;

import es.paradigma.shared.event.UserDeleteEvent;

@Component
public class RegisterCommands implements CommandLineRunner {

	@Autowired
	private Commander commander;
	
	@Override
	public void run(String... args) throws Exception {
		commander.executeCommandOnEvent(UserDeleteEvent.class, new DeleteProjectsCommand());
	}
}
