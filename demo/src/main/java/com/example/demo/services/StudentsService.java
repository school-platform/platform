package com.example.demo.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Act_participantMapper;
import com.example.demo.dao.CollegeMapper;
import com.example.demo.dao.MajorMapper;
import com.example.demo.dao.MessageMapper;
import com.example.demo.dao.Msg_stu_orgMapper;
import com.example.demo.dao.Org_studentMapper;
import com.example.demo.dao.OrganizationMapper;
import com.example.demo.dao.OrginfosMapper;
import com.example.demo.dao.ScoretypeMapper;
import com.example.demo.dao.StudentsMapper;
import com.example.demo.dao.StudentsinfosMapper;
import com.example.demo.dao.TeammemberMapper;
import com.example.demo.dao.tooldao.StudenttoolMapper;
import com.example.demo.domain.Act_participant;
import com.example.demo.domain.College;
import com.example.demo.domain.Major;
import com.example.demo.domain.Message;
import com.example.demo.domain.Msg_stu_org;
import com.example.demo.domain.Org_student;
import com.example.demo.domain.Organization;
import com.example.demo.domain.Orginfos;
import com.example.demo.domain.Scoretype;
import com.example.demo.domain.Students;
import com.example.demo.domain.Studentsinfos;
import com.example.demo.domain.Teammember;
import com.example.demo.tools.TimeExchange;


@Service
public class StudentsService {
	@Autowired
	private StudentsMapper studentsmapper;
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private MajorMapper majorMapper;
	@Autowired 
	StudentsinfosMapper studentsinfosMapper;
	@Autowired
	Org_studentMapper org_studentMapper;
	@Autowired
	OrginfosMapper orginfosMapper;
	@Autowired
	OrganizationMapper organizationMapper;
	@Autowired
	Msg_stu_orgMapper msg_stu_orgMapper;
	@Autowired 
	MessageMapper messageMapper;
	
	public void  insertStudents(Students record) {
		System.out.println(record);
		studentsmapper.insert(record);
	}
	
	//获取学生详细信息
	public StudentsInfosDetail getStudentById(String stu_id) throws Exception {
		
		//验证用户是否存在
		Students student = studentsmapper.selectByStuID(stu_id);
		if(student == null) throw new Exception("no such student");
		//根据student查询studentinfos
		Studentsinfos infos = studentsinfosMapper.selectByPrimaryKey(student.getId());
		//查询学院和专业name
		College college = collegeMapper.selectByPrimaryKey(infos.getCollegeid());
		Major major = majorMapper.selectByPrimaryKey(infos.getMajorid());
		
		//构造需要回传的数据信息
		StudentsInfosDetail sid = new StudentsInfosDetail();
		sid.setName(student.getName());
		sid.setImg(student.getImg());
		sid.setStudentid(stu_id);
		Date date = infos.getBirthday();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd号");
		sid.setBirthday(sdf.format(date));
		sid.setGrade(infos.getGrade());
		sid.setMessage(infos.getMessage());
		sid.setPhone(infos.getPhone());
		sid.setCollegename(college.getName());
		sid.setMajorname(major.getName());
		
		//返回对象
		return sid;
	}
	
	//学生详细信息类
	public class StudentsInfosDetail{
		
		String studentid;
		String name;
		String img;
		String phone;
		String birthday;
		int grade;
		String message;
		String collegename;
		String majorname;
		public String getStudentid() {
			return studentid;
		}
		public void setStudentid(String studentid) {
			this.studentid = studentid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getCollegename() {
			return collegename;
		}
		public void setCollegename(String collegename) {
			this.collegename = collegename;
		}
		public String getMajorname() {
			return majorname;
		}
		public void setMajorname(String majorname) {
			this.majorname = majorname;
		}
		@Override
		public String toString() {
			return "StudentsInfosDetail [studentid=" + studentid + ", name=" + name + ", img=" + img + ", phone="
					+ phone + ", birthday=" + birthday + ", grade=" + grade + ", message=" + message + ", collegename="
					+ collegename + ", majorname=" + majorname + "]";
		}
		
	}
	
