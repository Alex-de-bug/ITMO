package enums;

public enum WaterColour {
    GREY("серая"),
    BLUE("голубая"),
    GREEN("зеленая"),
    ORANGE("оранжевый"),
    TRANSPARENT("прозрачная");

    private final String translation;

    WaterColour(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }
}
