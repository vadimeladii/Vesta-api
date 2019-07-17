package com.vesta.service;

public interface EmailService {

    void sendEmailForgotPassword(String username, String email);
}
