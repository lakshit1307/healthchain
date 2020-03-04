package com.healthedge.healthchain.user.service.impl;


import com.healthedge.healthchain.common.exception.BusinessException;
import com.healthedge.healthchain.common.util.Constants;
import com.healthedge.healthchain.common.util.ErrorCode;
import com.healthedge.healthchain.common.util.PasswordGenerator;
import com.healthedge.healthchain.user.dao.UserServiceDao;
import com.healthedge.healthchain.user.dto.RoleDTO;
import com.healthedge.healthchain.user.dto.UserDTO;
import com.healthedge.healthchain.user.entity.Role;
import com.healthedge.healthchain.user.entity.User;
import com.healthedge.healthchain.user.entity.UserDetails;
import com.healthedge.healthchain.user.repository.UserRepository;
import com.healthedge.healthchain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userServiceDaoImpl")
    UserServiceDao userServiceDao;

    @Autowired
    UserRepository userRepository;

    private void validateRequest(UserDTO userDTO, String action) {
        if (action.equals(Constants.CREATE_ACTION)) {

            User user = userServiceDao.getUserDetailsByUserName(userDTO.getUserName().toLowerCase());
            if (user != null && user.getUserName().toLowerCase().equals(userDTO.getUserName().toLowerCase()))
                throw BusinessException.throwBusinessException(ErrorCode.USER_NAME_ALREADY_EXIST);
        } else {
            User user = userServiceDao.getUserDetailsByUserId(userDTO.getUserId());
            if (user == null) {
                throw BusinessException.throwBusinessException(ErrorCode.INVALID_USER);
            }
        }
    }

    public void createOrModifyUser(UserDTO userDTO, String action) {
        validateRequest(userDTO, action);
        persistData(userDTO, action);
    }

    public void persistData(UserDTO userDTO, String action) {

        if (action.equals(Constants.CREATE_ACTION)) {
            if (userDTO.getPassword() == null || userDTO.getPassword().equals("")) {
                userDTO.setPassword(PasswordGenerator.get(10, false));
            }
            userDTO.setUserName(userDTO.getUserName().toLowerCase());
            userServiceDao.createUser(convertUserDtoToUser(userDTO));
        } else if (action.equals(Constants.UPDATE_ACTION)) {
            userServiceDao.updateUser(convertUserDtoToUser(userDTO));
        } else {
            userServiceDao.deleteUser(userDTO.getUserId());
        }
    }


    public UserDTO getUserDetailsByUserId(Long userId) {

        User user = userServiceDao.getUserDetailsByUserId(userId);
        if (user == null) {
            throw BusinessException.throwBusinessException(ErrorCode.INVALID_USER);
        }
        UserDTO userDTO = new UserDTO(user.getUserId(), user.getUserName(), null, user.getUserDetails().getfName(),
                user.getUserDetails().getlName(),
                user.getUserDetails().getmName(), user.getUserDetails().getPhNo(), user.getUserDetails().getEmail(),null);

        return userDTO;
    }


    public List<RoleDTO> getRolesByUser(String userName) {

        List<RoleDTO> roles = new ArrayList<>();
        List<Role> allRoles = userServiceDao.getRolesByUser(userName);
        if (allRoles != null && !allRoles.isEmpty()) {
            roles = allRoles.stream().map(p -> new RoleDTO(p.getRoleId(), p.getRoleName(), p.getRoleDesc()))
                    .collect(Collectors.toList());
        }
        return roles;
    }

    public User convertUserDtoToUser(UserDTO userDTO) {

        User user = new User(userDTO.getUserName(), userDTO.getPassword(),0);
        user.setUserId(userDTO.getUserId());
        user.setUserDetails(new UserDetails(user.getUserId(), userDTO.getfName(), userDTO.getlName(), userDTO.getmName(), userDTO.getPhNo(),
                userDTO.getEmail()));
        return user;
    }

    public UserDTO convertUserToUserDto(User user) {

        UserDTO userDTO = new UserDTO(user.getUserId(), user.getUserName(), null, user.getUserDetails().getfName(),
                user.getUserDetails().getlName(),
                user.getUserDetails().getmName(), user.getUserDetails().getPhNo(), user.getUserDetails().getEmail(),
                (user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.joining(","))));

        return userDTO;

    }

}
