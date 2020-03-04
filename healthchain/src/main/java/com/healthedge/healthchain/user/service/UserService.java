package com.healthedge.healthchain.user.service;


import com.healthedge.healthchain.user.dto.RoleDTO;
import com.healthedge.healthchain.user.dto.UserDTO;

import java.util.List;

public interface UserService {


    public UserDTO getUserDetailsByUserId(Long userId);

    public void createOrModifyUser(UserDTO userDTO, String action);

    public List<RoleDTO> getRolesByUser(String userName);

}
