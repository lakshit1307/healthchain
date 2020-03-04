package com.healthedge.healthchain.user.controller;

import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.RoleDTO;
import com.healthedge.healthchain.user.dto.UserDTO;
import com.healthedge.healthchain.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.healthedge.healthchain.common.util.Constants.*;
@RestController
@RequestMapping(value = "v1.0")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;


    @ApiOperation(value = "view a user")
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity viewUser(@PathVariable("userId") Long userId) {
        UserDTO userDTO = userService.getUserDetailsByUserId(userId);
        return new ResponseEntity(userDTO, HttpStatus.OK);


    }

    @ApiOperation(value = "get roles for logged user")
    @GetMapping(value = "/user/role")
    public ResponseEntity getRolesForUser(@RequestHeader(value = "username") String userName) {

        List<RoleDTO> response = userService.getRolesByUser(userName);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping(value = "/user")
    @ApiOperation(value = "create a user")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {


        userService.createOrModifyUser(userDTO, CREATE_ACTION);
        BaseResponse response = new BaseResponse(SUCCESS, "USER CREATED SUCCESSFULLY");

        return new ResponseEntity(response, HttpStatus.OK);

    }

    @ApiOperation(value = "update a user")
    @PostMapping(value = "/user")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {

        userService.createOrModifyUser(userDTO, UPDATE_ACTION);
        BaseResponse response = new BaseResponse(SUCCESS, "USER UPDATED SUCCESSFULLY");
        return new ResponseEntity(response, HttpStatus.OK);

    }

    @ApiOperation(value = "delete a user")
    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userService.createOrModifyUser(userDTO, DELETE_ACTION);
        BaseResponse response = new BaseResponse(SUCCESS, "USER DELETED SUCCESSFULLY");
        return new ResponseEntity(response, HttpStatus.OK);

    }


}
