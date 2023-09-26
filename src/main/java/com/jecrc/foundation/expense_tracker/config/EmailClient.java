package com.jecrc.foundation.expense_tracker.config;

import com.jecrc.foundation.expense_tracker.constants.PropertyConstants;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Slf4j
public class EmailClient {

  @Autowired
  private ConfigProps configProps;

  private JavaMailSenderImpl mailSender;

  @PostConstruct
  public void init() {
    mailSender = new JavaMailSenderImpl();
    mailSender.setHost(configProps.getMailHost());
    mailSender.setPort(configProps.getMailPort());
    mailSender.setUsername(configProps.getMailUsername());
    mailSender.setPassword(configProps.getMailPassword());
    Properties props = mailSender.getJavaMailProperties();
    props.put(PropertyConstants.MAIL_TRANSPORT_PROTOCOL, configProps.getMailTransportProtocol());
    props.put(PropertyConstants.MAIL_SMTP_AUTH, configProps.getMailSmtpAuth());
    props.put(PropertyConstants.MAIL_SMTP_STARTTLS_ENABLE,
        configProps.getMailSmtpStartTlsEnabled());
    log.info("EmailClient Configured ");
  }

}
