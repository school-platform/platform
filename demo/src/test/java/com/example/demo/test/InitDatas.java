package com.example.demo.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Application;
import com.example.demo.dao.Act_examineMapper;
import com.example.demo.dao.Act_participantMapper;
import com.example.demo.dao.Act_scoreMapper;
import com.example.demo.dao.ActinfosMapper;
import com.example.demo.dao.ActivityMapper;
import com.example.demo.dao.CollegeMapper;
import com.example.demo.dao.CommentsMapper;
import com.example.demo.dao.MajorMapper;
import com.example.demo.dao.MessageMapper;
import com.example.demo.dao.Msg_stu_orgMapper;
import com.example.demo.dao.Org_studentMapper;
import com.example.demo.dao.StudentsMapper;
import com.example.demo.dao.StudentsinfosMapper;
import com.example.demo.dao.TeammemberMapper;
import com.example.demo.dao.tooldao.StudenttoolMapper;
import com.example.demo.domain.Act_examine;
import com.example.demo.domain.Act_participant;
import com.example.demo.domain.Act_score;
import com.example.demo.domain.Actinfos;
import com.example.demo.domain.Activity;
import com.example.demo.domain.College;
import com.example.demo.domain.Comments;
import com.example.demo.domain.Major;
import com.example.demo.domain.Message;
import com.example.demo.domain.Msg_stu_org;
import com.example.demo.domain.Org_student;
import com.example.demo.domain.Organization;
import com.example.demo.domain.Orginfos;
import com.example.demo.domain.Students;
import com.example.demo.domain.Studentsinfos;
import com.example.demo.domain.Teammember;
import com.example.demo.services.StudentsService;
import com.example.demo.tools.JsonMessage;

import net.sf.json.JSON;

