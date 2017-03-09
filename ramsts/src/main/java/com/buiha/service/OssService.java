package com.buiha.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ListBucketsRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by tal on 2017/03/08.
 */

public class OssService {
    private static final String endpoint = "http://oss-ap-northeast-1.aliyuncs.com";
    private String accessKey;
    private String secretKey;

    private OSSClient ossClient;

    public OssService(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.ossClient = new OSSClient(endpoint, accessKey, secretKey);
    }

    /**
     * isBucketExisted: Check for bucket existence.
     * @param bucketName
     * @return true if bucket exists, false if bucket does not exist
     */
    public boolean isBucketExisted(String bucketName) {
        return ossClient.doesBucketExist(bucketName);
    }

    /**
     * createBucket: Create an OSS bucket with bucketName in the parameter
     * @param bucketName
     */
    public void createBucket(String bucketName) throws OSSException {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
        ossClient.createBucket(createBucketRequest);
    }

    /**
     * listBuckets: Return all buckets
     * @return A Stream of Bucket
     */
    public List<Bucket> listBuckets() {
        List<Bucket> result = ossClient.listBuckets();
        return result;
    }

    /**
     * deleteBucket: Delete bucket with name in the parameter
     * @param bucketName
     */
    public void deleteBucket(String bucketName) {
       ossClient.deleteBucket(bucketName);
    }

}
