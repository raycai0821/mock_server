package com.huifu.mock.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @author leifeng.cai
 * @description
 * @time: 2022/8/1 15:40
 **/
@Data
@TableName("mock_data")
public class MockDataEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

   private String url;

   private int ifUse;


   private Integer httpRespCode;


   private String respMsg;

   private Integer latency;


   private String contentType;

   private String reqMsg;

    @Override
    public String toString() {
        return "MockDataEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", httpRespCode=" + httpRespCode +
                ", respMsg='" + respMsg + '\'' +
                ", latency=" + latency +
                ", contentType='" + contentType + '\'' +
                ", reqMsg='" + reqMsg + '\'' +
                '}';
    }




}
