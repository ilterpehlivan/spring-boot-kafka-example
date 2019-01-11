package com.codenotfound.kafka;

import static org.junit.Assert.assertEquals;

import com.salesforce.kafka.test.KafkaBroker;
import com.salesforce.kafka.test.KafkaTestUtils;
import com.salesforce.kafka.test.junit4.SharedKafkaTestResource;
import java.util.List;
import org.apache.kafka.common.Node;
import org.junit.ClassRule;
import org.junit.Test;

public class SaleforgeEmbeddedTest {

  @ClassRule
  public static final SharedKafkaTestResource sharedKafkaTestResource = new SharedKafkaTestResource();

  @Test
  public void testStartStop() throws Exception {


    // Describe the cluster
    List<Node> nodes = getKafkaTestUtils().describeClusterNodes();

    // We should only have 1 node now, and it should not include broker Id 2.
    assertEquals("Should have two entries", 1, nodes.size());

    KafkaBroker broker = sharedKafkaTestResource.getKafkaBrokers().getBrokerById(1);
    broker.stop();

    // Describe the cluster
    nodes = getKafkaTestUtils().describeClusterNodes();

    // We should only have 1 node now, and it should not include broker Id 2.
    assertEquals("Should have 0 entries", 0, nodes.size());
  }

  /**
   * Simple accessor.
   */
  private KafkaTestUtils getKafkaTestUtils() {
    return sharedKafkaTestResource.getKafkaTestUtils();
  }
}
