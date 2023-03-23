public class Routes {

    private static final String BASE_URI = "https://pokeapi.co/api";
    private static final String VERSION = "/v2";
    private static final String BERRY = "/berry";
    private static final String BERRY_FIRMNESS = "/berry-firmness";
    private static final String ABILITIES = "/ability";
    private static final String STATS = "/stat";

    public static String getBerryEndpoint(int id) {
        return BASE_URI + VERSION + BERRY + "/" + id + "/";
    }

    public static String getBerryFirmnessEndpoint(int id) {
        return BASE_URI + VERSION + BERRY_FIRMNESS + "/" + id + "/";
    }

    public static String getAbilitiesEndpoint(int id) {
        return BASE_URI + VERSION + ABILITIES + "/" + id + "/";
    }

    public static String getStatsEndpoint(int id) {
        return BASE_URI + VERSION + STATS + "/" + id + "/";
    }
}
