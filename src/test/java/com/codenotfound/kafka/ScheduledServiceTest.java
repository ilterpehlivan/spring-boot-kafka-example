package com.codenotfound.kafka;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class ScheduledServiceTest {

  @Test
  public void testScheduledServiceThread() {

    ScheduledExecutorService scheduler =
        Executors.newSingleThreadScheduledExecutor();
    scheduler.scheduleAtFixedRate(
        new Runnable() {
          @Override
          public void run() {
            System.out.println(Thread.currentThread().getName());
          }
        }, 1, 10, TimeUnit.MILLISECONDS);
  }
}

class TestRunnable implements Runnable {

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName());
  }
}
