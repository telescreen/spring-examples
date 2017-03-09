package com.buiha;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

import java.io.*;

/**
 * Created by tal on 2017/02/22.
 */
public class MainMain {
    /* RAM User: sbcloud */
    private static String accessKey = "LTAIMsPaUcuHZNQH";
    private static String accessSecret = "P1naOBUgn2twOoo4YE1f3WA12lhqOm";
    private static String endpoint = "http://oss-ap-northeast-1.aliyuncs.com";
    private static String bucketName = "sbcloudbucket";
    private static String key = "sbcloudkey";

    /*public static void main(String[] args) {
        OSSClient ossClient = new OSSClient(endpoint, accessKey, accessSecret);
        System.out.println("Getting Started with OSS SDK\n");

        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                System.out.println("Creating bucket: " + bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }

            System.out.println("Listing buckets");
            ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
            listBucketsRequest.setMaxKeys(500);
            for (Bucket bucket : ossClient.listBuckets()) {
                System.out.println(" - " + bucket.getName());
            }
            System.out.println();

            System.out.println("Uploading a new object to OSS from a file\n");
            ossClient.putObject(new PutObjectRequest(bucketName, key, createTempFile()));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (IOException e) {
            System.out.println("Temporary files can not be created");
            System.exit(-1);
        } finally {
            ossClient.shutdown();
        }
    }

    private static File createTempFile() throws IOException {
        File file = File.createTempFile("oss-test", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("asbkldkjazvcvcv;askdjfdlkjf");
        writer.write("0123234124089");
        writer.close();

        return file;
    }
    */
}
