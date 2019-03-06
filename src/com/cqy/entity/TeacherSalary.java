package com.cqy.entity;

import org.apache.struts2.json.annotations.JSON;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "teacher_salary", schema = "admin", catalog = "")
public class TeacherSalary {
    private int id;
    private String userId;
    private String name;
    private String roleId;
    private String departId;
    private Date month;
    private Integer baseSalary;
    private Integer bonus;
    private Integer total;

    @Basic
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "depart_id")
    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    @Basic
    @Column(name = "month")
    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    @Basic
    @Column(name = "baseSalary")
    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Basic
    @Column(name = "bonus")
    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherSalary that = (TeacherSalary) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(departId, that.departId) &&
                Objects.equals(month, that.month) &&
                Objects.equals(baseSalary, that.baseSalary) &&
                Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, name, roleId, departId, month, baseSalary, bonus);
    }
}
