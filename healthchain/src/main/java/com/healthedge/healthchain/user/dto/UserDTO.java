package com.healthedge.healthchain.user.dto;

public class UserDTO  {


    private Long userId;
    private String userName;
    private String oldPassword;
    private String password;
    private String fName;
    private String lName;
    private String mName;
    private String phNo;
    private String email;

    private String roles;

    public UserDTO() {

    }

    public UserDTO(Long userId, String userName, String password, String fName, String lName, String mName, String phNo,
                   String email,
                   String roles) {

        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.phNo = phNo;
        this.email = email;
        this.roles = roles;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }


}

