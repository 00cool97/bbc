package bf.orangemoney.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.orangemoney.entities.Pret;

public interface PretRepository extends JpaRepository<Pret, Integer> {

}
