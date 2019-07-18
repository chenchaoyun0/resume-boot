package com.chency.resume.controller;

import com.alibaba.fastjson.JSON;
import com.chency.resume.domain.entities.AccessLog;
import com.chency.resume.domain.vo.AccessLogPageVo;
import com.chency.resume.domain.vo.AccessLogPageVo.DataVo;
import com.chency.resume.domain.vo.AccessLogPageVo.DataVo.ResultsVo;
import com.chency.resume.domain.vo.SystemNamesVo;
import com.chency.resume.service.IAccessLogService;
import com.github.pagehelper.PageInfo;
import com.megvii.dzh.utils.DzhBeanUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessLog")
@Slf4j
public class AccessLogController {

  @Resource private IAccessLogService accessLogService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public AccessLogPageVo getPersonsAll(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam("sysName") String sysName,
      @RequestParam("ip") String ip) {
    PageInfo<AccessLog> accessLogPageInfo = accessLogService.queryPage(pageNo, null);
    log.info("accessLogPageInfo --> {}", JSON.toJSONString(accessLogPageInfo));
    AccessLogPageVo accessLogPageVo = new AccessLogPageVo();
    DataVo dataVo = new DataVo();
    dataVo.setCount(accessLogPageInfo.getPageSize());
    dataVo.setPage(accessLogPageInfo.getPageNum());
    dataVo.setTotal(accessLogPageInfo.getTotal());
    List<AccessLog> accessLogList = accessLogPageInfo.getList();
    dataVo.setResults(DzhBeanUtils.copyList(accessLogList, ResultsVo.class));

    accessLogPageVo.setData(dataVo);

    return accessLogPageVo;
  }

  @RequestMapping(
      value = "/detail/{itemId}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultsVo getUserDetail(@PathVariable Long itemId) {
    AccessLog accessLog = accessLogService.findById(itemId);
    log.info("accessLog --> {}", JSON.toJSONString(accessLog));
    ResultsVo resultsVo = DzhBeanUtils.copyBean(accessLog, ResultsVo.class);
    return resultsVo;
  }

  @RequestMapping(
      value = "/update/{itemId}",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResultsVo update(@PathVariable Long itemId, @RequestBody ResultsVo resultsVo) {
    resultsVo.setId(itemId);
    AccessLog update = DzhBeanUtils.copyBean(resultsVo, AccessLog.class);
    int updateSelective = accessLogService.updateSelective(update);
    log.info("update AccessLog --> {}", updateSelective);
    AccessLog accessLog = accessLogService.findById(itemId);
    return DzhBeanUtils.copyBean(accessLog, ResultsVo.class);
  }

  @RequestMapping(
      value = "/sysNames",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<SystemNamesVo> selectSysNames() {
    List<SystemNamesVo> systemNamesVoList = accessLogService.selectSysNames();
    log.info("systemNamesVoList --> {}", JSON.toJSONString(systemNamesVoList));
    return systemNamesVoList;
  }
}
