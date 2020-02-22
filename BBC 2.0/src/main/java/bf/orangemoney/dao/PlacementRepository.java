package bf.orangemoney.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.orangemoney.entities.Placement;

public interface PlacementRepository extends JpaRepository<Placement, Integer> {

}
