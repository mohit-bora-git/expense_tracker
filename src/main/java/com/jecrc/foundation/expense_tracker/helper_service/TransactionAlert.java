package com.jecrc.foundation.expense_tracker.helper_service;

import com.jecrc.foundation.expense_tracker.config.AsyncConfig;
import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import com.jecrc.foundation.expense_tracker.config.EmailClient;
import com.jecrc.foundation.expense_tracker.dos.UserDo;
import com.jecrc.foundation.expense_tracker.utils.EmailTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TransactionAlert {

  private final ConfigProps configProps;

  private final EmailClient emailClient;

  @Autowired
  private TransactionAlert(ConfigProps configProps,EmailClient emailClient){
    this.configProps=configProps;
    this.emailClient=emailClient;
  }

  @Async(value = AsyncConfig.TASK_EXECUTOR)
  public void sendEmail(UserDo userDO, Double overHeadTransaction) {
    emailClient.sendMail(configProps.getAppEmail(), userDO.getEmail(),
        EmailTemplateUtils.createTransactionLimitExceedTemplate(userDO, overHeadTransaction),
        "TRANSACTION ALERT");
  }
}
