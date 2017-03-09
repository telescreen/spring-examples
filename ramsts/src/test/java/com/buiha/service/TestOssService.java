package com.buiha.service;

import com.aliyun.oss.model.Bucket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by tal on 2017/03/08.
 */
public class TestOssService {
    private OssService service = new OssService(
            "LTAIJkvFPsGnbxVf",
            "Wj5Mk58K5cWBxrXfu19EE3PAEWYr7x");

    @Test
    @DisplayName("Should return true because this test bucket exists")
    public void testIsBucketExisted() {
        assertTrue(service.isBucketExisted("sbcloudbucket"));
    }

    @Test
    @DisplayName("Bucket should be created and deleted without errors")
    public void testCreateAndDeleteBucket() {
        service.createBucket("sbcloudtestbucket");
        assertTrue(service.isBucketExisted("sbcloudtestbucket"));
        service.deleteBucket("sbcloudtestbucket");
        assertFalse(service.isBucketExisted("sbcloudtestbucket"));
    }

    @Test
    @DisplayName("Should return a list of one bucket")
    public void testListBuckets() {
        List<Bucket> buckets = service.listBuckets();
        assertEquals(buckets.size(), 1);
    }
}