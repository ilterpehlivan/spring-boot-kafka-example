package com.codenotfound.kafka;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class ScalaEmbeddedKafkaTest {

  private static final String TEMPLATE_TOPIC = "templateTopic";


  @ClassRule
  public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true);

  @Test
  public void testEmbeddedKafka() throws Exception {

    String brokersAsString = embeddedKafka.getBrokersAsString();
    System.out.println(brokersAsString);
    System.out.println("Stoping kafka");
    embeddedKafka.destroy();
    System.out.println("Starting kafka again");
    embeddedKafka.before();
    brokersAsString = embeddedKafka.getBrokersAsString();
    System.out.println(brokersAsString);
  }
}
