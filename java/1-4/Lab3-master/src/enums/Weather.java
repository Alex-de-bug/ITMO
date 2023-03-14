package enums;

public enum Weather {
    EXTREMELY_COLD("очень холодно"),
    COLD("холодно"),
    COOL("прохладно"),
    WARM("тепло"),
    HOT("жарко"),
    EXTREMELY_HOT("очень жарко");

    private final String translation;

    Weather(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
