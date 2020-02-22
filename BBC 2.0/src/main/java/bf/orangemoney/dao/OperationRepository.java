package bf.orangemoney.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bf.orangemoney.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
	//public Operation findByLibelleOperation (String libelleOperation);
	
	@Query("SELECT o FROM Operation o WHERE o.libelleOperation = :x")
	public Operation findByLibelleOperation (@Param("x")String libelleOperation);
}
