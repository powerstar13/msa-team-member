package team.msa.member.domain;

import java.util.Date;

public class Member {

     String member_id;
     String member_name;
     String member_password;
     String member_grade;
     Date insert_dt;
     Date update_dt;

    public Member(String member_id, String member_name, String member_password, String member_grade, Date insert_dt, Date update_dt) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_password = member_password;
        this.member_grade = member_grade;
        this.insert_dt = insert_dt;
        this.update_dt = update_dt;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_password() {
        return member_password;
    }

    public void setMember_password(String member_password) {
        this.member_password = member_password;
    }

    public String getMember_grade() {
        return member_grade;
    }

    public void setMember_grade(String member_grade) {
        this.member_grade = member_grade;
    }


    public Date getInsert_dt() {
        return insert_dt;
    }

    public void setInsert_dt(Date insert_dt) {
        this.insert_dt = insert_dt;
    }

    public Date getUpdate_dt() {
        return update_dt;
    }

    public void setUpdate_dt(Date update_dt) {
        this.update_dt = update_dt;
    }
}
