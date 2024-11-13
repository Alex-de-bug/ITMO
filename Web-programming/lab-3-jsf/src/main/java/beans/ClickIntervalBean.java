package beans;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Getter
@Setter
@ManagedBean(name = "interval", eager = true)
@SessionScoped
public class ClickIntervalBean implements ClickIntervalBeanMBean, MBeanRegistration {
    private static final Logger logger = Logger.getLogger(ClickIntervalBean.class.getName());

    private List<Long> clickTimestamps = new LinkedList<>();
    private double averageInterval = 0;

    @Override
    public void addClickTimestamp() {
        long currentTime = System.currentTimeMillis();
        if (!clickTimestamps.isEmpty()) {
            long lastTime = clickTimestamps.get(clickTimestamps.size() - 1);
            averageInterval = ((averageInterval * (clickTimestamps.size() - 1)) + (currentTime - lastTime)) / clickTimestamps.size();
        }
        clickTimestamps.add(currentTime);
        logger.info("New click timestamp added: " + currentTime + "; Updated average interval: " + averageInterval);
    }

    @Override
    public void postRegister(Boolean registrationDone) {
        logger.info("ClickIntervalBean registered to the MBean server: " + registrationDone);
    }

    @Override
    public void postDeregister() {
        logger.info("ClickIntervalBean deregistered from the MBean server");
    }

    @Override
    public void preDeregister() {
        logger.info("ClickIntervalBean is about to be deregistered from the MBean server");
    }

    @Override
    public ObjectName preRegister(MBeanServer server, ObjectName name) throws Exception {
        // Optionally modify the name
        return name;
    }
}
