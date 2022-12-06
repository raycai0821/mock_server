package com.huifu.mock.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huifu.mock.dao.MockDataDao;
import com.huifu.mock.entity.MockDataEntity;
import com.huifu.mock.service.MockDataService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.UUID;


/**
 * @author leifeng.cai
 * @description
 * @time: 2022/8/1 15:46
 **/

@Service
@Slf4j
public class MockDataServiceImpl implements MockDataService {

    @Autowired
    private MockDataDao mockDataDao;


    @Override
    public MockDataEntity handleRequest(HttpServletRequest request) {
        String url = request.getRequestURI();
        log.info("url为" + url);
        log.info("body请求体" + getReqBody(request));

        LambdaQueryWrapper<MockDataEntity> queryWrapper = Wrappers.lambdaQuery(MockDataEntity.class);
        queryWrapper.eq(MockDataEntity::getUrl, url);
        queryWrapper.eq(MockDataEntity::getIfUse, 1);
        MockDataEntity mockDataEntity = mockDataDao.selectOne(queryWrapper);
        if (null != mockDataEntity) {
            handleResponse(mockDataEntity);
            return mockDataEntity;
        }

        return null;
    }


    public void handleResponse(MockDataEntity mockDataEntity) {

        String respMsg = mockDataEntity.getRespMsg();
        if (respMsg.contains("_UUID")) {
            respMsg = respMsg.replaceAll("_UUID", UUID.randomUUID().toString().replaceAll("-", ""));
            log.info("UUID");
        }
        if (respMsg.contains("_AMT")) {
            respMsg = respMsg.replaceAll("_AMT", BigDecimal.valueOf(Math.random() * 100000).setScale(2, RoundingMode.HALF_UP).toString());
            log.info("AMT");
        }
        if (respMsg.contains("_ACCT")) {
            Random random = new Random();
            respMsg = respMsg.replaceAll("_ACCT", String.valueOf(Math.abs(random.nextLong())));
        }
        mockDataEntity.setRespMsg(respMsg);
    }


    /**
     * @desc 从请求李获取请求body
     */

    public String getReqBody(@NotNull HttpServletRequest request) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();


    }



    @Test
    public void test() {
        Random random = new Random();
        System.out.println(Math.abs(random.nextLong()));

    }


}
