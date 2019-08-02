package com.example.demo.domain;

import java.util.Date;

public class Act_participant {
    private Integer id;

    private Integer stu_Id;

    private Integer act_Id;

    private Date posttime;

    private Date jointime;

    private Date checktime;

    private Integer scoreId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stu_Id;
    }

    public void setStuId(Integer stuId) {
        this.stu_Id = stuId;
    }

    public Integer getActId() {
        return act_Id;
    }

    public void setActId(Integer actId) {
        this.act_Id = actId;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

	@Override
	public String toString() {
		return "Act_participant [id=" + id + ", stu_Id=" + stu_Id + ", act_Id=" + act_Id + ", posttime=" + posttime
				+ ", jointime=" + jointime + ", checktime=" + checktime + ", scoreId=" + scoreId + "]";
	}
}