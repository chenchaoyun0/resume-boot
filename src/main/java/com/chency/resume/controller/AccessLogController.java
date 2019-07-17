package com.chency.resume.controller;

import com.chency.resume.domain.entities.AccessLog;
import com.chency.resume.domain.vo.AccessLogPageVo;
import com.chency.resume.domain.vo.AccessLogPageVo.DataVo;
import com.chency.resume.domain.vo.AccessLogPageVo.DataVo.ResultsVo;
import com.chency.resume.service.IAccessLogService;
import com.github.pagehelper.PageInfo;
import com.megvii.dzh.utils.DzhBeanUtils;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessLog")
public class AccessLogController {

  @Resource private IAccessLogService accessLogService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public AccessLogPageVo getPersonsAll(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam("sex") String sex) {
    PageInfo<AccessLog> accessLogPageInfo = accessLogService.queryPage(pageNo, null);
    AccessLogPageVo accessLogPageVo = new AccessLogPageVo();
    DataVo dataVo = new DataVo();
    dataVo.setCount(accessLogPageInfo.getSize());
    dataVo.setPage(accessLogPageInfo.getPages());
    dataVo.setTotal(accessLogPageInfo.getTotal());
    List<AccessLog> accessLogList = accessLogPageInfo.getList();
    dataVo.setResults(DzhBeanUtils.copyList(accessLogList, ResultsVo.class));

    accessLogPageVo.setData(dataVo);

    return accessLogPageVo;
  }
}
