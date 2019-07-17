package com.chency.resume.service.impl;

import com.chency.resume.entities.AccessLog;
import com.chency.resume.mapper.AccessLogMapper;
import com.chency.resume.service.IAccessLogService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author chenchaoyun
 * @create 2019-07-17 22:19
 */
@Service
@Slf4j
public class AccessLogServiceImpl implements IAccessLogService {

  @Resource
  private AccessLogMapper accessLogMapper;

  @Override
  public int save(AccessLog accessLog) {
    return accessLogMapper.insert(accessLog);
  }

  @Override
  public List<AccessLog> queryAll() {
    return accessLogMapper.selectAll();
  }
}