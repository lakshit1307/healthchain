package com.healthedge.healthchain.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "M_USER_DETAIL")
public class UserDetails {

    @Id
    @Column(name = "USER_DETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailId;

    @NotNull
    @Column(name = "USER_ID")
    private Long userId;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String fName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lName;

    @Column(name = "MIDDLE_NAME")
    private String mName;

    @Column(name = "PH_NUMBER")
    private String phNo;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

    public UserDetails() {

    }

    public UserDetails(Long userId, String fName, String lName, String mName, String phNo, String email) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.phNo = phNo;
        this.email = email;
    }

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
