package com.healthedge.healthchain.common.util;

public class EntityConstants {

    public enum Entity {
        TENANT("TENANT"),
        USER("USER"),
        ROLE("ROLE"),
        ROLE_GROUP("ROLE_GROUP"),
        USR_PREF("USER-PREFERENCE"),
        TEAM("TEAM");

        private String name;

        Entity(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
