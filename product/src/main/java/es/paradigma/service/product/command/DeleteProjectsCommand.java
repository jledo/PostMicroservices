package es.paradigma.service.product.command;
import com.paradigma.arquitecture.StaticApplicationContextAccess;
import com.paradigma.arquitecture.command.EventCommand;

import es.paradigma.service.product.domain.ProductRepository;
import es.paradigma.shared.event.UserDeleteEvent;

public class DeleteProjectsCommand extends EventCommand<UserDeleteEvent>{

	public DeleteProjectsCommand() {
		super();
	}

	@Override
	protected void execute() {
		String userId = this.event.getSource().getData().getSource().getData();
		ProductRepository productRepository =StaticApplicationContextAccess.
				getApplicationContext().getBean(ProductRepository.class);
		
		productRepository.deleteByUserId(userId);

	}

}
