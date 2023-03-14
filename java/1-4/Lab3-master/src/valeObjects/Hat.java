package valeObjects;

import humans.Human;

public abstract class Hat extends ValeObject {

    private Human owner;

    /**
     * Sets owner to the hat
     * @param owner only Human
     */
    public void setOwner(Human owner) {
        this.owner = owner;
    }

    public Human getOwner() {
        return owner;
    }
}
