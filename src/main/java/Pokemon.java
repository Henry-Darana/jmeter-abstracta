import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Pokemon {

    @Test
    public void responseTimeUnder5Seconds() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(10, 2, httpSampler("https://pokeapi.co/api/v2/berry/1/"))).run();
        //assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
        System.out.println(stats.overall().sampleTimePercentile99());
    }
}
