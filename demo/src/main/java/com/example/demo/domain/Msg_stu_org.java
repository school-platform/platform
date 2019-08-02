package com.example.demo.domain;

public class Msg_stu_org {
    private Integer id;

    private Integer stu_Id;

    private Integer msg_Id;

    private Boolean isread;

    private Integer orgId;

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

    public Integer getMsgId() {
        return msg_Id;
    }

    public void setMsgId(Integer msgId) {
        this.msg_Id = msgId;
    }

    public Boolean getIsread() {
        return isread;
    }

    public void setIsread(Boolean isread) {
        this.isread = isread;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}