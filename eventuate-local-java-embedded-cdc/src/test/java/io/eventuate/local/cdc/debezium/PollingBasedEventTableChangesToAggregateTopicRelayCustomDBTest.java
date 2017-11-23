package io.eventuate.local.cdc.debezium;


import io.eventuate.local.java.jdbckafkastore.EventuateLocalConfiguration;
import io.eventuate.local.testutil.CustomDBCreator;
import io.eventuate.local.testutil.CustomDBTestConfiguration;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("EventuatePolling")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PollingBasedEventTableChangesToAggregateTopicRelayCustomDBTest.EventTableChangesToAggregateTopicRelayTestConfiguration.class)
@DirtiesContext
@IntegrationTest
public class PollingBasedEventTableChangesToAggregateTopicRelayCustomDBTest extends AbstractTopicRelayTest {

  @org.springframework.context.annotation.Configuration
  @Import({CustomDBTestConfiguration.class, EventuateLocalConfiguration.class, EventTableChangesToAggregateTopicRelayConfiguration.class})
  @EnableAutoConfiguration
  public static class EventTableChangesToAggregateTopicRelayTestConfiguration {
  }

  private boolean createCustomDb = true;

  @Autowired
  private CustomDBCreator customDBCreator;

  @Before
  public void createCustomDB() {
    if (createCustomDb) {
      customDBCreator.create();
      createCustomDb = false;
    }
  }
}
