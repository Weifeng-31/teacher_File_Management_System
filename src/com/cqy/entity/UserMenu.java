package com.cqy.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "menu", schema = "admin", catalog = "")
public class UserMenu {
    private int id;
    private String roleId;
    private String roleName;
    private String menuId;
    private String menuName;
    private String menuHref;
    private String menuIcon;
    private String parentMenuId;

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
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "menuId")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menuName")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "menuHref")
    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    @Basic
    @Column(name = "menuIcon")
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Basic
    @Column(name = "parentMenuId")
    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMenu that = (UserMenu) o;
        return id == that.id &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(menuId, that.menuId) &&
                Objects.equals(menuName, that.menuName) &&
                Objects.equals(menuHref, that.menuHref) &&
                Objects.equals(menuIcon, that.menuIcon) &&
                Objects.equals(parentMenuId, that.parentMenuId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, roleId, roleName, menuId, menuName, menuHref, menuIcon, parentMenuId);
    }
}
