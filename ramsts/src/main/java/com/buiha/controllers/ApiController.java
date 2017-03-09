package com.buiha.controllers;

import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.buiha.service.OssExceptionInformation;
import com.buiha.service.OssService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by tal on 2017/03/09.
 */

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(method = RequestMethod.GET, path = "/listbuckets")
    public List<Bucket> listBuckets(@RequestParam(name = "accessKey") String accessKey,
                                    @RequestParam(name = "secretKey") String secretKey) {
        OssService service = new OssService(accessKey, secretKey);
        return service.listBuckets();
    }


    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(OSSException.class)
    @ResponseBody
    public Map<String, String> processOssServiceError() {

    }
}
