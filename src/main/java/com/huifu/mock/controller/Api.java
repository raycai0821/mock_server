package com.huifu.mock.controller;

import com.huifu.mock.entity.MockDataEntity;
import com.huifu.mock.service.MockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class Api {

    @Autowired
    private MockDataService mockDataService;

    @RequestMapping("/**")
    public ResponseEntity getMockData(HttpServletRequest request){
        String url = request.getRequestURI();
        MockDataEntity mockDataEntity = mockDataService.handleRequest(request);
        Integer latency = mockDataEntity.getLatency();
        Integer http_resp_code = mockDataEntity.getHttpRespCode();
        if(null != latency){
        try {
            Thread.sleep(mockDataEntity.getLatency());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
        HttpHeaders httpHeaders = new HttpHeaders();
        switch (mockDataEntity.getContentType()){
            case "json":
                httpHeaders.add("Content-Type", "application/json");
                break;
            case "xml":
                httpHeaders.add("Content-Type", "text/xml");
                break;
        }

        return new ResponseEntity(mockDataEntity.getRespMsg(), httpHeaders,HttpStatus.valueOf(http_resp_code));

    }
}
