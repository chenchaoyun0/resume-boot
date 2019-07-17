
import com.github.pagehelper.PageInfo;
import com.megvii.dzh.utils.UUIDUtils;
import java.util.Date;import com.alibaba.fastjson.JSON;
import com.chency.resume.App;
import com.chency.resume.domain.entities.AccessLog;
import com.chency.resume.service.IAccessLogService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
@Slf4j
public class LogTest {

  @Resource private IAccessLogService accessLogService;


  @Test
  @Ignore
  public void save() {
    for (int i = 0; i < 10000; i++) {
      AccessLog accessLog = new AccessLog();
      String uuid = UUIDUtils.randomUUID();
      accessLog.setIp(uuid);
      accessLog.setLocation(uuid);
      accessLog.setLongitude(uuid);
      accessLog.setLatitude(uuid);
      accessLog.setModule(uuid);
      accessLog.setPath(uuid);
      accessLog.setCreateTime(new Date());
      accessLog.setCauseTime(System.nanoTime());
      accessLog.setBrowserType(uuid);
      accessLog.setBrowserAndVersion(uuid);
      accessLog.setManufacturer(uuid);
      accessLog.setRenderingEngine(uuid);
      accessLog.setSysName(uuid);
      accessLog.setOperatingSystem(uuid);
      accessLog.setSysManufacturer(uuid);
      accessLog.setDeviceType(uuid);
      accessLog.setUserAgent(uuid);
      int save = accessLogService.save(accessLog);
      log.info("save accessLog -> {}", save);
    }
  }

  @Test
  public void queryAll() {
    List<AccessLog> accessLogList = accessLogService.queryAll();
    log.info("accessLogList {}", JSON.toJSONString(accessLogList));
  }

  @Test
  public void queryPage() {
    Integer pageNo=1;
    Integer pageSize=100;
    PageInfo<AccessLog> accessLogPageInfo = accessLogService.queryPage(pageNo, pageSize);
    log.info("accessLogPageInfo {}", JSON.toJSONString(accessLogPageInfo));
  }
}
