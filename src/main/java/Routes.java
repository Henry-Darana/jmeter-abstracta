public class Routes {

    private static final String BASE_URI = "https://pokeapi.co/api";
    private static final String VERSION = "/v2";
    private static final String BERRY = "/berry";
    private static final String BERRY_FIRMNESS = "/berry-firmness";
    private static final String ABILITIES = "/ability";
    private static final String STATS = "/stat";
    private static final String COLOR = "/pokemon-color";

    private static String prefixEndpoint(String endpoint) {
        return BASE_URI + VERSION + endpoint;
    }

    public static String getBerryEndpoint(int id) {
        return prefixEndpoint(BERRY) + "/" + id + "/";
    }

    public static String getBerryFirmnessEndpoint(int id) {
        return prefixEndpoint(BERRY_FIRMNESS) + "/" + id + "/";
    }

    public static String getAbilitiesEndpoint(int id) {
        return prefixEndpoint(ABILITIES) + "/" + id + "/";
    }

    public static String getStatsEndpoint(int id) {
        return prefixEndpoint(STATS) + "/" + id + "/";
    }

    public static String getColorEndpoint(int id) {
        return prefixEndpoint(COLOR) + "/" + id + "/";
    }
}
