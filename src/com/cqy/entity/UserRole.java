package com.cqy.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role", schema = "admin", catalog = "")
public class UserRole {
    private int id;
    private String roleId;
    private String rolename;
    private String description;
    private String css;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "rolename")
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "css")
    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole that = (UserRole) o;
        return id == that.id &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(rolename, that.rolename) &&
                Objects.equals(description, that.description) &&
                Objects.equals(css, that.css);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, roleId, rolename, description, css);
    }
}