	//学生修改手机号
	public void alterPhone(String stu_id,String phone) throws Exception{
		try {
			Students student = studentsmapper.selectByStuID(stu_id);
			Studentsinfos si = new Studentsinfos();
			si.setStudentid(student.getId());
			si.setPhone(phone);
			studentsinfosMapper.updateByPrimaryKeySelective(si);
		}catch(Exception e) {
			throw new Exception("更新失败");
		}
	}
	
	
	//学生请求社团信息
	public ArrayList<OrgInfoDetail> getOrgInfo(String stu_id) throws Exception{
		ArrayList<OrgInfoDetail> oid_list = new ArrayList<StudentsService.OrgInfoDetail>();
		OrgInfoDetail oid;
		Orginfos oi;
		Organization org;
		
		//根据学号查询所在社团列表
		try {
			//根据学号查询学生
			Students student = studentsmapper.selectByStuID(stu_id);
			ArrayList<Org_student> os_list = org_studentMapper.selectByStuID(student.getId());
			System.out.println("学生与社团关系"+os_list);
			//根据学号查社团
			for(Org_student os:os_list) { 
				//根据社团号查询社团详细信息
				System.out.println("学生与社团关系"+os.getOrg_Id());
				org = organizationMapper.selectByPrimaryKey(os.getOrg_Id());
				System.out.println(org.getId());;
				oi = orginfosMapper.selectByPrimaryKey(org.getId());
				
				//构建需要返回的数组
				oid = new OrgInfoDetail();
				oid.setName(org.getName());
				oid.setContact(oi.getLeadercontact());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
				oid.setFoundTime(sdf.format(oi.getFoundtime()));
				oid.setJoinTime(sdf.format(os.getJointime()));
				
				oid.setLeaderName(oi.getLeadername());
				oid.setCollegeName(collegeMapper.getNameByID(oi.getOrgid()));
				
				oid_list.add(oid);
			}
		}catch(Exception e) {
			throw new Exception("查询社团信息失败！");
		}
		
		return oid_list;
	}
	
