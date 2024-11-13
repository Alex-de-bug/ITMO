package beans;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.util.logging.Logger;

@Getter
@Setter
@ManagedBean(name = "counter", eager = true)
public class CounterBean implements CounterBeanMBean {
    private static final Logger logger = Logger.getLogger(CounterBean.class.getName());

    private int countHits;
    private int loseHits;
    private boolean lastHit;

    public CounterBean() {
        countHits = 0;
        loseHits = 0;
        lastHit = true;
    }

    @Override
    public void addHit(boolean res) {
        countHits++;
        if (!res) {
            loseHits++;
            if (!lastHit) {
                logger.info("Промах 2 раза подряд");
            }
        }
        lastHit = res;
    }

    @Override
    public int getCountHits() {
        return countHits;
    }

    @Override
    public int getLoseHits() {
        return loseHits;
    }

    @Override
    public boolean isLastHit() {
        return lastHit;
    }
}
