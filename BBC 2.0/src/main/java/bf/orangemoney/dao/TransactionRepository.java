package bf.orangemoney.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bf.orangemoney.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	@Query("SELECT t FROM Transaction t WHERE t.idOperation.libelleOperation = :x ORDER BY t.idTrans DESC")
	public List<Transaction> transactionByOperation(@Param("x")String libelleOperation);
	
	@Query("SELECT t FROM Transaction t WHERE t.idProduit.libelleProduit = :x ORDER BY t.idTrans DESC")
	public List<Transaction> transactionByProduit(@Param("x")String libelleProduit);
	
	@Query("SELECT t FROM Transaction t WHERE t.idProduit.libelleProduit = :x and t.idOperation.libelleOperation = :y ORDER BY t.idTrans DESC")
	public List<Transaction> transactionByProduitOperation(@Param("x")String libelleProduit, @Param("y")String libelleOperation);
}
