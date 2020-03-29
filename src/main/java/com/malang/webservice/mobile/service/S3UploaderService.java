package com.malang.webservice.mobile.service;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
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
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3UploaderService {

    private AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        InstanceProfileCredentialsProvider instanceProfileCredentialsProvider
                = new InstanceProfileCredentialsProvider(true);

        System.out.println(instanceProfileCredentialsProvider.getCredentials().toString());

        amazonS3Client = AmazonS3ClientBuilder.standard()
                .withRegion(this.region)
                .withCredentials(instanceProfileCredentialsProvider)
                .build();
    }

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
