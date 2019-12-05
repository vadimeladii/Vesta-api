package com.vesta.service;

import java.util.List;

public interface EmailService {

    void sendEmailForgotPassword(String username, String email);
}
