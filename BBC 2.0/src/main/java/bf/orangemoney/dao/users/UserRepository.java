package bf.orangemoney.dao.users;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.orangemoney.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
