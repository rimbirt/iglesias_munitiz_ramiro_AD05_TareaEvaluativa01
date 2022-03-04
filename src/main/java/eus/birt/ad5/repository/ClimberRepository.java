package eus.birt.ad5.repository;

import eus.birt.ad5.domain.Climber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimberRepository extends JpaRepository<Climber, Long> {

    @Query("SELECT c FROM Climber c WHERE c.id NOT IN (SELECT a.key.climber.id FROM Ascend a WHERE a.key.route.id = :id)")
    List<Climber> getAllWithNoRouteAscends(@Param("id") Long routeId);
}
