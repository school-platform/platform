package com.example.demo.domain;

import java.util.Date;

public class Org_student {
    private Integer id;

    private Integer org_Id;

    private Integer stu_Id;

    private Date jointime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrg_Id() {
        return org_Id;
    }

    public void setOrg_Id(Integer orgId) {
        this.org_Id = orgId;
    }

    public Integer getStuId() {
        return stu_Id;
    }

    public void setStuId(Integer stuId) {
        this.stu_Id = stuId;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

	@Override
	public String toString() {
		return "Org_student [id=" + id + ", orgId=" + org_Id + ", stuId=" + stu_Id + ", jointime=" + jointime + "]";
	}
    
}