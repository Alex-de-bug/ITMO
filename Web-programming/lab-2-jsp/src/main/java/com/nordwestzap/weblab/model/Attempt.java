package com.nordwestzap.weblab.model;

public class Attempt {

    private final String sessionId;
    private final double x;
    private final double y;
    private final int r;
    private final boolean isHit;
    private final String attemptTime;
    private final long scriptDuration;

    public Attempt(String sessionId, double x, double y, int r, boolean isHit, String attemptTime, long scriptDuration) {
        this.sessionId = sessionId;
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.attemptTime = attemptTime;
        this.scriptDuration = scriptDuration;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "sessionId='" + sessionId + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isHit=" + isHit +
                ", attemptTime='" + attemptTime + '\'' +
                ", scriptDuration=" + scriptDuration +
                '}';
    }

    public String getSessionId() {
        return sessionId;
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
