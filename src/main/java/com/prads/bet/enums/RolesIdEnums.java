package com.prads.bet.enums;

public enum RolesIdEnums {

    ROLE_OWNER_ID(Long.valueOf(1)),
    ROLE_ADMIN_ID(Long.valueOf(2)),
    ROLE_BANK_ID(Long.valueOf(3)),
    ROLE_BETTER_ID(Long.valueOf(4)),
    ROLE_USER_ID(Long.valueOf(5));

    private Long roleId;

    RolesIdEnums(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }
}

