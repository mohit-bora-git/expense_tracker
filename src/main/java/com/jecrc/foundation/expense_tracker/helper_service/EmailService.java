package com.jecrc.foundation.expense_tracker.helper_service;

import com.jecrc.foundation.expense_tracker.config.AsyncConfig;
import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.config.EmailClient;
import com.jecrc.foundation.expense_tracker.dos.UserDO;
import com.jecrc.foundation.expense_tracker.utils.EmailTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private ConfigProps configProps;

  @Autowired
  private EmailClient emailClient;

  @Async(value = AsyncConfig.TASK_EXECUTOR)
  public void sendEmail(UserDO userDO, Double overHeadTransaction) {
    emailClient.sendMail(configProps.getAppEmail(), userDO.getEmail(),
        EmailTemplateUtils.createTransactionLimitExceedTemplate(userDO, overHeadTransaction),
        "TRANSACTION ALERT");
  }
}
