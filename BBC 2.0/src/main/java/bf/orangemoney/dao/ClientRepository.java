package bf.orangemoney.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bf.orangemoney.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	//Retrouver un client par son numero
	public Client findByPhone(String phone);
	
	//Retrouver un client par son numero de CNIB
	public Client findByNumCNIB(String numCNIB);
	
	//Liste des credits
	@Query("SELECT c FROM Client c WHERE c.montantRest != 0.00")
	public List<Client> listClientCredit ();
	
	@Query("SELECT c FROM Client c WHERE c.montantCredit != 0.00")
	public List<Client> bilanCredits ();
	
	@Query("SELECT c FROM Client c WHERE c.montantPlace != 0.00")
	public List<Client> listClientPlacement ();
	
	@Query("SELECT c FROM Client c WHERE c.montantRetire < c.montantPlace")
	public List<Client> listRetraitPlacement ();
}
