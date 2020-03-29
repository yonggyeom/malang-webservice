package com.malang.webservice.mobile.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.EnumSet;
import java.util.Optional;

import static java.nio.file.attribute.PosixFilePermission.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3UploaderService {

    private AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        String fileName = dirName + "/" + multipartFile.getName();
        System.out.println("dirName : " + dirName);
        System.out.println("multipartFile.getName() : " + multipartFile.getName());
        System.out.println("fileName : " + fileName);

        String uploadImageUrl = putS3(multipartFile, fileName);
        return uploadImageUrl;
    }

    private String putS3(MultipartFile multipartFile, String fileName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        System.out.println("multipartFile.getSize() : " + multipartFile.getSize());
        System.out.println("metadata.getContentLength() : " + metadata.getContentLength());
        System.out.println("bucket : " + bucket);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata);
        System.out.println("putObjectRequest : " + putObjectRequest.toString());
        System.out.println("amazonS3Client : " + amazonS3Client.toString());

        amazonS3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }
}