import com.example.demo.dao.OrganizationMapper;
import com.example.demo.dao.OrginfosMapper;;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InitDatas {
	@Autowired
	StudentsService service;
	@Autowired
	StudentsinfosMapper studentsinfosmapper;
	@Autowired
	CollegeMapper collegeMapper;
	@Autowired
	MajorMapper majormapper;
	@Autowired
	OrganizationMapper organizationmapper;
	
	@Ignore("")
	@Test
	public void initStudent() {
		Students student = new Students();
		
		//循环生成
		for(int i=0;i<100;i++) {
			//学生id
			int id = i+1;
			student.setId(id);
			
			String studentid = "16111205"+String.format("%03d", i);
			student.setStudentid(studentid);
			
			String name = "学生"+i;
			student.setName(name);
			
			String password = studentid;
			student.setPassword(password);
			
			student.setImg("");
			student.setIslogin(false);
			
			//插入数据
			service.insertStudents(student);
		}
	}
	
	@Ignore("")
	@Test
	public void initCollege() {
		College college = new College();
		for(int i=0;i<50;i++) {
			college.setName("学院"+i);
			college.setCollegeid(String.format("%04d", i));
			collegeMapper.insert(college);
		}
	}
	
	@Ignore("")
	@Test
	public void initStudentInfos() {
		Studentsinfos infos = new Studentsinfos();
		for(int i=101;i<=200;i++) {
			infos.setStudentid(i);
			
			infos.setPhone("1771826"+String.format("%04d", i));
			
			infos.setMessage("");
			
			infos.setGrade(2017);
			
			infos.setCollegeid(i%50+1);
			
			infos.setMajorid(i%50+1);
			
			Date date = Date.valueOf("2019-10-01");
			infos.setBirthday(date);
			
			studentsinfosmapper.insert(infos);
		}
	}
	
	@Ignore("")
	@Test
	public void initMajor() {
		Major major = new Major();
		for(int i=1;i<=50;i++) {
			major.setMajorid(String.format("%04d", i));
			
			major.setName("专业"+i);
			
			major.setCollegeid(i);
			
			majormapper.insert(major);
		}
	}
	
	@Ignore
	@Test
	public void initOrgnization() {
		Organization organization = new Organization();
		for(int i=1;i<=10;i++) {
			
			organization.setImg("");
			
			organization.setName("社团"+i);
			
			organization.setOrgid("1771826"+String.format("%04d", i));
			
			organization.setPassword("1771826"+String.format("%04d", i));
			
			organizationmapper.insert(organization);
		}
	}
	
	@Autowired
	OrginfosMapper orginfosmapper;
	@Ignore
	@Test
	public void initOrginfos() {
		Orginfos orginfo = new Orginfos();
		for(int i=1;i<=10;i++) {
			
			orginfo.setCollegeid(i);
			
			Date date = Date.valueOf("2019-10-10");
			orginfo.setFoundtime(date);
			
			orginfo.setLeadercontact("17718264651");
			
			orginfo.setLeadername("SilverRice");
			
			orginfo.setMessage("");
			
			orginfo.setOrgid(i);
			
			orginfosmapper.insert(orginfo);
		}
	}
	
	@Autowired
	ActivityMapper activitymapper;
	@Ignore
	@Test
	public void initActivity() {
		Activity act = new Activity();
		for(int i=1;i<=10;i++) {
			for(int j=1;j<=5;j++) {
				
				act.setImg("");
				
				act.setName("活动"+i+"-"+j);
				
				Date date = Date.valueOf("2019-10-11");
				act.setPosttime(date);
				
				date = Date.valueOf("2019-10-12");
				act.setStarttime(date);
				
				char type = 'A';
				type = (char)((int)type+(j%3));
				act.setType(type+"");
				
				act.setOrgId(i);
				
				boolean flag = false;
				if(j%2==1) flag=true;
				act.setIsteam(flag);
				
				act.setVisible(true);
				
				activitymapper.insert(act);
			}
		}
	}
	
	@Autowired
	ActinfosMapper actinfosMapper;
	@Ignore
	@Test
	public void initActinfos() {
		Actinfos actinfo = new Actinfos();
		for(int i=51;i<=100;i++) {
			actinfo.setActId(i);
			
			Date date = Date.valueOf("2019-10-15");
			actinfo.setEndtime(date);
			
			actinfo.setMax(1000/(i%2+1));
			
			if(i%2==1) {
				actinfo.setTmax((byte)5);
				actinfo.setTmin((byte)2);
			}else {
				actinfo.setTmax((byte)0);
				actinfo.setTmin((byte)0);
			}
			
			String msg = "";
			for(int j=0;j<i;j++) {
				msg = msg+"j";
			}
			actinfo.setMessage(msg);
			
			actinfosMapper.insert(actinfo);
		}
	}
	
	@Autowired
	Act_examineMapper act_examineMapper;
	@Ignore
	@Test
	public void initActexamine() {
		Act_examine acte = new Act_examine();
		
		for(int i=51;i<=100;i++) {
			Date posttime = Date.valueOf("2019-5-10");
			acte.setPosttime(posttime);
			
			Date extime = Date.valueOf("2019-5-12");
			acte.setExtime(extime);
			
			acte.setResult("pass");
			
			acte.setActId(i);
			
			act_examineMapper.insert(acte);
		}
	}
	
	@Autowired
	Act_scoreMapper act_scoreMapper;
	@Ignore
	@Test
	public void initScore() {
		Act_score acts = new Act_score();
		for(int i=51;i<=100;i++) {
			for(int j=0;j<3;j++) {
				acts.setActId(i);
				
				acts.setLevel((j+1)+"");
				
				acts.setMax(j*10);
				
				acts.setScore((float)j);
				
				char c = 'A';
				switch(i%3) {
				case 0:
					c = 'A';
					break;
				case 1:
					c = 'B';
					break;
				case 2:
					c = 'C';
					break;
				}
				acts.setType(c+"");
				
				act_scoreMapper.insert(acts);
			}
		}
	}
	
	@Autowired
	CommentsMapper commentsMapper;
	@Ignore
	@Test
	public void initcomments() {
		Comments comments = new Comments();
		for(int i=51;i<=100;i++) {
			for(int j=0;j<i*10;j++) {
				comments.setActId(i);
				comments.setParentId(null);
				comments.setSenderId(i);
				comments.setText("testMessage"+j);
				
				java.util.Date date = new java.util.Date();
				Date time = new Date(date.getTime());
				comments.setTime(time);
				
				commentsMapper.insert(comments);
			}
		}
	}
	
	@Ignore
	@Test
	public void initChildComments() {
		Comments comments = new Comments();
		int id=1;
		for(int i=51;i<=100;i++) {
			for(int j=0;j<i*10;j++) {
				id++;
				if((j%5)!=0) continue;
				comments.setActId(i);
				comments.setParentId(id);
				comments.setSenderId(i);
				comments.setText("testMessage"+j);
				
				java.util.Date date = new java.util.Date();
				Date time = new Date(date.getTime());
				comments.setTime(time);
				
				commentsMapper.insert(comments);
			}
		}
	}
	
	@Autowired
	Act_participantMapper actm;
	@Ignore
	@Test
	public void initParticipant() {
		Act_participant actp = new Act_participant();
		for(int i=51;i<=100;i++) {
			for(int j=1;j<=20;j++) {
				actp.setStuId(j);
				
				Date posttime = Date.valueOf("2020-10-10");
				actp.setPosttime(posttime);
				
				actp.setActId(i);
				
				actm.insert(actp);
			}
		}
	}
	
	@Autowired
	TeammemberMapper teammemberMapper;
	@Ignore
	@Test
	public void initTeammember() {
		Teammember team = new Teammember();
		int teamid;
		for(int i=51;i<=100;i=i+2) {
			teamid = (i-51)*20+1;
			for(int j=1;j<=20;j++) {
				team.setTeamId(teamid);
				
				Date date = Date.valueOf("2018-10-11");
				team.setPosttime(date);
				
				team.setStuId(j+20);
				
				teammemberMapper.insert(team);
			}
			
		}
	}
	
	@Autowired
	Org_studentMapper org_studentMapper;
	@Ignore
	@Test
	public void initOrgStu() {
		Org_student os = new Org_student();
		for(int i=1;i<=10;i++) {
			for(int j=1;j<100;j++) {
				Date date = Date.valueOf("2019-10-09");
				os.setJointime(date);
				
				os.setOrg_Id(i);
				
				os.setStuId(j);
				
				org_studentMapper.insert(os);
			}
		}
	}
	
	@Autowired
	MessageMapper mm;
	@Ignore
	@Test
	public void initMessage() {
		Message msg = new Message();
		for(int i=1;i<=10;i++) {
			for(int j=1;j<=20;j++) {
				msg.setSenderId(i+"");
				msg.setText("TestMessage"+i+":"+j);
				
				Date date = Date.valueOf("2019-09-30");
				msg.setTime(date);
				
				mm.insert(msg);
			}
		}
	}
	
	@Autowired
	Msg_stu_orgMapper msoMapper;
	@Ignore
	@Test
	public void initmso() {
		Msg_stu_org mso = new Msg_stu_org();
		for(int i=1;i<=10;i++) {
			for(int j=1;j<=20;j++) {
				for(int x=1;x<100;x++) {
					mso.setIsread(false);
					mso.setMsgId(200+(i-1)*20+j);
					mso.setStuId(x);
					
					msoMapper.insert(mso);
				}
			}
		}
	}
	
	@Autowired
	StudentsMapper studentsmapper;
	@Ignore
	@Test
	public void testSelectStudentID() {
//		Students s = studentsmapper.selectByPrimaryKey(0);
//		System.out.println(s);
		
		Students s = studentsmapper.selectByStuID("16111205999");
		System.out.println(s);
	}
	
	@Autowired
	StudentsService ss;
	@Ignore
	@Test
	public void testStudentGetinfo() {
		try {
			Object obj = ss.getStudentById("16111205119");
			System.out.println(obj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(JsonMessage.error(e.getMessage()));
		}
		
	}
	
	@Ignore
	@Test
	public void testUpdate() throws Exception {
		try {
			Students student = studentsmapper.selectByStuID("1");
			Studentsinfos si = new Studentsinfos();
			si.setStudentid(student.getId());
			si.setPhone("22222222222");
			studentsinfosmapper.updateByPrimaryKeySelective(si);
		}catch(Exception e) {
			throw new Exception("更新失败");
		}
	}

	@Ignore
	@Test
	public void testSelectOGINFOS() throws Exception{
		String stu_id = "16111205000";
		System.out.println(JsonMessage.success("查询成功",ss.getOrgInfo(stu_id)));
	}
	
	@Ignore
	@Test
	public void tsetSelectOi() {
		Org_student os = org_studentMapper.selectByStuID(1).get(0);
		System.out.println(os);
	}
	
	@Ignore
	@Test
	public void testGetNew() throws Exception{
		System.out.println(JsonMessage.success("查询成功",ss.getNews("16111205000")));
		
	}
	
	@Ignore
	@Test
	public void testDelete() {
		msoMapper.deleteByStuID(101);
	}
	
	@Ignore
	@Test
	public void testReadNews() throws Exception{
		System.out.println(ss.readNews("16111205000", "201"));
	}
	
	@Ignore
	@Test
	public void initStudentScore() throws Exception{
		//对活动进行循环
		for(int i=51;i<=100;i++) {
			//对学生进行循环
			for(int j=1;j<=20;j++) {
				//根据学生id和活动id查询学生参与活动的记录
				Act_participant ap = actm.selectByStuAct(j, i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				ap.setJointime(sdf.parse("2020-11-15"));
				ap.setChecktime(sdf.parse("2020-11-16"));
				int scoreID = 3*(i-51)+j%3+1;
				ap.setScoreId(scoreID);
				System.out.println(ap);
				actm.updateByPrimaryKey(ap);
			}
		}
	}
	
	@Autowired
	StudenttoolMapper studenttoolMapper;
	@Ignore
	@Test
	public void testToolMapper() {
		System.out.println(studenttoolMapper.selectScoreInfo(1, "B"));
	}
	
	
	@Test
	public void testGetScoreInfo() throws Exception {
		service.getStuScoreInfos("16111205001");
	}
}
