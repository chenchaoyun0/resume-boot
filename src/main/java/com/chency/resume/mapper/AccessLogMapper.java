package com.chency.resume.mapper;

import com.chency.resume.domain.entities.AccessLog;
import com.chency.resume.domain.vo.SystemNamesVo;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface AccessLogMapper extends Mapper<AccessLog> {

  @Select("select sys_name as label,sys_name as value from t_access_log GROUP BY sys_name;")
  List<SystemNamesVo> selectSysNames();

}
