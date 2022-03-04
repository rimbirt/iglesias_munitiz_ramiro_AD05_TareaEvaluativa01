package eus.birt.ad5.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Climber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String lastName;

    @OneToMany(mappedBy = "key.climber", cascade = CascadeType.REMOVE)
    private Set<Ascend> ascends;

    @Formula("SELECT COUNT(a.climber_id) FROM ascend a WHERE a.climber_id=id")
    @Setter(AccessLevel.NONE)
    private int ascendNumber = 0;
}
