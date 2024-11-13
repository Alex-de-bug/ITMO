package beans;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Named(value = "clock")
@ApplicationScoped
public class ClockBean{
    private final static SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private String time;

    public ClockBean() {
        this.time = sdfDate.format(new Date());
    }

    public void updateTime() {
        time = sdfDate.format(new Date());
    }
}
