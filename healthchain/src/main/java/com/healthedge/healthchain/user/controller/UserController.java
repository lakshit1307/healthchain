package com.healthedge.healthchain.user.controller;


import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.entity.User;
import com.healthedge.healthchain.user.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import java.io.IOException;

@RestController
@RequestMapping(value = "v1.0/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "create a benefit plan")
    @PostMapping(value = "/benefitPlan")
    @ResponseBody
    public ResponseEntity<BaseResponse> createUser(@RequestBody User user) {
        BaseResponse baseResponse = userService.createUser(user);
        return new ResponseEntity(baseResponse, HttpStatus.OK);
    }

}
