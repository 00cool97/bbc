package bf.orangemoney.dao.users;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.orangemoney.users.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByLibelle(String libelle);
}
