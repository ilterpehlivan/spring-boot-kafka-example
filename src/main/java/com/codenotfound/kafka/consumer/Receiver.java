package com.codenotfound.kafka.consumer;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(2);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topicPattern="DDM.measurements.*")
  public void listen(@Payload Map message,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    //    LOGGER.info("received Topic:{} -\nMessage:{}",topic,message);
    System.out.println("***Received Message***");
    System.out.println("Topic:"+topic);
    System.out.println("Message:"+message);
    System.out.println("***End Message****");
//    LOGGER.info("received Message:{}",message);
    latch.countDown();
  }


//  @KafkaListener(topics = "DDM.measurements.e3dd4bf6-5022-4af5-94a3-66afabc3bec2.69be76eb-2940-4e60-a805-198efe193349")
//  public void listenTopic(@Payload String message,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//    LOGGER.info("ListenTopic-> received Topic:{} - Message: {}",topic, message);
//    latch.countDown();
//  }
//  @KafkaListener(topics = "${kafka.topic.foo}")
//  public void receiveFoo(Foo foo) {
//    LOGGER.info("received {}", foo.toString());
//    latch.countDown();
//  }
}
