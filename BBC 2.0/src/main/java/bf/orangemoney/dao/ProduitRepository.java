package bf.orangemoney.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bf.orangemoney.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
	public Produit findByLibelleProduit(String libelleProduit);
	
	@Query("SELECT p FROM Produit p WHERE p.etatDepotRetrait is true")
	List<Produit> produitsDepotRetraits ();
	
	@Query("SELECT p FROM Produit p WHERE p.etatRecharge is true")
	List<Produit> produitsRecharge();
}
