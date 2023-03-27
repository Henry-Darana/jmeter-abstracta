import org.testng.Assert;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class CircleCITest {

    @Test
    public void validateBerryResponseTimeUnder5Seconds() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 2, httpSampler(Routes.getBerryEndpoint(1)))).run();
        Assert.assertTrue(stats.overall().sampleTimePercentile99().getSeconds() <= 5);
    }

    @Test
    public void validateBerryFirmnessResponseTimeUnder5Seconds() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 1,
                httpSampler(Routes.getBerryEndpoint(1)).children(jsonExtractor("FIRMNESS_PATH", "firmness.url")),
                httpSampler("${FIRMNESS_PATH}"))).run();
        Assert.assertTrue(stats.overall().sampleTimePercentile99().getSeconds() <= 5);
    }

    @Test
    public void validateProperEndpointsInBerry() throws IOException {
        testPlan(threadGroup("Follow Up", 2, 1, httpSampler(Routes.getBerryEndpoint(1))
                        .children(jsonExtractor("BERRY_FIRMNESS", "firmness.url"),
                                jsonExtractor("FLAVOR", "flavors.flavor[0].url"),
                                jsonExtractor("ITEM", "item.url"),
                                jsonExtractor("NATURAL_GIFT_TYPE", "natural_gift_type.url")),
                httpSampler("Firmness", "${BERRY_FIRMNESS}"),
                httpSampler("Flavor", "${FLAVOR}"), httpSampler("Item", "${ITEM}"),
                httpSampler("Natural Gift Type", "${NATURAL_GIFT_TYPE}"))).run();
    }
}
