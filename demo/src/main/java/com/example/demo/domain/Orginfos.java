package com.example.demo.domain;

import java.util.Date;

public class Orginfos {
    private Integer orgid;

    private Date foundtime;

    private String message;

    private Integer collegeid;

    private String leadername;

    private String leadercontact;

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public Date getFoundtime() {
        return foundtime;
    }

    public void setFoundtime(Date foundtime) {
        this.foundtime = foundtime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(Integer collegeid) {
        this.collegeid = collegeid;
    }

    public String getLeadername() {
        return leadername;
    }

    public void setLeadername(String leadername) {
        this.leadername = leadername == null ? null : leadername.trim();
    }

    public String getLeadercontact() {
        return leadercontact;
    }

    public void setLeadercontact(String leadercontact) {
        this.leadercontact = leadercontact == null ? null : leadercontact.trim();
    }

	@Override
	public String toString() {
		return "Orginfos [orgid=" + orgid + ", foundtime=" + foundtime + ", message=" + message + ", collegeid="
				+ collegeid + ", leadername=" + leadername + ", leadercontact=" + leadercontact + "]";
	}
    
}