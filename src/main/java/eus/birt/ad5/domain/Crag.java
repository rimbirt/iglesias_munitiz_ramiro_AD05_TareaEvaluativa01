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
public class Crag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "crag", cascade = CascadeType.REMOVE)
    private Set<Route> routes;

    @Formula("SELECT COUNT(a.id) FROM route a WHERE a.crag_id=id")
    @Setter(AccessLevel.NONE)
    private int routeNumber = 0;
}
