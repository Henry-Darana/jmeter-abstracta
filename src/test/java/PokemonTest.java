import org.testng.Assert;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PokemonTest {

    @Test
    public void responseTimeUnder5Seconds() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 2, httpSampler(Routes.getBerryEndpoint(1)))).run();
        Assert.assertTrue(stats
                .overall()
                .sampleTimePercentile99()
                .getSeconds() <= 5);
    }

    @Test(enabled = false)
    public void showInGUI() {
        testPlan(threadGroup(1, 2, httpSampler(Routes.getBerryEndpoint(1)))).showInGui();
    }

    @Test
    public void validateBerryFirmnessFromBerry() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 1,
                httpSampler(Routes.getBerryEndpoint(1)).children(jsonExtractor("FIRMNESS_PATH", "firmness.url")),
                httpSampler("${FIRMNESS_PATH}"))).run();
        Assert.assertTrue(stats
                .overall()
                .sampleTimePercentile99()
                .getSeconds() <= 5);
    }

    @Test
    public void validateConstantTimer() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(3, 1,
                httpSampler(Routes.getBerryEndpoint(1)).children(constantTimer(Duration.ofSeconds(3))))).run();
        Assert.assertTrue(stats
                .overall()
                .sampleTimePercentile99()
                .getSeconds() <= 5);
    }

    @Test
    public void validateUniformRandomTimer() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(3, 1,
                httpSampler(Routes.getBerryEndpoint(1)).children(
                        uniformRandomTimer(Duration.ofSeconds(3), Duration.ofSeconds(10))))).run();
        Assert.assertTrue(stats
                .overall()
                .sampleTimePercentile99()
                .getSeconds() <= 5);
    }
}
