package com.jecrc.foundation.expense_tracker.config;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Slf4j
public class AmazonS3Client {

  private AmazonS3 amazonS3;

  @PostConstruct
  public void init() {
    amazonS3 = AmazonS3ClientBuilder.standard()
        .withCredentials(new EnvironmentVariableCredentialsProvider())
        .withRegion(Regions.AP_SOUTH_1).build();
    log.info("AmazonS3Client Configured");
  }

  public void uploadImageToS3bucket(String bucketName, String fileName, MultipartFile image)
      throws IOException {
    amazonS3.putObject(
        new PutObjectRequest(bucketName, fileName, image.getInputStream(), new ObjectMetadata()));
  }
}
