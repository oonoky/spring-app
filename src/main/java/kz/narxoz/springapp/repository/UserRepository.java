package kz.narxoz.springapp.repository;

import kz.narxoz.springapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //1-task
    List<User> findByEmailEndingWith(String email);

    //2-task
    List<User> findTop2ByNameStartsWith(String task2);

    //3-task
    List<User> findBySurnameContaining(String task3);

    //native query
    //4-task
    @Query(value = "select * from users order by id asc", nativeQuery = true)
    List<User> findUsersByCustomQuery();

    //5-task
    @Query(value = "select * from users order by id desc limit 2", nativeQuery = true)
    List<User> findLastInsertedId();

    //6-task
    @Query(value = "select * from users order by name desc", nativeQuery = true)
    List<User> findUsersOrderByNameDesc();

    //7-task
    @Query( value="select *from users where email not like'%@%'", nativeQuery = true)
    List<User> findByEmailNotContaining(String email);

    //8-task
    @Query(value = "select * from users where name=surname", nativeQuery = true)
    List<User> findByNameEqualsSurname();

    //9-task
    @Query(value = "select * from users where email like '%narxoz.kz' or email like '%gmail.com' or email like '%gmail.com'", nativeQuery = true)
    List<User> findByEmailContains();

    //10-task
    @Query(value = "select distinct on (name) * from users", nativeQuery = true)
    List<User> findDistinctByName();
}