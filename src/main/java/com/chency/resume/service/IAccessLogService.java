package com.chency.resume.service;

import com.chency.resume.entities.AccessLog;
import java.util.List;

/**
 * 描述:
 *
 * @author chenchaoyun
 * @create 2019-07-17 22:18
 */
public interface IAccessLogService {

  int save(AccessLog accessLog);

  List<AccessLog> queryAll();

}