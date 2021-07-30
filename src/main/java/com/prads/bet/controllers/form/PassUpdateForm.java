package com.prads.bet.controllers.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PassUpdateForm {

    @NotNull @NotEmpty @Length(min = 8)
    private String oldPassword;

    @NotNull @NotEmpty @Length(min = 8)
    private String newPassword;

    @NotNull @NotEmpty @Length(min = 8)
    private String checkPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
