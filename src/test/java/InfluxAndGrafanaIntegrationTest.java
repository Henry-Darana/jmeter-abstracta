import org.testng.Assert;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class InfluxAndGrafanaIntegrationTest {

    @Test(enabled = false)
    public void responseTimeUnder5Seconds() throws IOException {
        String token = "GwtuliqskLSS2zqtRD2uUqBEaACLfdShYOQ2T4rzosJY0saWt48jwiu7lBfLa4XpU753cBNK2IvKx7nciNQSWg==";
        TestPlanStats stats = testPlan(threadGroup(10, Duration.ofSeconds(5), httpSampler(Routes.getBerryEndpoint(1))),
                influxDbListener("http://localhost:9002/api/v2/write?org=jmeter-influx&bucket=jmeter-influx").token(
                        token)).run();

        Assert.assertTrue(stats
                .overall()
                .sampleTimePercentile99()
                .getSeconds() <= 5);
    }
}

