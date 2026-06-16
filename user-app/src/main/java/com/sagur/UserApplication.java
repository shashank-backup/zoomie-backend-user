package com.sagur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

//@ComponentScan(basePackages = "com.sagur")
@SpringBootApplication(scanBasePackages = "com.sagur")
@EnableAsync
//    Redis Cache for OTPs, active sessions, driver availability, fare lookup, promo code validation.
//   Caffeine Cache for local in-memory metadata (city configuration, vehicle types, surcharge rules).
//@EnableCaching // default cacheManager must define in spring boot4 else it will throw runtime issue
@EnableFeignClients
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ServletComponentScan(basePackages = "com.sagur")   // This annotation is used to scan for @WebFilter, @WebListener, and @WebServlet components.
@EnableJpaRepositories(basePackages = "com.sagur")
@EntityScan(basePackages = "com.sagur")
@EnableKafka
public class UserApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }

}