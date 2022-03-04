package eus.birt.ad5.repository;

import eus.birt.ad5.domain.Ascend;
import eus.birt.ad5.domain.AscendKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AscendRepository extends JpaRepository<Ascend, AscendKey> {
    Ascend findByKeyClimberIdAndKeyRouteId(Long climberId, Long routeId);
}
