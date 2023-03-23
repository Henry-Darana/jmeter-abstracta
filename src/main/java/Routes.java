public class Routes {

    private static final String BASE_URI = "https://pokeapi.co/api";
    private static final String VERSION = "/v2";
    private static final String BERRY = "/berry";
    private static final String BERRY_FIRMNESS = "/berry-firmness";

    public static String getBerryEndpoint(int id) {
        return BASE_URI + VERSION + BERRY + "/" + id + "/";
    }

    public static String getBerryFirmnessEndpoint(int id) {
        return BASE_URI + VERSION + BERRY_FIRMNESS + "/" + id + "/";
    }
}
