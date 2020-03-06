package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.common.util.Constants;
import com.healthedge.healthchain.user.Repository.UserRepository;
import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl {

  @Autowired
  UserRepository userRepository;

  public BaseResponse createUser(User user){
    userRepository.save(user);

    BaseResponse baseResponse = new BaseResponse(Constants.SUCCESS, "User Created");
    return  baseResponse;
  }


}
