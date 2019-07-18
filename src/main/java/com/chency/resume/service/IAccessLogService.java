package com.chency.resume.service;

import com.chency.resume.domain.entities.AccessLog;
import com.chency.resume.domain.vo.AccessLogPageVo.DataVo.ResultsVo;
import com.chency.resume.domain.vo.SystemNamesVo;
import com.github.pagehelper.PageInfo;
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

  PageInfo<AccessLog> queryPage(Integer pageNo, Integer pageSize);

  AccessLog findById(Long itemId);

  int updateSelective(AccessLog accessLog);

  List<SystemNamesVo> selectSysNames();

}