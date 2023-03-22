import org.testng.Assert;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Pokemon {

    @Test
    public void responseTimeUnder5Seconds() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 2, httpSampler(Routes.getBerryEndpoint(1)))).run();
        Assert.assertTrue(stats.overall().sampleTimePercentile99().getSeconds() <= 5);
    }

    @Test
    public void showInGUI() throws IOException {
        testPlan(threadGroup(10, 2, httpSampler("https://pokeapi.co/api/v2/berry/1/"))).showInGui();
    }
}
