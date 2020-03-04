package com.healthedge.healthchain.user.repository;

import com.healthedge.healthchain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT p FROM User p WHERE p.userName = (:userName) and  p.password = (:password) ")
    Optional<User> findByByUserNameAndPassword(@Param("userName") String username,
                                               @Param("password") String password);

    @Query("SELECT p FROM User p  LEFT JOIN FETCH p.roles  WHERE p.userName = (:userName) and  p.isDeleted = (:isDeleted)")
    User findByUserNameWithRoles(@Param("userName") String username, @Param("isDeleted") Integer isDeleted);

    Optional<User> findByUserName(String userName);


}
