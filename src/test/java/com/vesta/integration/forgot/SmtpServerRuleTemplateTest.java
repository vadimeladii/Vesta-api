package com.vesta.integration.forgot;

import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.rules.ExternalResource;

import javax.mail.internet.MimeMessage;


public class SmtpServerRuleTemplateTest extends ExternalResource {

    public GreenMail mailServer;
    private int port;

    public SmtpServerRuleTemplateTest(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        mailServer = new GreenMail(new ServerSetup(port, null, "smtp"));
        mailServer.start();
        GreenMailUser user = mailServer.setUser("vesta.api@gmail.com", "username", "password");

    }

    public MimeMessage[] getMessages() {
        return mailServer.getReceivedMessages();
    }

    @Override
    protected void after() {
        super.after();
        mailServer.stop();
    }
}
