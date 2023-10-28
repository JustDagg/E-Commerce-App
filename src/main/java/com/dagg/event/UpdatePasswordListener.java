package com.dagg.event;

import com.dagg.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Dagg
 */

@Component
public class UpdatePasswordListener implements ApplicationListener<OnUpdatePasswordEvent>{
	@Autowired
	private MailService mailService;
	
	@Override
	public void onApplicationEvent(OnUpdatePasswordEvent event) {
		mailService.sendUpdatePasswordConfirm(event.getId(), event.getEmail());
	}

}
