package com.chency.resume.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author chenchaoyun
 * @create 2019-07-18 00:46
 */
@NoArgsConstructor
@Data
public class AccessLogPageVo implements Serializable {

  private DataVo data;

  @NoArgsConstructor
  @Data
  public static class DataVo {


    private int count;
    private int page;
    private Long total;
    private List<ResultsVo> results;

    @NoArgsConstructor
    @Data
    public static class ResultsVo {

      private Long id;

      private String ip;

      private String location;

      private String longitude;

      private String latitude;

      private String module;

      private String path;

      private Date createTime;

      private Long causeTime;

      private String browserType;

      // 浏览器名称和版本
      private String browserAndVersion;

      // 浏览器厂商
      private String manufacturer;

      // 浏览器引擎
      private String renderingEngine;

      // 系统名称
      private String sysName;

      // 产品系列
      private String operatingSystem;

      // 生成厂商
      private String sysManufacturer;

      // 设备类型
      private String deviceType;

      private String userAgent;
    }
  }
}