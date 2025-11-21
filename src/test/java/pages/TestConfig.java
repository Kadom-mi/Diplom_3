package pages;

public final class TestConfig {

    private TestConfig() {
        throw new UnsupportedOperationException("Utility class - instantiation is not allowed");
    }

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public static final String MAIN_PAGE_URL = BASE_URL + "/";
    public static final String REGISTER_URL = BASE_URL + "/register";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String FORGOT_PASS_URL = BASE_URL + "/forgot-password";
    public static final String PROFILE_URL = BASE_URL + "/account/profile";
    public static final String ORDER_HISTORY_URL = BASE_URL + "/account/order-history";
}