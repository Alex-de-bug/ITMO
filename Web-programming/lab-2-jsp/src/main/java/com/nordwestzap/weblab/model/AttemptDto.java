package com.nordwestzap.weblab.model;

public class AttemptDto {

    private final double x;
    private final double y;
    private final int r;
    private final boolean isHit;
    private final String attemptTime;
    private final long scriptDuration;

    public AttemptDto(double x, double y, int r, boolean isHit, String attemptTime, long scriptDuration) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.attemptTime = attemptTime;
        this.scriptDuration = scriptDuration;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean isHit() {
        return isHit;
    }

    public String getAttemptTime() {
        return attemptTime;
    }

    public long getScriptDuration() {
        return scriptDuration;
    }
}
