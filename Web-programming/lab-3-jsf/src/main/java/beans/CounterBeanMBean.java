package beans;

public interface CounterBeanMBean {
    int getCountHits();
    int getLoseHits();
    boolean isLastHit();
    void addHit(boolean res);
}
