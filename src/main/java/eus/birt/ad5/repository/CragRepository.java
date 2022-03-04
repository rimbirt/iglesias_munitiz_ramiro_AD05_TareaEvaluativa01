package eus.birt.ad5.repository;

import eus.birt.ad5.domain.Crag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CragRepository extends JpaRepository<Crag, Long> {
}
