package org.example.domain;

import java.io.Serializable;

public class QueryVo implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
