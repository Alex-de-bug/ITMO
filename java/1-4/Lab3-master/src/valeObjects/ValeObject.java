package valeObjects;

import abilities.AbleToFeelWeather;
import enums.Type;

public abstract class ValeObject implements AbleToFeelWeather {

    private final Type type = Type.OBJECT;

    public Type getType() {
        return type;
    }
}
