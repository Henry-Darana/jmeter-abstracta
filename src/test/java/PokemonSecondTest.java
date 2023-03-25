import org.testng.Assert;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PokemonSecondTest {

    @Test
    public void assertingResponseSimple() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 2, httpSampler(Routes.getBerryEndpoint(1)).children(
                responseAssertion().containsSubstrings("cheri"),
                responseAssertion().containsSubstrings("cheri-berry"),
                responseAssertion().containsSubstrings("fire")))).run();

        Assert.assertTrue(stats
                .overall()
                .sampleTimePercentile99()
                .getSeconds() <= 2);

    }

    @Test
    public void assertingResponseComplex() throws IOException {
        TestPlanStats stats = testPlan(threadGroup(1, 2, httpSampler(Routes.getBerryEndpoint(1)).children(
                jsr223PostProcessor(s -> {
                    if ("200".equals(s.prev.getResponseCode())) {
                        s.prev.setSuccessful(true);
                    }
                })))).run();

        Assert.assertTrue(stats
                .overall()
                .sampleTimePercentile99()
                .getSeconds() <= 2);

    }

    @Test
    public void regularExpressionsExtraction() throws IOException {
        testPlan(threadGroup("Follow Up", 2, 1, httpSampler(Routes.getBerryEndpoint(1))
                        .children(jsonExtractor("BERRY_FIRMNESS", "firmness.url"),
                                jsonExtractor("FLAVOR", "flavors.flavor[0].url"),
                                jsonExtractor("ITEM", "item.url"),
                                jsonExtractor("NATURAL_GIFT_TYPE", "natural_gift_type.url")),
                httpSampler("Firmness", "${BERRY_FIRMNESS}"),
                httpSampler("Flavor", "${FLAVOR}"), httpSampler("Item", "${ITEM}"),
                httpSampler("Natural Gift Type", "${NATURAL_GIFT_TYPE}"))).run();
    }

    @Test
    public void validateIfController() throws IOException {
        testPlan(threadGroup("Follow Up", 2, 1, httpSampler(Routes.getColorEndpoint(1))
                        .children(jsonExtractor("SPECIES", "pokemon_species[0].url"),
                                jsonExtractor("NAME", "pokemon_species[0].name")),
                ifController("${NAME}==murkrow",
                        httpSampler("Species", "${SPECIES}")))).run();
    }

    @Test
    public void validateForController() throws IOException {
        String species = "SPECIES";
        String specie = "SPECIE";
        testPlan(threadGroup("Follow Up", 2, 1, httpSampler(Routes.getColorEndpoint(1))
                        .children(jsonExtractor(species, "pokemon_species[*].url")
                        ),
                forEachController(species, specie,
                        httpSampler(specie)))).run();
    }
}
