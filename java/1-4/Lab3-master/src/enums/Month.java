package enums;

public enum Month {
    JANUARY("январь"),
    FEBRUARY("февраль"),
    MARCH("март"),
    APRIL("апрель"),
    MAY("май"),
    JUNE("июнь"),
    JULY("июль"),
    AUGUST("август"),
    SEPTEMBER("сентябрь"),
    OCTOBER("октябрь"),
    NOVEMBER("ноябрь"),
    DECEMBER("декабрь");

    private final String translation;

    Month(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
