package es.paradigma.shared.event;

import com.paradigma.arquitecture.event.EventData;
import com.paradigma.arquitecture.event.RemoteEvent;

public class UserDeleteEvent extends RemoteEvent<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -577375080055332943L;

	public UserDeleteEvent(String userId) {
		super(new EventData<String>());
		this.source.setData(userId);
	}

}
