package bf.orangemoney.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.orangemoney.entities.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {

}
