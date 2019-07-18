package com.chency.resume.service.impl;

import com.chency.resume.domain.entities.AccessLog;
import com.chency.resume.domain.vo.AccessLogPageVo.DataVo.ResultsVo;
import com.chency.resume.mapper.AccessLogMapper;
import com.chency.resume.service.IAccessLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megvii.dzh.utils.DzhBeanUtils;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

  @Resource private AccessLogMapper accessLogMapper;

  @Value("${config.page.size:100}")
  private Integer configPageSize;

  @Override
  public int save(AccessLog accessLog) {
    return accessLogMapper.insert(accessLog);
  }

  @Override
  public List<AccessLog> queryAll() {
    return accessLogMapper.selectAll();
  }

  @Override
  public PageInfo<AccessLog> queryPage(Integer pageNo, Integer pageSize) {
    pageNo = pageNo == null ? (Integer) 1 : pageNo;
    pageSize = pageSize == null ? configPageSize : pageSize;
    PageHelper.startPage(pageNo, pageSize); // 告诉插件开始分页
    List<AccessLog> selectAll = accessLogMapper.selectAll();
    PageInfo<AccessLog> pageInfo = new PageInfo<>(selectAll);
    return pageInfo;
  }

  @Override
  public ResultsVo findById(Long itemId) {
    AccessLog accessLog = accessLogMapper.selectByPrimaryKey(itemId);
    ResultsVo resultsVo = DzhBeanUtils.copyBean(accessLog, ResultsVo.class);
    return resultsVo;
  }
}
