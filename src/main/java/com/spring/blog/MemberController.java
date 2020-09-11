package com.spring.blog;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.member.model.MemberVO;
import com.spring.member.service.InterMemberService;

@Controller
@Component
public class MemberController {

	@Autowired
	private InterMemberService service;

	@RequestMapping(value="/login.action", method= {RequestMethod.GET})
	public String login(HttpServletRequest req) {
		
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");		
		
		req.setAttribute("email", email);
		req.setAttribute("pwd", pwd);
			
		return "member/login.tiles";
	}
	

	//LoginEnd.action
	
	@RequestMapping(value="/loginEnd.action", method= {RequestMethod.POST})
	public String loginEnd(HttpServletRequest req,HttpServletResponse res) {	

		
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");				
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", email);
		map.put("pwd",pwd);		
		
		MemberVO loginuser = service.getLoginMember(map);
		if(loginuser !=null) {
			HttpSession session = req.getSession();	
			session.setAttribute("loginuser", loginuser);	
			String gobackURL = (String)session.getAttribute("gobackurl");
			req.setAttribute("gobackURL", gobackURL);
			session.removeAttribute("gobackurl");	
		}		
	
	//	System.out.println("login!!!!!!!!!!!!!\n");
		return "member/loginEnd.tiles";				
	}
	
	
	@RequestMapping(value="/logout.action", method= {RequestMethod.GET})
	public String logout(HttpServletRequest req) {	
				
		HttpSession session = req.getSession();
		session.removeAttribute("loginuser");
		session.invalidate();
		
		
		return "member/logout.tiles";		
	}
	
	
	@RequestMapping(value="/memberform.action", method= {RequestMethod.GET})
	public String memberform(HttpServletRequest req) {
	
		return "member/memberform.tiles";
	}
	
	@RequestMapping(value="/emailCheck.action", method= {RequestMethod.GET})
	@ResponseBody
	public String emailCheck(HttpServletRequest req, HttpServletResponse res) {
		
		String email = req.getParameter("email");
	//	System.out.println(email);
		JSONObject jsonObject = new JSONObject();	

			if(email != null) {			
				int isUserEmail = service.emailDuplicateCheck(email);

				jsonObject.put("cnt",isUserEmail);				
				req.setAttribute("cnt", jsonObject);
				System.out.println(isUserEmail);
			}
			
			
			return jsonObject.toString();		

	}
	
	@RequestMapping(value="/memberRegisterEnd.action", method= {RequestMethod.POST})
	public String memberRegisterEnd(HttpServletRequest req) {
	
		String method = req.getMethod();
		
		System.out.println("memberRegisteredEnd");
		if(!"post".equalsIgnoreCase(method)) {
			
			
			String msg = "등록 실패";
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
		}
		else {
			String email = req.getParameter("email");
			String pwd = req.getParameter("pwd");
		
			MemberVO membervo = new MemberVO();
		
			membervo.setPwd(pwd);
			membervo.setEmail(email);
			
			int n = service.registerMember(membervo); 
			
			if(n>0) {
			
				HttpSession session = req.getSession();	
				session.setAttribute("n", n);	
				String gobackURL = (String)session.getAttribute("gobackurl");
				req.setAttribute("gobackURL", gobackURL);

				session.removeAttribute("gobackurl");

			}
	
		} // end of if~else-------------------------
		
		return "member/memberRegisterEnd.tiles";
	}
	
	@RequestMapping(value="emailSearch.action",method= {RequestMethod.GET})
	public String emailSearch(HttpServletRequest req) {
		
		String emailSearchWord = req.getParameter("emailSearchFrm");
				
		req.setAttribute("emailSearchWord", emailSearchWord);
		
	//	System.out.println(emailSearchWord);
		HttpSession session = req.getSession();
		
		List<MemberVO> memberList = null;
		
		if(emailSearchWord != null) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("emailSearchWord", emailSearchWord);
			
			int totalCount =0;
			
			totalCount = service.getEmailSearchTotalCount(map); 
	//		System.out.println(totalCount);
			
			memberList = service.getEmailSearch(map);
		}
		
		req.setAttribute("memberList", memberList);

