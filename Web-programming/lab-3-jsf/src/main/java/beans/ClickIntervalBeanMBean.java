package beans;

import java.util.List;

public interface ClickIntervalBeanMBean {
    List<Long> getClickTimestamps();
    double getAverageInterval();
    void addClickTimestamp();
}
