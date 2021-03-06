package com.chency.resume;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.chency.resume.mapper")
@Slf4j
public class App extends SpringBootServletInitializer
    implements ApplicationListener<ContextRefreshedEvent> {

  public static void main(String args[]) {
    SpringApplication.run(App.class, args);
    log.info(">>>>>>>>>>>>>>>>>>>>>> resume-boot 启动成功! <<<<<<<<<<<<<<<<<<<<<<<<<");
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info("--->开机服务执行的操作....");
    try {

    } catch (Exception e) {
      log.error("onApplicationEvent error", e);
    }
  }
}
