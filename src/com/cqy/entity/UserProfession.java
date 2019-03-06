package com.cqy.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_profession", schema = "admin", catalog = "")
public class UserProfession {
    private int id;
    private String professionId;
    private String professionName;
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
    @Column(name = "profession_id")
    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @Basic
    @Column(name = "profession_name")
    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
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
        UserProfession that = (UserProfession) o;
        return id == that.id &&
                Objects.equals(professionId, that.professionId) &&
                Objects.equals(professionName, that.professionName) &&
                Objects.equals(css, that.css);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, professionId, professionName, css);
    }
}
