package rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import rental.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
	
	@Query("from User as u where u.email = :email")
	User findByEmail(@Param("email") String email);
	
	@Query("Select count(*) from User as u where u.email = :email")
	Integer checkEmail(@Param("email") String email);
}
