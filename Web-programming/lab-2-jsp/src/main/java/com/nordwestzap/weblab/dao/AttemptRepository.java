package com.nordwestzap.weblab.dao;

import com.nordwestzap.weblab.model.Attempt;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AttemptRepository {
    private List<Attempt> attempts = new ArrayList<>();

    public Attempt addAttempt(Attempt attempt) {
        attempts.add(attempt);
        return attempt;
    }

    public List<Attempt> getUserAttempts(String sessionId) {
        return attempts.stream()
                .filter(attempt -> attempt.getSessionId().equals(sessionId))
                .collect(Collectors.toList());
    }

    public void deleteUserAttempts(String sessionId) {
        attempts = attempts.stream()
                .filter(attempt -> !attempt.getSessionId().equals(sessionId))
                .collect(Collectors.toList());
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }
}
