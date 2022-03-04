package eus.birt.ad5.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Ascend {

    @EmbeddedId
    private AscendKey key = new AscendKey();

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Grade grade;

    public void setClimber(Climber climber) {
       key.setClimber(climber);
    }

    public Climber getClimber() {
        return key.getClimber();
    }

    public void setRoute(Route route) {
        key.setRoute(route);
    }

    public Route getRoute() {
        return key.getRoute();
    }

}
