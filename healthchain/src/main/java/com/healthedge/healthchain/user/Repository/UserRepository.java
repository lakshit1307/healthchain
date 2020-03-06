package com.healthedge.healthchain.user.Repository;

import com.healthedge.healthchain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
