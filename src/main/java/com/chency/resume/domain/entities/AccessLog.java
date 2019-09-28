package com.chency.resume.domain.entities;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Entity
@Table(name = "t_access_log")
@Data
public class AccessLog{

    @Id
    private Long id;

    private String ip;

    private String location;

    private String longitude;

    private String latitude;

    private String module;


    private Date createTime;

    private Long causeTime;

    private String path;

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