package fi.haagahelia.course.web;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long>  {
	
	User findByUsername(String username);

}
