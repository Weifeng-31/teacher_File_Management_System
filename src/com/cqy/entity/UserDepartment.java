package com.cqy.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_department", schema = "admin", catalog = "")
public class UserDepartment {
    private int id;
    private String departmentId;
    private String departmentName;
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
    @Column(name = "department_id")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "departmentName")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
        UserDepartment that = (UserDepartment) o;
        return id == that.id &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(css, that.css);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, departmentId, departmentName, css);
    }
}
