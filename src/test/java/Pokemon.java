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
        testPlan(threadGroup(1, 2, httpSampler(Routes.getBerryEndpoint(1)))).showInGui();
    }

    @Test
    public void validateBerryFirmnessFromBerry() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 1,
                httpSampler(Routes.getBerryEndpoint(1)).children(jsonExtractor("FIRMNESS_PATH", "firmness.url")),
                httpSampler("${FIRMNESS_PATH}"))).run();
        Assert.assertTrue(stats.overall().sampleTimePercentile99().getSeconds() <= 5);

    }
}
