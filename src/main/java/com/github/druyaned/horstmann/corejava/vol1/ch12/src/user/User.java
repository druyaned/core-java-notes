package com.github.druyaned.horstmann.corejava.vol1.ch12.src.user;

public class User {
    
    private String name;
    private String pass;
    private boolean isSet;

    public User() {
        isSet = false;
    }

    /**
     * Sets once user's name and password.
     * @param name user name.
     * @param pass user password.
     * @return {@code true}, if name and password aren't set, else {@code false}
     * and new name and password will not be set.
     */
    public boolean set(String name, String pass) {
        if (isSet) { return false; }
        this.name = name;
        this.pass = pass;
        return isSet = true;
    }

    public boolean isSet() {
        return isSet;
    }

    public boolean checkUser(String name, String pass) {
        return this.name.equals(name) && this.pass.equals(pass);
    }
    
}
