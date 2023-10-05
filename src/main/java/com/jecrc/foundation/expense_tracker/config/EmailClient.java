package com.jecrc.foundation.expense_tracker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EmailClient {

  @Autowired
  private ConfigProps configProps;

  private JavaMailSenderImpl mailSender;

  public void sendMail() {

  }

}
