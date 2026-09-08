package com.sagur.drivemate.user.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.sagur.product.common.config.ApplicationContextProvider;
import com.sagur.product.common.utils.event.UserRegistrationEvent;
import com.sagur.product.common.utils.exceptions.BusinessException;
import com.sagur.product.common.config.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@ConditionalOnProperty(name = "zoomie.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class UserRegistrationConsumer {

  @KafkaListener(topics = "${kafka.topic.user-registration-topic}", groupId = "${kafka.consumer.group-id.user-registration}")
  public void listen(@Payload String event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
      @Header(KafkaHeaders.OFFSET) long offset, Acknowledgment ack) {
    log.info("@class UserRegistrationConsumer @method listen @param topic {} partition {} offset {} event {}", topic, partition, offset, event);
    try {
      JsonUtil jsonUtil = ApplicationContextProvider.getApplicationContext().getBean(JsonUtil.class);
      UserRegistrationEvent userRegistrationEvent = jsonUtil.fromJson(event, UserRegistrationEvent.class);

      ack.acknowledge();
    } catch(Exception e) {
      log.error("@class UserRegistrationConsumer @method listen Error processing ", e);
      throw new BusinessException(e);
    }
  }

}
