package com.healthedge.healthchain.user.dao;


import com.healthedge.healthchain.user.entity.Role;
import com.healthedge.healthchain.user.entity.User;

import java.util.List;

public interface UserServiceDao {


    public User getUserDetailsByUserName(String userName);

    public void deleteUser(Long userId);

    public User getUserDetailsByUserId(Long userId);

    public User createUser(User user);

    public User updateUser(User user);

    List<Role> getRolesByUser(String userName);

}
