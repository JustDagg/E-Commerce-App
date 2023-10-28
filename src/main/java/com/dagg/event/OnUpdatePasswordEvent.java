package com.dagg.event;

import com.dagg.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author Dagg
 */

@SuppressWarnings("serial")
public class OnUpdatePasswordEvent extends ApplicationEvent{

	private int id;
	
	private String email;
	
	public OnUpdatePasswordEvent(Object source) {
		super(source);
		User ac = (User) source;
		this.id = ac.getId();
		this.email = ac.getEmail();
	}
	
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

}
