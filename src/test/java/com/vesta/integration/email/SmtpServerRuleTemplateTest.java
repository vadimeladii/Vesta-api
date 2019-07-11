package com.vesta.integration.email;

import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.rules.ExternalResource;

import javax.mail.internet.MimeMessage;


public class SmtpServerRuleTemplateTest extends ExternalResource {
    public GreenMail smtpServer;
    private int port;

    public SmtpServerRuleTemplateTest(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        smtpServer = new GreenMail(new ServerSetup(port, null, "smtp"));
        smtpServer.start();
        GreenMailUser user = smtpServer.setUser("wael@localhost.com", "waelc", "soooosecret");

    }

    public MimeMessage[] getMessages() {
        return smtpServer.getReceivedMessages();
    }

    @Override
    protected void after() {
        super.after();
        smtpServer.stop();
    }
}
