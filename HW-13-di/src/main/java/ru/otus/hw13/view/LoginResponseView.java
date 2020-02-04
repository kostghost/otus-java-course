package ru.otus.hw13.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseView {

    @JsonProperty("loginSuccessfull")
    private boolean loginSuccessfull;

    @JsonProperty("accessAllowed")
    private boolean accessAllowed;

    public LoginResponseView(boolean accessAllowed, boolean loginSuccessfull) {
        this.accessAllowed = accessAllowed;
        this.loginSuccessfull = loginSuccessfull;
    }
}
