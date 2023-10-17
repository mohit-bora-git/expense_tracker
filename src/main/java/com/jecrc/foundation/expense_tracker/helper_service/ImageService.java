package com.jecrc.foundation.expense_tracker.helper_service;

import com.jecrc.foundation.expense_tracker.config.AmazonS3Client;
import com.jecrc.foundation.expense_tracker.config.AsyncConfig;
import com.jecrc.foundation.expense_tracker.config.ConfigProps;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

  @Autowired
  private AmazonS3Client amazonS3Client;

  @Autowired
  private ConfigProps configProps;

  @Async(value = AsyncConfig.TASK_EXECUTOR)
  public void uploadImageToS3Bucket(String fileName, MultipartFile image) {
    amazonS3Client.uploadImageToS3bucket(configProps.getAwsS3BucketName(),
        fileName + getContentType(image.getContentType()), image);
  }

  private String getContentType(String contentType) {
    if (contentType.equals(ContentType.IMAGE_JPEG.toString())) {
      return ".jpeg";
    } else if (contentType.equals(ContentType.IMAGE_PNG.toString())) {
      return ".png";
    } else
      return ".jpg";
  }
}
