package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CollegeMapper;
import com.example.demo.dao.MajorMapper;
import com.example.demo.dao.MessageMapper;
import com.example.demo.dao.Msg_stu_orgMapper;
import com.example.demo.dao.OrganizationMapper;
import com.example.demo.dao.OrginfosMapper;
import com.example.demo.dao.StudentsMapper;
import com.example.demo.dao.StudentsinfosMapper;
import com.example.demo.dao.tooldao.AdministratorToolMapper;
import com.example.demo.domain.Message;
import com.example.demo.domain.Msg_stu_org;
import com.example.demo.domain.Organization;
import com.example.demo.domain.Orginfos;
import com.example.demo.domain.Students;
import com.example.demo.domain.Studentsinfos;
import com.example.demo.tools.TimeExchange;

import net.sf.json.JSONObject;

@Service
public class AdministratorService {
	@Autowired
	AdministratorToolMapper administratorToolMapper;
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	StudentsMapper studentsMapper;
	@Autowired
	StudentsinfosMapper studentsinfosMapper;
	@Autowired
	OrganizationMapper organizationMapper;
	@Autowired
	OrginfosMapper orginfosMapper;
	@Autowired
	MajorMapper majorMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	Msg_stu_orgMapper msg_stu_orgMapper;
	
	//获取全部的学院
	public ArrayList<Map<String,Object>> getAllCollege() throws Exception{
		try {
			return administratorToolMapper.getAllCollege();
		} catch (Exception e) {
			throw new Exception("学院查询失败"+e.getMessage());
		}
	}
	
	//获取学院中的专业
	public ArrayList<Map<String,Object>> getMajorByCollege(String col_id) throws Exception{
		try {
			int colid = collegeMapper.getIDByColID(col_id);
			return administratorToolMapper.getMajorByCollege(colid);
		} catch (Exception e) {
			throw new Exception("专业信息查询失败"+e.getMessage());
		}
	}
	
	//获取学生列表
	public ArrayList<Map<String,Object>> getAllStudent(int page,int num) throws Exception{
		try {
			int snum = (page-1)*num;
			return administratorToolMapper.getAllStudent(snum, num);
		} catch (Exception e) {
			throw new Exception("学生列表查询失败"+e.getMessage());
		}
	}
	
	//获取社团列表
	public ArrayList<Map<String,Object>> getAllOrgnization(int page,int num) throws Exception{
		try {
			int snum = (page-1)*num;
			return administratorToolMapper.getAllOrgnization(snum, num);
		} catch (Exception e) {
			throw new Exception("社团列表查询失败"+e.getMessage());
		}
	}
	
	//添加学生账号
	public int addStudent(JSONObject data) throws Exception{
		try {
			//组建学生类
			Students student = new Students();
			int id = studentsMapper.getLastID()+1;
			student.setId(id);
			student.setImg("");//获取头像
			student.setName(data.getString("name"));
			student.setPassword(data.getString("password"));
			student.setStudentid(data.getString("stu_id"));
			studentsMapper.insert(student);
			//组建学生信息类
			Studentsinfos stuinfo = new Studentsinfos();
			stuinfo.setStudentid(id);
			stuinfo.setBirthday(new Date(data.getString("birthday")));
			stuinfo.setCollegeid(collegeMapper.getIDByColID(data.getString("col_id")));
			stuinfo.setGrade(data.getInt("grade"));
			stuinfo.setMajorid(data.getInt("major_id"));
			stuinfo.setMessage(data.getString("message"));
			stuinfo.setPhone(data.getString("phone"));
			studentsinfosMapper.insert(stuinfo);
			return 0;
		} catch (Exception e) {
			throw new Exception("新建学生失败"+e.getMessage());
		}
	}
	
