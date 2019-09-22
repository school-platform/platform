package com.example.demo.domain;

public class Students {
    private Integer id;

    private String studentid;

    private String name;

    private String img;

    private String password;

    private Boolean islogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getIslogin() {
        return islogin;
    }

    public void setIslogin(Boolean islogin) {
        this.islogin = islogin;
    }

	@Override
	public String toString() {
		return "Students [id=" + id + ", studentid=" + studentid + ", name=" + name + ", img=" + img + ", password="
				+ password + ", islogin=" + islogin + "]";
	}
    
}