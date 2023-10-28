package com.dagg.service.impl;

import com.dagg.entity.User;
import com.dagg.service.MailService;
import com.dagg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@Component
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendRegistrationUserConfirm(String email) {
        User user = userService.findAccountByEmail(email);

        String confirmationUrl = "http://localhost:8889/api/active_account?id=" + user.getId();

        String subject = "Xác Nhận Đăng Ký Account";
        String content = "Bạn đã đăng ký thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    }

    @Override
    public void sendUpdatePasswordConfirm(int id, String email) {
        String confirmationUrl = "http://localhost:8889/api/active_account?id=" + id;

        String subject = "Xác Nhận Thay Đổi Mật Khẩu";
        String content = "Bạn đã đổi mật khẩu thành công. Click vào link dưới đây để kích hoạt tài khoản \n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    }

    private void sendEmail(final String recipientEmail, final String subject, final String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }
}
