package com.github.pomaretta.cide.hibernate.dto;

public class UserFactory {
    
    private User user;

    public UserFactory() {
        user = new User();
    }

    public UserFactory setId(int id) {
        user.setId(id);
        return this;
    }

    public UserFactory setUsername(String username) {
        this.user.setUsername(username);
        return this;
    }

    public UserFactory setPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public User createUser() {
        return this.user;
    }

}
