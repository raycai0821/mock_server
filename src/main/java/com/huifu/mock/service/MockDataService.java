package com.huifu.mock.service;

import com.huifu.mock.entity.MockDataEntity;

import javax.servlet.http.HttpServletRequest;


public interface MockDataService {

    MockDataEntity handleRequest(HttpServletRequest request);




}
