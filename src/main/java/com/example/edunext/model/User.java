package com.example.edunext.model;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    public String name;
    public Date creationdate;
    public long usertypeid;
    public long statusid;
    public boolean   ischangepasswordrequired;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public long getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(long usertypeid) {
        this.usertypeid = usertypeid;
    }

    public long getStatusid() {
        return statusid;
    }

    public void setStatusid(long statusid) {
        this.statusid = statusid;
    }

    public boolean isIschangepasswordrequired() {
        return ischangepasswordrequired;
    }

    public void setIschangepasswordrequired(boolean ischangepasswordrequired) {
        this.ischangepasswordrequired = ischangepasswordrequired;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
  
}