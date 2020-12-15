package web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