		return "friend/emailSearch.tiles2";
	}
	
	//emailSearchJSON.action
	@RequestMapping(value="emailSearchJSON.action",method= {RequestMethod.GET})
	@ResponseBody
	public String cultureSearchJSON(HttpServletRequest req) {
				
		String searchword = req.getParameter("emailSearch");				
				
		req.setAttribute("emailSearchWord", searchword);
		JSONArray jonsArray = new JSONArray();
				
	//	System.out.println(searchword);
		
		if(!searchword.trim().isEmpty()) {
									
			List<String> emailList = service.getEmailSearchJSON(searchword);
					
		//	System.out.println("emailList" + emailList.size());
					
			if(emailList !=null && emailList.size() >0) {
						
				for(String emailName : emailList) {
					JSONObject jsonObj = new JSONObject();
							
					jsonObj.put("emailName" ,emailName);	
					jonsArray.put(jsonObj);
							
				}	
					
			}
					
		}// end of if(!searchword.trim().isEmpty())
				
		String str_name = jonsArray.toString();
								
		req.setAttribute("str_name", str_name);
				
		return str_name;
	}

	@RequestMapping(value="/myInfoEdit.action",method= {RequestMethod.GET})
	public String memberEdit(HttpServletRequest req, HttpServletResponse res) {
	
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		String email = req.getParameter("email");

	 	if(loginuser == null){
	 	 
	 		String msg = "로그인 하세요.";
	 		String loc = "javascript:history.back();";
	 		
	 		req.setAttribute("msg", msg);
	 		req.setAttribute("loc", loc);
	 	
	 	}
	 	else if(loginuser != null ) {
	 					
	 		email = loginuser.getEmail();

	 		session.setAttribute("email", email);
						
	 	}
	 	return "myinfo/myInfoEdit.tiles2";
}
	

	@RequestMapping(value="/myInfoEditEnd.action",method= {RequestMethod.POST})
	public String myInfoEditEnd(HttpServletRequest req,HttpServletResponse res) {
		
		
		String pwd = req.getParameter("pwd");
		
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		String email = loginuser.getEmail();
		HashMap<String,String> map = new HashMap<String,String>();
		
		
		map.put("email", email);
		map.put("pwd", pwd);

		int n = service.updateMyInfo(map); 
	
		System.out.println("email: "+email+"pwd: "+pwd);
		System.out.println("n"+n);
 		req.setAttribute("n", n);
		
		return "myinfo/myInfoEditEnd.tiles2";								
	}
	
}


		
	/*	
	// ���̵� ã��
	// idFind.action
	@RequestMapping(value="/emailFind.action",method= {RequestMethod.GET})
	public String idFind(HttpServletRequest req,HttpServletResponse res) {
			
		String method = req.getMethod();		
		req.setAttribute("method", method);			
						
		if("post".equalsIgnoreCase(method)) {
			// ���̵� ã�� ���â���� ã�� ��ư�� Ŭ������ ���
			String name = req.getParameter("name");
			String mobile = req.getParameter("mobile");
				
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("name", name);
			map.put("mobile", mobile);
				
			String email = service.getUserEmail(map); 
				
				if(email != null) {
					req.setAttribute("email", email);
				}
				else {
					req.setAttribute("", "�������� �ʽ��ϴ�.");
				}
			
			req.setAttribute("name", name);
			req.setAttribute("mobile", mobile);
			}
			
		return "emailFind.notiles";
			
	}
	
	// ��й�ȣ ã�⸦ ���� ���� userid �� email �� ������ ����ڰ� �����ϴ��� �������ִ� �� ����
	// pwdFind.action
	@RequestMapping(value="/pwdFind.action",method= {RequestMethod.GET})
	public String pwdFind(HttpServletRequest req,HttpServletResponse res) {
		
		String method = req.getMethod();
		
		req.setAttribute("method", method);
		
		if("post".equalsIgnoreCase(method)) {
			// ��й�ȣ ã�� ���â���� ã�� ��ư�� Ŭ������ ���
			String email = req.getParameter("email");
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("email", email);
			
			int n = service.isEmailExists(map); 
			
			if(n==1) {
				
				GoogleMail mail = new GoogleMail();
				
				Random rnd = new Random();
				
				String certificationCode = "";
				// certificationCode ==> "ewtyq0452029"
				
				char randchar = ' ';
				for(int i=0; i<5; i++) {
					// min ���� max ������ ������ ������ ������ ��������
					// int rndnum = rnd.nextInt(max - min + 1) + min;  
					randchar = (char)(rnd.nextInt('z' - 'a' + 1) + 'a');
					certificationCode += randchar;
				}
				
				int randnum = 0;
				for(int i=0; i<7; i++) {
					randnum = (int)(rnd.nextInt(10-0+1)+0);
					certificationCode += randnum;
				}
				
				try {
					mail.sendmail(email, certificationCode);
					req.setAttribute("certificationCode", certificationCode);
					
				} catch(Exception e) {
					e.printStackTrace();
					
					req.setAttribute("sendFailmsg", "���Ϲ߼ۿ� �����߽��ϴ�.");
					n = -1;
					
				}
		//		System.out.println(n);
			}
			
			req.setAttribute("n", n);  
			// n�� 0�̸� �������� ���� userid �Ǵ� email �� ���
			// n�� 1�̸� userid �� email �����ϸ鼭 ���Ϲ߼��� ������ ���
			// n�� -1�̸� userid �� email �����ϴµ� ���Ϲ߼��� ������ ���
			
			req.setAttribute("userid", userid);
			req.setAttribute("email", email);
		}
		return "pwdFind.notiles";
		
	}
	
	// ��ȣ�� ����ȣ�� �����ϴ� �� ����
	// pwdConfirm.action
	@RequestMapping(value="/pwdConfirm.action",method= {RequestMethod.POST})
	public String pwdConfirm(HttpServletRequest req,HttpServletResponse res) {
		
		String method = req.getMethod();
		req.setAttribute("method", method);
		
	    String email =	req.getParameter("email");
	    req.setAttribute("email", email);
	    
	    HashMap<String,String> map = new HashMap<String,String>();
	    
	    if("POST".equalsIgnoreCase(method)) {
	    	String pwd = req.getParameter("pwd");
	    	
	    	map.put("email", email);
	    	map.put("pwd", pwd);
	    	
	    	int n = 0;
	    	if(email != null && pwd != null) {
	    		n = service.updatePwdUser(map);
	    	}
	    	
	    	req.setAttribute("n", n);
	    }
		
		return "pwdConfirm.notiles";
		
	}	
	
	////////////////////////////////////////////////////////////////////////
	// ������ �α��� ������ 
	
	@RequestMapping(value="/memberlist.action", method= {RequestMethod.GET})
	public String requireAdmin_memberlist(HttpServletRequest req,HttpServletResponse res) {
			
		String currentURL = MyUtil.getCurrentURL(req);
		
		req.setAttribute("currentURL", currentURL);
		// ȸ�� ��ȸ/����/����/���� �� ���� ���������� �״�� �ӹ��� ���� �뵵��. 
			    
		HashMap<String,String> map = new HashMap<String,String>();
		
	    String searchName = req.getParameter("searchName");
	    String searchType = req.getParameter("searchType");
		String str_currentShowPageNo = req.getParameter("currentShowPageNo");
	    String str_sizePerPage = req.getParameter("sizePerPage"); 
	    if(searchType==null) {
	    	searchType="name";
	    }
	
	    if(searchName == null)   // �ʱ�ȭ��
	    {
	    	searchName = "";
	    }
	    
	    if(str_sizePerPage == null)  // �ʱ�ȭ��
	    	str_sizePerPage = "5";
	    
	    int sizePerPage = 5;
	    
	    req.setAttribute("sizePerPage", sizePerPage);
	    // 3. ��ü ������ ���� �˾ƿ���
	    // -- �� ȸ�������� �˾ƿ���
	    HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
	    
		if(loginuser == null) {
			   // �α����� ���� ���� ���
			   String msg = "�����ڷ�  �α��� �ϼž� �մϴ�.";
			   String loc = "index.action";
			   
			   req.setAttribute("msg", msg);
			   req.setAttribute("loc", loc);			   
			   
			   return "msg.notiles";
		   }
		
		
		int totalMemberCount = 0;
	    int startRno = 0;
	    int endRno = 0 ;
	
	    int currentShowPageNo = 0; // ����ڰ� ������ �ϴ� ��������ȣ    
	    
	 
	    map.put("searchName", searchName);
	    map.put("searchType", searchType);
	    
	//    System.out.println("Ȯ�ο� = "+searchName);
	 //   System.out.println("Ȯ�ο� searchType = " + searchType);
	    
	    

	//	totalMemberCount = mdao.getTotalCountWithDel(searchName); ȸ���ѿ��� ���ϴ� ���񽺴�.
		 
		totalMemberCount = service.gettotalmember(map);
		
	    req.setAttribute("totalMemberCount", totalMemberCount); 
//	    System.out.println("totalMemberCount : "+totalMemberCount);
	    
	    int totalPage = (int)Math.ceil((double)totalMemberCount / sizePerPage);   
	    
        req.setAttribute("totalPage", totalPage);
  //      System.out.println("totalPage : "+totalPage);
	    
	
			    
	    if(str_currentShowPageNo == null || str_currentShowPageNo.trim().isEmpty() ) {
	    	currentShowPageNo = 1;
	    	// �ʱ�ȭ���� ���������� ��ȣ�� 1 �������� �����Ѵ�.
	    }
	    else {
	    	try{
	    		currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
	        	// ����ڰ� �������ϴ� ��������ȣ�� Ŭ������ ��
	        	// ��������ȣ�� ������������ ������.
	        	
	        	if(currentShowPageNo < 1 || currentShowPageNo > totalPage) { // totalPage ��Ż�������� �����վ����.
	        		currentShowPageNo = 1;
	        	}
	        	
	    	}catch(NumberFormatException e) {
	    		currentShowPageNo = 1;
	    	}
	    	
	    }
	    
	    // 4. memberDao ��ü������ ����Ͽ�  jsp_member ���̺� 
	    //    ����� ��� �����͸� �ҷ��ͼ� List Ÿ���� ������ �����Ѵ�.
		   		   
	    // List<MemberVO> memberList = mdao.getSearchMembers(currentShowPageNo, sizePerPage,searchName); ȸ���� ��ΰ����ͼ� ����Ʈ<membervo> ������
	
	    
	    
	    startRno = (currentShowPageNo - 1) * sizePerPage + 1;
		endRno = startRno + sizePerPage - 1;
	    
		
	//	System.out.println("startRno = " + startRno);
	//	System.out.println("endRno = " + endRno);
		
		
	    map.put("startRno", String.valueOf(startRno));
	    map.put("endRno", String.valueOf(endRno));
	    
	    List<MemberVO> memberList = service.getMemberlist(map);
	    
	    // 5. ������ �ҷ��� �����Ͱ� ����� memberList ��  VIEW�� �������� �Ѱ��ָ� �ȴ�.
		   req.setAttribute("memberList", memberList); // ���񽺴� ���� ��� ����Ʈ���޾ƿͼ� ��ܿ��ѷ���
		   
		   req.setAttribute("currentShowPageNo", currentShowPageNo); 
		   // VIEW�� �������� ���� �������� ����ϱ� ���� �뵵(Ȯ�ο���!!)
	
		//   System.out.println("currentShowPageNo : "+currentShowPageNo);
		   
        // 6. ����¡ ó���� �������ٸ� �����Ͽ� VIEW �� �������� �Ѱ��ش�. 
		// **** �������� ����� **** //
		  int blockSize = 10;
		  
		  String url = "memberlist.action";
		  
		  String pageBar ="<ul>";
		   pageBar += MyUtil.getSearchPageBar(url, currentShowPageNo, sizePerPage, totalPage, blockSize,searchType, searchName);
		  pageBar +="</ul>";
		  req.setAttribute("pageBar", pageBar);
		  
		//  System.out.println("pageBar : " + pageBar);
		  
		return "member/memberList.tiles";
		
	}
	
	//naverRegister.action
	// ���̹� ���̵�� ȸ������
	@RequestMapping(value="/naverRegister.action", method= {RequestMethod.POST})
	public String naverRegister(HttpServletRequest req) {
	
		
		
		
		String method = req.getMethod();
		
		if(!"post".equalsIgnoreCase(method)) {
			// POST ������� �Ѿ�� ���� �ƴ϶��
			
			String msg = "���������� ��η� ���Խ��ϴ�.";
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
		}
		else {
		
			String name = req.getParameter("name");
			String userid = req.getParameter("userid");
			String pwd = req.getParameter("pwd");
			String email = req.getParameter("userid");
			
			MemberVO membervo = new MemberVO();			
	
		//	System.out.println("name : "+name);
		//	System.out.println("userid : "+userid);
		//	System.out.println("pwd : "+pwd);
		//	System.out.println("email : "+email);
			
			
			membervo.setName(name);
			membervo.setUserid(userid);
			membervo.setPwd(pwd);
			membervo.setEmail(email);		
			int n = service.NaverRegisterMember(membervo); 	
	
		
			if(n>0) {			
			
				HashMap<String,String> map = new HashMap<String,String>();
	
				map.put("userid", userid);
				map.put("pwd",pwd);
				
				HttpSession session = req.getSession(); 
				MemberVO loginuser = service.getLoginMember(map);
				session.setAttribute("loginuser", loginuser);
				
				return "main/index.tiles";
				
			
			}
			
	
		} // end of if~else-------------------------
		
		return "msg.notiles";
	}
	

		// ȸ����������
		// memberDetail.action
		@RequestMapping(value="/memberDetail.action",method= {RequestMethod.GET})
		public String memberDetail(HttpServletRequest req,HttpServletResponse res) {
			
			String idx = req.getParameter("idx");
			String goBackURL = req.getParameter("goBackURL");
			//String goBackURL = req.getParameter("goBackURL");	
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("idx", idx);
			
		//	System.out.println("idx Ȯ�ο�: " + map);
			MemberVO membervo = null;	    
		    membervo = service.getMemberOneByIdx(map);
		    
			if(membervo != null) {					
				HttpSession session = req.getSession();	
				session.setAttribute("membervo", membervo);	
				String gobackURL = (String)session.getAttribute("gobackurl");
				req.setAttribute("gobackURL", gobackURL);
				session.removeAttribute("gobackurl");
			}		
		   	return "member/memberDetail.tiles";
		}
		@RequestMapping(value="/memberDetailJSON.action",method= {RequestMethod.GET})
		public String memberDetailJSON(HttpServletRequest req,HttpServletResponse res) {
			
			String idx = req.getParameter("idx");
			String goBackURL = req.getParameter("goBackURL");
			//String goBackURL = req.getParameter("goBackURL");	
			
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("idx", idx);
			
		//	System.out.println("idx Ȯ�ο�: " + map);
			MemberVO membervo = null;	    
		    membervo = service.getMemberOneByIdx(map);
		    
			if(membervo != null) {					
				HttpSession session = req.getSession();	
				session.setAttribute("membervo", membervo);	
				String gobackURL = (String)session.getAttribute("gobackurl");
				req.setAttribute("gobackURL", gobackURL);
				session.removeAttribute("gobackurl");
	
			}	
			
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("idx", membervo.getIdx());
			jsonObj.put("email", membervo.getEmail());
			jsonObj.put("registerday", membervo.getRegisterday());
	
			req.setAttribute("jsonObject", jsonObj);
			
			
		   	return "memberDetailJSON.notiles";
		}
		
		
		
		// ȸ�� Ż�� �ϴ� ��
				// memberDelete.action
				@RequestMapping(value="/memberDelete.action",method= {RequestMethod.GET})
				public String memberDelete(HttpServletRequest req,HttpServletResponse res) {
					
					HttpSession session = req.getSession();
					MemberVO loginuser = (MemberVO)session.getAttribute("loginuser"); 

					String idx = req.getParameter("idx");
					
			    //	System.out.println("==> Ȯ�ο� goBackURL : " + goBackURL);
				// ==> Ȯ�ο� goBackURL : memberList.do?currentShowPageNo=19&sizePerPage=3&searchType=name&searchWord=%EA%B8%B8%EB%8F%99&period=-1
					
					int n = service.deleteMember(idx); 
				    
				    if(n>0) {				
						session = req.getSession();	
						session.setAttribute("n", n);	
						String gobackURL = (String)session.getAttribute("gobackurl");
						req.setAttribute("gobackURL", gobackURL);
						session.removeAttribute("gobackurl");
					} else if(n==1) {
						session.removeAttribute("loginuser");				
					}
				    
				    session.invalidate();

					return "member/memberDelete.tiles";			
				}
	
	
		//kakaoDuplicate.action
		@RequestMapping(value="/kakaoDuplicate.action",method={RequestMethod.POST})
		public String kakaoDuplicate(HttpServletRequest req) {
			
			
			//var data_form ={"userid":id,pwd:nickname,email:email};
			
			String userid = req.getParameter("userid");
			String pwd = req.getParameter("pwd");
		
			
			//System.out.println("Ȯ�ο� kakao : "+userid);
			//System.out.println("Ȯ�ο� kakao : "+pwd);
		
			
			int isUserable = service.idDuplicateCheck(userid);
			//System.out.println("īī�� : "+isUserable);
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("cnt", isUserable);
			
			req.setAttribute("cnt", jsonObj);
			
			return "kakoDuplicateJSON.notiles";
		}
		
/*		  var frm = document.kakaoFrm;
  		  frm.id.value=id;
  		  frm.email.value=email;
  		  frm.nickname.value=nickname;
  		  frm.method="POST";
  		  frm.action="kakaoRegister.action";
  		  frm.submit();		   

	*/
	/*
		//kakaoRegister.action
		@RequestMapping(value="/kakaoRegister.action",method={RequestMethod.POST})
		public String kakaoRegister(HttpServletRequest req) {
	
			
			String name = req.getParameter("nickname");
			String userid = req.getParameter("id");
			String pwd = req.getParameter("nickname");
			String email = req.getParameter("email");
			
			MemberVO membervo = new MemberVO();			
	
		//	System.out.println("name : "+name);
		//	System.out.println("userid : "+userid);
		//	System.out.println("pwd : "+pwd);
		//	System.out.println("email : "+email);
			
			
			membervo.setName(name);
			membervo.setUserid(userid);
			membervo.setPwd(pwd);
			membervo.setEmail(email);		
			int n = service.NaverRegisterMember(membervo); 	
	
		
			if(n>0) {			
			
				HashMap<String,String> map = new HashMap<String,String>();
	
				map.put("userid", userid);
				map.put("pwd",pwd);
				
				HttpSession session = req.getSession(); 
				MemberVO loginuser = service.getLoginMember(map);
				session.setAttribute("loginuser", loginuser);
				
				return "main/index.tiles";				
			
			}
			return "main/index.tiles";		
		
		}
		
		/*
		//googleRegister.action
		@RequestMapping(value="/googleRegister.action",method={RequestMethod.POST})
		public String googleRegister(HttpServletRequest req) {
	
			/*<input type="hidden" name="userid" id="userid"/>
			<input type="hidden" name="pwd" id="pwd"/>
			<input type="hidden" name="name" id="name"/>*/
/*			
			String name = req.getParameter("name");
			String userid = req.getParameter("userid");
			String pwd = req.getParameter("pwd");
			String email = req.getParameter("userid");
			
			MemberVO membervo = new MemberVO();			
	
		//	System.out.println("name : "+name);
		//	System.out.println("userid : "+userid);
		//	System.out.println("pwd : "+pwd);
		//	System.out.println("email : "+email);
			
		
			membervo.setPwd(pwd);
			membervo.setEmail(email);		
			int n = service.NaverRegisterMember(membervo); 	
	
		
			if(n>0) {			
			
				HashMap<String,String> map = new HashMap<String,String>();
	
				map.put("userid", userid);
				map.put("pwd",pwd);
				
				HttpSession session = req.getSession(); 
				MemberVO loginuser = service.getLoginMember(map);
				session.setAttribute("loginuser", loginuser);
				
				return "main/index.tiles";				
			
			}
			return "main/index.tiles";		
		
		}
*/		
		

