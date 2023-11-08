package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.entity.User;

public interface EmailService {
    public void sendHtmlEmail(User user, String subject);
}
