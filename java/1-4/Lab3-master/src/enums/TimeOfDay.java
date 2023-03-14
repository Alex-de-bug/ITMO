package enums;

public enum TimeOfDay {
    MORNING("утро"),
    AFTERNOON("день"),
    EVENING("вечер"),
    NIGHT("ночь");

    private final String translation;

    TimeOfDay(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
