package com.dagg.service;

/**
 * @author Dagg
 * @created 27/10/2023
 */
public interface MailService {

    void sendRegistrationUserConfirm(String email);

    void sendUpdatePasswordConfirm(int id, String email);

}
