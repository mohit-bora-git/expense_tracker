package com.jecrc.foundation.expense_tracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class BaseController {

  public DeferredResult<ResponseEntity<?>> getDeferredResult() {
    return new DeferredResult<>();
  }

  public void processDeferredResult(final DeferredResult<ResponseEntity<?>> df,
      CompletableFuture<ResponseEntity<?>> cf, String apiEndPoint, Long startTime, String reqId) {
    df.onCompletion(
        () -> log.info("Request processed apiEndPoint: {}, reqID: {}, execTime: {}", apiEndPoint,
            reqId, System.currentTimeMillis() - startTime));
    cf.thenAccept(result -> {
      if (df.hasResult()) {
        return;
      }
      df.setResult(result);
    });
    cf.exceptionally(e -> {
      df.setResult(ResponseEntity.ok(e));
      return null;
    });
  }

  public String generateReqID() {
    return UUID.randomUUID().toString();
  }
}