	//学生查找的社团信息类
	public class OrgInfoDetail{
		String name;
		String foundTime;
		String leaderName;
		String collegeName;
		String contact;
		String joinTime;
		public String getCollegeName() {
			return collegeName;
		}
		public void setCollegeName(String collegeName) {
			this.collegeName = collegeName;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getFoundTime() {
			return foundTime;
		}
		public void setFoundTime(String foundTime) {
			this.foundTime = foundTime;
		}
		public String getLeaderName() {
			return leaderName;
		}
		public void setLeaderName(String leaderName) {
			this.leaderName = leaderName;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getJoinTime() {
			return joinTime;
		}
		public void setJoinTime(String joinTime) {
			this.joinTime = joinTime;
		}
		@Override
		public String toString() {
			return "orgInfoDetail [name=" + name + ", foundTime=" + foundTime + ", leaderName=" + leaderName
					+ ", contact=" + contact + ", joinTime=" + joinTime + "]";
		}
	}
	
	//学生请求自己的消息
	public ArrayList<NewDetail> getNews(String stu_id) throws Exception{
		try {
			//根据学号查询学生id
			Students student = studentsmapper.selectByStuID(stu_id);
			//根据学号查询关联的消息
			ArrayList<Msg_stu_org> mso_list = msg_stu_orgMapper.selectByStuID(student.getId());
			Message msg;
			NewDetail nd;
			Organization sender;
			ArrayList<NewDetail> nds = new ArrayList<StudentsService.NewDetail>();
			//构建需要返回的消息
			for(Msg_stu_org mso:mso_list){
				//根据关联信息查询消息内容
				msg = messageMapper.selectByPrimaryKey(mso.getMsgId());
				//构建返回的数列
				nd = new NewDetail();
				nd.setId(msg.getId());
				nd.setText(msg.getText()+"");
				nd.setIsread(mso.getIsread());
				
				sender = organizationMapper.selectByPrimaryKey(Integer.parseInt(msg.getSenderId()));
				nd.setSender(sender.getName());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd号");
				nd.setSendTime(sdf.format(msg.getTime()));
				
				nds.add(nd);
			}
			return nds;
		}catch (Exception e) {
			throw new Exception("信息查询失败！");
		}
	}
	
	//学生请求的消息
	public class NewDetail{
		int id;
		String sender;
		String sendTime;
		String text;
		boolean isread;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		public String getSendTime() {
			return sendTime;
		}
		public void setSendTime(String sendTime) {
			this.sendTime = sendTime;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public boolean isIsread() {
			return isread;
		}
		public void setIsread(boolean isread) {
			this.isread = isread;
		}
		@Override
		public String toString() {
			return "NewDetail [id=" + id + ", sender=" + sender + ", sendTime=" + sendTime + ", text=" + text
					+ ", isread=" + isread + "]";
		}
	}
	
	//学生删除消息
	public int deleteMessage(String stu_id,String msg_id) throws Exception{
		try {
			//根据学号查询学生id
			System.out.println(msg_id+"||"+stu_id);
			Students student = studentsmapper.selectByStuID(stu_id);
			//根据消息id选择方法进行删除
			if(msg_id.equals("")) msg_stu_orgMapper.deleteByStuID(student.getId());
			else msg_stu_orgMapper.deleteByStuIDMSGID(student.getId(), Integer.parseInt(msg_id));
			return 0;
		}catch (Exception e) {
			throw new Exception("消息删除失败！");
		}
	}
	
	//学生阅读消息（将消息的isread修改）
	public int readNews(String stu_id,String msg_id) throws Exception{
		try {
			Students student = studentsmapper.selectByStuID(stu_id);
			Msg_stu_org mso = msg_stu_orgMapper.selectByStuMsg(student.getId(), Integer.parseInt(msg_id));
			mso.setIsread(!mso.getIsread());
			msg_stu_orgMapper.updateByPrimaryKey(mso);
			return 0;
		}catch (Exception e) {
			throw new Exception("消息阅读状态操作失败！");
		}
	}
	
	//学生请求学分信息
	@Autowired
	ScoretypeMapper scoretypeMapper;
	@Autowired
	StudenttoolMapper studenttoolMapper;
	
	public ArrayList<ScoreInfo> getStuScoreInfos(String stu_id) throws Exception{
		
		//查询学分类型
		ArrayList<Scoretype> st_list = scoretypeMapper.selectAll();
		//装配ScoreInfo类
		ArrayList<ScoreInfo> si_list = new ArrayList<StudentsService.ScoreInfo>();
		try {
			for(Scoretype st:st_list) {
				//装配学分类型
				ScoreInfo si = new ScoreInfo();
				si.setType(st.getName());
				si.setSum((float) 0.00);
				//装配ScoreInfoDetail
				ArrayList<ScoreInfoDetail> sid_list = new ArrayList<StudentsService.ScoreInfoDetail>();
				ScoreInfoDetail sid = new ScoreInfoDetail();
				Students student = studentsmapper.selectByStuID(stu_id);
				List<Map<String,Object>> lm_list = studenttoolMapper.selectScoreInfo(student.getId(), si.getType());
				for(Map<String,Object> elm:lm_list) {
					sid.setAct_name((String)elm.get("act_name"));
					Date date = (Date)elm.get("act_time");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd号");
					sid.setAct_time(sdf.format(date));
					date = (Date)elm.get("checktime");
					sid.setCheck_time(sdf.format(date));
					date = (Date)elm.get("posttime");
					sid.setPost_time(sdf.format(date));
					sid.setReward((String)elm.get("level"));
					sid.setScore((float)elm.get("score"));
					System.out.println(sid);
					sid_list.add(sid);
					si.setSum(si.getSum()+sid.getScore());
				}
				si.setSi_list(sid_list);
				si_list.add(si);
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("学分信息查找失败！\n"+e.getMessage());
		}
		System.out.println(si_list);
		return si_list;
	}
	
	//学生的学分信息类
	public class ScoreInfo{
		String type;
		float sum;
		ArrayList<ScoreInfoDetail> si_list;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public float getSum() {
			return sum;
		}
		public void setSum(float sum) {
			this.sum = sum;
		}
		public ArrayList<ScoreInfoDetail> getSi_list() {
			return si_list;
		}
		public void setSi_list(ArrayList<ScoreInfoDetail> si_list) {
			this.si_list = si_list;
		}
		@Override
		public String toString() {
			return "ScoreInfo [type=" + type + ", sum=" + sum + ", si_list=" + si_list + "]";
		}
		
		
	}
	
	public class ScoreInfoDetail{
		String act_name;
		String act_time;
		String reward;
		String post_time;
		String check_time;
		float score;
		public String getAct_name() {
			return act_name;
		}
		public void setAct_name(String act_name) {
			this.act_name = act_name;
		}
		public String getAct_time() {
			return act_time;
		}
		public void setAct_time(String act_time) {
			this.act_time = act_time;
		}
		public String getReward() {
			return reward;
		}
		public void setReward(String reward) {
			this.reward = reward;
		}
		public String getPost_time() {
			return post_time;
		}
		public void setPost_time(String post_time) {
			this.post_time = post_time;
		}
		public String getCheck_time() {
			return check_time;
		}
		public void setCheck_time(String check_time) {
			this.check_time = check_time;
		}
		public float getScore() {
			return score;
		}
		public void setScore(float score) {
			this.score = score;
		}
		@Override
		public String toString() {
			return "ScoreInfoDetail [act_name=" + act_name + ", act_time=" + act_time + ", reward=" + reward
					+ ", post_time=" + post_time + ", check_time=" + check_time + ", score=" + score + "]";
		}
		
	}
	
	//学生修改密码
	public int upPass(String old_pass,String password,String stu_id) throws Exception{
		try {
			if(old_pass.equals(studenttoolMapper.getPass(stu_id)))
				return studenttoolMapper.upPass(password, stu_id);
			else throw new Exception("旧密码错误");
		} catch (Exception e) {
			throw new Exception("学生密码修改失败"+e.getMessage());
		}
	}
	
	//学生获取活动参加信息
	public ArrayList<Map<String,Object>> getPartInfo(String act_id,String stu_id)throws Exception{
		try {
			ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			//判断活动是否是团队活动
			if(studenttoolMapper.isTeamAct(act_id)) {
				list.add(studenttoolMapper.getMyPartInfo(act_id, stu_id));
			}else {
				list.add(studenttoolMapper.getMyPartInfoInTeam(act_id, stu_id));
			}
			//判断是否找到参与信息
			if(list.isEmpty()) {
				throw new Exception("notfound");
			}else {
				ArrayList<String> names = new ArrayList<String>();
				names.add("posttime");
				names.add("starttime");
				TimeExchange.changeTimeDate(list, names);
			}
			return list;
		} catch (Exception e) {
			throw new Exception("参与信息获取失败"+e.getMessage());
		}
	}
	
	@Autowired
	Act_participantMapper act_participantMapper;
	//学生进行报名与创建团队
	public int addParticipant(String act_id,String stu_id) throws Exception{
		try {
			//构造报名信息对象
			Act_participant ap = new Act_participant();
			ap.setActId(Integer.parseInt(act_id));
			ap.setStuId(studenttoolMapper.getIDByStudentID(stu_id));
			ap.setIspass(false);
			
			Date posttime = new Date();
			ap.setPosttime(posttime);
			
			return act_participantMapper.insert(ap);
		} catch (Exception e) {
			throw new Exception("报名失败"+e.getMessage());
		}
	}
	
	@Autowired
	TeammemberMapper teammemberMapper;
	//申请入团
	public int joinTeam(String leader_id,String stu_id,String act_id) throws Exception{
		try {
			//构建队员对象
			Teammember tm = new Teammember();
			Date posttime = new Date();
			tm.setPosttime(posttime);
			tm.setStuId(studenttoolMapper.getIDByStudentID(stu_id));
			Map<String,Object> map = studenttoolMapper.getMyPartInfo(act_id, leader_id);
			tm.setTeamId((int)map.get("id"));
			return teammemberMapper.insert(tm);
		} catch (Exception e) {
			throw new Exception("团队参加申请失败"+e.getMessage());
		}
	}
	
	
	//同意入团
	public int confirmTm(String stu_id,String tm_id,String act_id) throws Exception {
		try {
			//查询队长的报名信息
			Map<String,Object> leaderMap = studenttoolMapper.getLeaderMap(studenttoolMapper.getIDByStudentID(stu_id), Integer.parseInt(act_id));
			//修改队员的jointime即为同意入团
			Date jointime = new Date();
			return studenttoolMapper.confirmTm(jointime, Integer.parseInt(act_id), studenttoolMapper.getIDByStudentID(tm_id));
		} catch (Exception e) {
			throw new Exception("队员操作失败"+e.getMessage());
		}
	}
	
	//拒绝入团
	public int cancel(String stu_id,String mem_id,String act_id) throws Exception{
		try {
			//查询队长报名信息
			Map<String,Object> leaderMap = studenttoolMapper.getLeaderMap(studenttoolMapper.getIDByStudentID(stu_id), Integer.parseInt(act_id));
			//删除对应队员行
			return studenttoolMapper.cancel((int)leaderMap.get("id"),studenttoolMapper.getIDByStudentID(mem_id));
		} catch (Exception e) {
			throw new Exception("操作失败"+e.getMessage());
		}
	}
	
	//获取最新活动
	public ArrayList<Map<String,Object>> getNewAct(int now,int num)throws Exception{
		try {
			int snum = now;
			ArrayList<Map<String,Object>> list = studenttoolMapper.getNewAct(snum, num);
			ArrayList<String> names = new ArrayList<String>();
			names.add("starttime");
			names.add("endtime");
			return TimeExchange.changeTimeDate(list, names);
		} catch (Exception e) {
			throw new Exception("活动信息获取失败"+e.getMessage());
		}
	}
	
	//获取热门活动
	public ArrayList<Map<String,Object>> getHotAct(int now,int num) throws Exception{
		try {
//			int snum = (page-1)*num;
			ArrayList<Map<String,Object>> list = studenttoolMapper.getHotAct(now, num);
			ArrayList<String> names = new ArrayList<String>();
			names.add("starttime");
			names.add("endtime");
			return TimeExchange.changeTimeDate(list, names);
		} catch (Exception e) {
			throw new Exception("活动获取失败");
		}
	}
	
	//获取我的活动
	public ArrayList<Map<String,Object>> getMyAct(String stu_id,int now ,int count) throws Exception{
		try {
			int stuid = studenttoolMapper.getIDByStudentID(stu_id);
			ArrayList<Map<String,Object>> list = studenttoolMapper.getMyAct(stuid,now,count);
			ArrayList<String> names = new ArrayList<String>();
			names.add("starttime");
			names.add("endtime");
			return TimeExchange.changeTimeDate(list, names);
		} catch (Exception e) {
			throw new Exception("我的活动列表获取失败"+e.getMessage());
		}
	}
}
