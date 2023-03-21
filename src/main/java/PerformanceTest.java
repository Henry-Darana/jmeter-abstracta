import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PerformanceTest {

    @Test
    public void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("http://my.service")
                                .post("{\"name\": \"test\"}", ContentType.APPLICATION_JSON)
                ),
                //this is just to log details of each request stats
                jtlWriter("target/jtls")
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

}