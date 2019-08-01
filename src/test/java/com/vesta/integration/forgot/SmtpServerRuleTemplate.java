package com.vesta.integration.forgot;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.rules.ExternalResource;

import javax.mail.internet.MimeMessage;

import static com.vesta.common.UserUtilData.USER_EMAIL;

public class SmtpServerRuleTemplate extends ExternalResource {

    private String mailUsername;
    private String mailPassword;
    private GreenMail mailServer;
    private int port;


    SmtpServerRuleTemplate(String mailUsername, String mailPassword, int port) {
        this.mailUsername = mailUsername;
        this.mailPassword = mailPassword;
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        mailServer = new GreenMail(new ServerSetup(port, null, "smtp"));
        mailServer.start();
        mailServer.setUser(USER_EMAIL, mailUsername, mailPassword);

    }

    MimeMessage[] getMessages() {
        return mailServer.getReceivedMessages();
    }

    @Override
    protected void after() {
        super.after();
        mailServer.stop();
    }
}
