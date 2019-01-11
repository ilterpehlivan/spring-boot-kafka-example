package com.codenotfound.kafka;

import com.codenotfound.kafka.producer.Sender;
import com.codenotfound.model.Foo;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaApplication implements CommandLineRunner {

  private static final String USAGE =
      "mvn spring-boot:run -DskipTests=true -Dspring-boot.run.arguments=--[OPTION]=[value],";

  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaApplication.class, args);
  }

  @Autowired Sender sender;

  private static final String topicPrefix = "DDM.measurements.";

  @Override
  public void run(String... args) throws Exception {
    // Define options for command line tools
    Options options = new Options();
    options.addOption("enableProducer", true, "Enable sample producer");
    CommandLine cl;
    HelpFormatter formatter = new HelpFormatter();
    formatter.setOptionComparator(null);
    try {
      for (int i = 0; i < args.length; i++) {
        System.out.println(args[i]);
      }
      cl = new BasicParser().parse(options, args);
    } catch (ParseException e) {
      System.err.println("Parsing failed.  Reason: " + e.getMessage());
      formatter.printHelp(USAGE, options);
      return;
    }
    String enableProducer = cl.getOptionValue("enableProducer");
    System.out.println("EnableProducer value->"+enableProducer);
    if ("true".equalsIgnoreCase(enableProducer)) {
      System.out.println("Enabling producer to send messages");
      for (int i = 0; i < 5; i++) {
        sender.send(topicPrefix + "testDN", getSampleObject(i));
      }
      System.out.println("Sending messages completed");
    }
  }

  private Object getSampleObject(int i) {
    Foo foo = new Foo();
    foo.setFoo("Foo-" + i);
    return foo;
  }
}
