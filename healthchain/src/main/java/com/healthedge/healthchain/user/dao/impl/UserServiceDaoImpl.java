package com.healthedge.healthchain.user.dao.impl;


import com.healthedge.healthchain.common.util.CareManagerEncryptionUtil;
import com.healthedge.healthchain.user.dao.UserServiceDao;
import com.healthedge.healthchain.user.entity.Role;
import com.healthedge.healthchain.user.entity.User;
import com.healthedge.healthchain.user.entity.UserDetails;
import com.healthedge.healthchain.user.repository.UserDetailRepository;
import com.healthedge.healthchain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDaoImpl implements UserServiceDao {


    @Autowired
    UserRepository userRepository;


    @Autowired
    UserDetailRepository userDetailRepository;

    public User getUserDetailsByUserName(String userName) {
        User user = null;
        Optional<User> userOp = userRepository.findByUserName(userName);
        if (userOp.isPresent()) {
            user = userOp.get();
        }
        return user;
    }


    public void deleteUser(Long userId) {
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            User user = userOp.get();
            user.setIsDeleted(1);
            userRepository.save(user);
        }
    }

    public User getUserDetailsByUserId(Long userId) {

        User user = null;
        Optional<User> userOp = userRepository.findById(userId);
        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            user = userEntity.getIsDeleted().equals(0) ? userEntity : null;

        }
        return user;
    }

    @Transactional
    public User createUser(User user) {

        User createUser = null;
        createUser = new User(user.getUserName(), new CareManagerEncryptionUtil().encrypt(user.getPassword()),
                0);
        createUser = userRepository.save(createUser);
        createUser.setUserDetails(new UserDetails(createUser.getUserId(), user.getUserDetails().getfName(),
                user.getUserDetails().getlName(), user.getUserDetails().getmName(),
                user.getUserDetails().getPhNo(), user.getUserDetails().getEmail()));


        userDetailRepository.save(createUser.getUserDetails());
        return createUser;
    }


    public User updateUser(User user) {

        Optional<User> userOp = userRepository.findById(user.getUserId());
        if (userOp.isPresent()) {
            User userOld = userOp.get();
            userOld.getUserDetails().setfName(user.getUserDetails().getfName());
            userOld.getUserDetails().setlName(user.getUserDetails().getlName());
            userOld.getUserDetails().setmName(user.getUserDetails().getmName());
            userOld.getUserDetails().setPhNo(user.getUserDetails().getPhNo());
            userOld.getUserDetails().setEmail(user.getUserDetails().getEmail());

            user = userRepository.save(userOld);
        }
        return user;
    }


    public List<Role> getRolesByUser(String userName) {

        List<Role> roles = new ArrayList<>();
        User user = userRepository.findByUserNameWithRoles(userName, 0);
        if (user != null) {
            roles = new ArrayList<>(user.getRoles());
        }
        return roles;
    }

    public boolean isUserPresentByUserNameAndPassword(String userName,String password) {

        boolean response = false;
        Optional<User> userOp = userRepository.findByByUserNameAndPassword(userName, password);
        if (userOp.isPresent()) {
            response = true;
        }
        return response;
    }

}
