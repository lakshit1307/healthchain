package com.healthedge.healthchain.common.util;

public class ErrorCode {

    public final static String COMMON_FAIL = "301";

    public final static String INVALID_CONSTRAINT_POLICY= "322";
    public final static String CONSTRAINT_ID_EMPTY= "323";
    public final static String CONDITION_EMPTY= "324";


    public final static String TENANT_ID_EMPTY = "tenant.mngment.B101";
    public final static String ORG_ID_EMPTY = "tenant.mngment.B102";
    public final static String TENANT_NAME_EMPTY = "tenant.mngment.B107";
    public final static String TENANT_PARENT_EMPTY = "tenant.mngment.B106";
    public final static String CATEGORY_EMPTY = "tenant.mngment.B113";
    public final static String INVALID_TENANT = "tenant.mngment.B114";
    public final static String INVALID_ORG = "tenant.mngment.B115";
    public final static String TENANT_PREFERENCE_EXIST = "tenant.mngment.B116";
    public final static String INVALID_TENANT_PREFERENCE= "tenant.mngment.B117";
    public final static String INVALID_TENANT_PREFERENCE_ID= "tenant.mngment.B118";
    public final static String INVALID_COMPONENT_NAME= "tenant.mngment.B119";
    public final static String INVALID_COMPONENT_VALUE= "tenant.mngment.B120";
    public final static String INVALID_EMAIL= "tenant.mngment.B121";
    public final static String NOT_PARENT_LEVEL= "tenant.mngment.B122";


    public final static String USER_ID_EMPTY = "user.mngment.U100";
    public final static String USER_NAME_EMPTY = "user.mngment.U101";
    public final static String PSWD_EMPTY = "user.mngment.U102";
    public final static String FIRST_NAME_EMPTY = "user.mngment.U103";
    public final static String LAST_NAME_EMPTY= "user.mngment.U104";
    public final static String USER_NAME_ALREADY_EXIST = "user.mngment.U105";
    public final static String INVALID_USER = "user.mngment.U106";
    public final static String USER_PREFERENCE_EXIST = "user.mngment.U107";
    public final static String INVALID_USER_PREFERENCE= "user.mngment.U108";
    public final static String INVALID_OLD_PASSWORD = "user.mngment.U109";
    public final static String EMAIL_ALREADY_EXIST = "user.mngment.U110";




    public final static String ROLE_ID_EMPTY = "role.mngment.R100";
    public final static String ROLE_NAME_EMPTY = "role.mngment.R101";
    public final static String INVALID_ROLE= "role.mngment.R103";
    public final static String ROLE_NAME_ALREADY_EXIST = "role.mngment.R104";

    public final static String ROLE_GROUP_ALREADY_EXIST = "rolegroup.mngment.RG100";
    public final static String INVALID_ROLE_GROUP = "rolegroup.mngment.RG101";
    public final static String ROLE_GROUP_ID_EMPTY = "rolegroup.mngment.RG102";
    public final static String ROLE_GROUP_NAME_EMPTY = "rolegroup.mngment.RG103";


    public final static String BOOLEAN_ERROR_CREATE= "permission.mngment.p100";
    public final static String BOOLEAN_ERROR_UPDATE= "permission.mngment.p101";
    public final static String BOOLEAN_ERROR_DELETE= "permission.mngment.p103";
    public final static String BOOLEAN_ERROR_VIEW= "permission.mngment.p104";
    public final static String BOOLEAN_ERROR_EXECUTE= "permission.mngment.p105";
    public final static String RESOURCE_NAME_EMPTY= "permission.mngment.p106";
    public final static String RESOURCE_ID_EMPTY= "permission.mngment.p107";

    public final static String TEAM_NAME_REQUIRED= "team.mngment.b201";
    public final static String TEAM_DESCRIPTION_REQUIRED= "team.mngment.b202";
    public final static String TEAM_DELETE_FLAG= "team.mngment.b203";
    public final static String TEAM_USER_ID_REQUIRED= "team.mngment.b204";
    public final static String TEAM_ADMIN_FLAG= "team.mngment.b205";
    public static final String TEAM_GUID_REQUIRED = "team.mngment.b206";
    public final static String TEAM_USER_NAME_REQUIRED= "team.mngment.b207";
    public final static String TEAM_EMAIL_ID_REQUIRED= "team.mngment.b208";
    public final static String TEAM_NAME_EXIST = "team.mngment.b209";
    public final static String TEAM_NOT_EXIST = "team.mngment.b210";

    public static final String KAFKA_NOT_AVAILABLE = "kafka.service.k001";
}