	//添加社团账号
	public int addOrgnization(JSONObject data) throws Exception{
		try {
			//组建社团类
			Organization org = new Organization();
			int id = organizationMapper.getLastID();
			org.setId(id);
			org.setImg("");//设置社团图片
			org.setName(data.getString("name"));
			org.setOrgid(data.getString("org_id"));
			org.setPassword(data.getString("password"));
			organizationMapper.insert(org);
			//组建社团信息类
			Orginfos orginfo = new Orginfos();
			orginfo.setCollegeid(collegeMapper.getIDByColID(data.getString("col_id")));
			orginfo.setFoundtime(new Date(data.getString("foundtime")));
			orginfo.setLeadercontact(data.getString("contact"));
			orginfo.setLeadername(data.getString("leadername"));
			orginfo.setMessage(data.getString("message"));
			orginfo.setOrgid(id);
			orginfosMapper.insert(orginfo);
			return 0;
		} catch (Exception e) {
			throw new Exception("社团添加失败"+e.getMessage());
		}
	}
	
	//修改学生账号
	public int modifyStudent(JSONObject data) throws Exception{
		try {
			//组建学生类
			Students student = new Students();
			int id = studentsMapper.getIDByStuID(data.getString("stu_id"));
			student.setId(id);
			student.setImg("");//获取头像
			student.setName(data.getString("name"));
			student.setPassword(data.getString("password"));
			student.setStudentid(data.getString("stu_id"));
			studentsMapper.insert(student);
			//组建学生信息类
			Studentsinfos stuinfo = new Studentsinfos();
			stuinfo.setStudentid(id);
			stuinfo.setBirthday(new Date(data.getString("birthday")));
			stuinfo.setCollegeid(collegeMapper.getIDByColID(data.getString("col_id")));
			stuinfo.setGrade(data.getInt("grade"));
			stuinfo.setMajorid(data.getInt("major_id"));
			stuinfo.setMessage(data.getString("message"));
			stuinfo.setPhone(data.getString("phone"));
			studentsinfosMapper.insert(stuinfo);
			return 0;
		} catch (Exception e) {
			throw new Exception("更新学生失败"+e.getMessage());
		}
	}
	
	//获得审核活动列表
	public ArrayList<Map<String,Object>> getAllExmaine(int page,int num) throws Exception{
		try {
			int snum = (page-1)*num;
			return administratorToolMapper.getAllExamine(snum, num);
		} catch (Exception e) {
			throw new Exception("获取活动审核列表失败"+e.getMessage());
		}
	}
	
	//审核活动
	public int examine(int id,boolean ispass,String result) throws Exception{
		try {
			Date extime = new Date();
			if(ispass) {
				administratorToolMapper.examine(extime, result, id);
			}
			else {
				administratorToolMapper.examine(extime, result, id);
			}
			return 0;
		} catch (Exception e) {
			throw new Exception("审核操作失败"+e.getMessage());
		}
	}
	
	//获取活动信息
	public Map<String,Object> getActInfo(String act_id) throws Exception{
		try {
			int actid = Integer.parseInt(act_id);
			Map<String,Object> map = administratorToolMapper.getActInfo(actid);
			map.put("score", administratorToolMapper.getScoreByAct(actid));
			return map;
		} catch (Exception e) {
			throw new Exception("活动列表查询失败"+e.getMessage());
		}
	}
	
	//发布消息
	public int publishMessage(boolean isstudent,boolean isorgan,String text) throws Exception {
		try {
			int id = messageMapper.getLastId()+1;
			Message msg = new Message();
			msg.setId(id);
			msg.setSenderId("管理员");
			msg.setText(text);
			msg.setTime(new Date());
			messageMapper.insert(msg);
			//进行消息分发
			Msg_stu_org mso = new Msg_stu_org();
			mso.setMsgId(id);
			if(isstudent) {
				ArrayList<Integer> list = studentsMapper.getAllID();
				for(int i:list) {
					mso.setStuId(i);
					msg_stu_orgMapper.insert(mso);
				}
				mso.setStuId(null);
			}
			if(isorgan) {
				ArrayList<Integer> list = organizationMapper.getAllID();
				for(int i:list) {
					mso.setOrgId(i);
					msg_stu_orgMapper.insert(mso);
				}
			}
			//分发完毕
			return 0;
		} catch (Exception e) {
			throw new Exception("消息发送失败"+e.getMessage());
		}
	}
}
