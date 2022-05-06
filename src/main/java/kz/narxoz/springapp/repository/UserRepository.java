package kz.narxoz.springapp.repository;

import kz.narxoz.springapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    /** Jpa Methods */

    // find top 2 users where name starts with `name`
    List<User> findTop2ByNameStartsWith(String name);

    // find users by name and surname (?1, ?2)
    List<User> findByNameAndSurname(String name, String surname);

    // find users where email contains `email` sorted by surname (asc)
    List<User> findFirstByEmailContainingOrderBySurname(String email);

    /** Native Query */

    // find users where name starts with `A` order by surname (asc)
    @Query(value = "select * from users where name like 'A%' order by surname", nativeQuery = true)
    List<User> findAllSorted();

    // find users where id greater than `qid`
    @Query(value = "select * from users where id > :qid", nativeQuery = true)
    List<User> findByGreaterId(Long qid);

}