package com.spring.blog;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.friend.model.FriendVO;
import com.spring.friend.service.InterFriendService;
import com.spring.member.model.MemberVO;

@Controller
@Component
public class FriendController {

	@Autowired
	private InterFriendService service;

	@RequestMapping(value="/friendList.action", method= {RequestMethod.GET})
	public String friendList(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		String email = loginuser.getEmail();
		
	//	String email = req.getParameter("email");
		req.setAttribute("email", email);
		
		System.out.println("email(pageuser)"+email);
		HashMap<String,String> map = new HashMap<String,String>();
		
	    String searchEmail = req.getParameter("searchEmail");
	   
	    if(searchEmail == null) 
	    {
	    	searchEmail = "";
	    }
	    
//	    System.out.println(email);
	    System.out.println(searchEmail);
	    

		if(loginuser == null) {
			  String msg = "로그인하세요";
			  String loc = "index.action";
			   
			  req.setAttribute("msg", msg);
			  req.setAttribute("loc", loc);			   
			   
			  return "msg.notiles";
		  }
		
		map.put("email", email);
	    map.put("searchEmail", searchEmail);
	
		int totalFriendCount = service.getTotalFriendCount(map);
		
	    req.setAttribute("totalFriendCount", totalFriendCount); 

	    List<FriendVO> friendList = service.getFriendList(map);
	    
		req.setAttribute("friendList", friendList); 
//		req.setAttribute("loginuser", loginuser); 

	
 		return "friend/friendList.tiles2";			

	}
	
	@RequestMapping(value="/friendSearch.action", method= {RequestMethod.GET})
	public String friendSearch(HttpServletRequest req, HttpServletResponse res) {
	
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
	//	String email = loginuser.getEmail();
		
		String email = req.getParameter("email");
		req.setAttribute("email", email);
		
		String searchEmail = req.getParameter("searchEmail");
		req.setAttribute("searchEmail", searchEmail);

		System.out.println("searchemail");
		System.out.println("searchemail(pageuser)"+searchEmail);
		
	    
	   
	    if(searchEmail == null) 
	    {
	    	searchEmail = "";
	    }
	    
//	    System.out.println(email);
//	    System.out.println(searchEmail);
	    
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", email);
	    map.put("searchEmail", searchEmail);
	   

	//	int totalFriendCount = service.getTotalFriendCount(map);
		
	//    req.setAttribute("totalFriendCount", totalFriendCount); 

	    List<FriendVO> searchfriendList = service.getsearchfriendList(map);
	    
		req.setAttribute("searchfriendList", searchfriendList); 
		req.setAttribute("loginuser", loginuser); 

	
 		return "friend/friendList.tiles2";			

	}
	
	// 승인허가
	@RequestMapping(value="/friendAllowList.action", method= {RequestMethod.GET})
	public String friendAllowList(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		String email = loginuser.getEmail();
		
	//	String email = req.getParameter("email");
		req.setAttribute("email", email);
		
		HashMap<String,String> map = new HashMap<String,String>();	   

		if(loginuser == null) {
			  String msg = "로그인하세요";
			  String loc = "index.action";
			   
			  req.setAttribute("msg", msg);
			  req.setAttribute("loc", loc);			   
			   
			  return "msg.notiles";
		  }
		
		map.put("email", email);
		
	    List<FriendVO> friendList = service.getFriendAllowList(map);

		req.setAttribute("friendList", friendList); 

 		return "friend/friendAllowList.tiles2";			

	}
	
	@RequestMapping(value="/friendAllow.action", method= {RequestMethod.GET})
	public String friendAllow(HttpServletRequest req, HttpServletResponse res) {		
		
		// 본인 (나에게 친구를 신청한거니까!)
	//	String friend_email = req.getParameter("friend_email"); 
		
		// 상대방(상대방이 나를 친구추가한거니까)
		String fk_email = req.getParameter("fk_email");
		
		HttpSession session = req.getSession();

		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		String friend_email = loginuser.getEmail();	
		
		System.out.println("fk_email(allow): " + fk_email);
		System.out.println("friend_email(me): " + friend_email);

			
		// 친구 신청
		HashMap<String,String> map = new HashMap<String,String>();

		map.put("fk_email", fk_email);
		map.put("friend_email", friend_email);

		int n = service.getFriendAllow(map); 
			
		req.setAttribute("n", n); 

		return "friend/friendAllowEnd.tiles2";
	}
	
	@RequestMapping(value="/friendAllowDel.action", method= {RequestMethod.GET})
	public String friendAllowDel(HttpServletRequest req, HttpServletResponse res) {		
		
		// 본인 (나에게 친구를 신청한거니까!)
	//	String friend_email = req.getParameter("friend_email"); 
		
		// 상대방(상대방이 나를 친구추가한거니까)
		String fk_email = req.getParameter("fk_email");
		
		HttpSession session = req.getSession();

		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		String friend_email = loginuser.getEmail();	
		
		System.out.println("fk_email(allow): " + fk_email);
		System.out.println("friend_email(me): " + friend_email);

			
		// 친구 신청취소
		HashMap<String,String> map = new HashMap<String,String>();

		map.put("fk_email", fk_email);
		map.put("friend_email", friend_email);

		int n = service.getFriendAllowDel(map); 
			
		req.setAttribute("n", n); 

		return "friend/friendAllowDelEnd.tiles2";
	}
	
	@RequestMapping(value="/friendAdd.action", method= {RequestMethod.GET})
	public String friendAdd(HttpServletRequest req, HttpServletResponse res) {		
		
		// 버튼을 눌러서 가져온 상대방 이메일
		String friend_email = req.getParameter("email"); 
			
		HttpSession session = req.getSession();
		
		// 본인 이메일
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		String fk_email = loginuser.getEmail();					
			
		// 친구 신청
		HashMap<String,String> map = new HashMap<String,String>();

		map.put("fk_email", fk_email);
		map.put("friend_email", friend_email);

		// 친구신청을 이미 했는지 안했는지 검사
		int n = service.checkFriendAdd(map);
		int m = 0;
		
		req.setAttribute("n", n); 

		if(n != 0 ) {
			  String msg = "이미 친구신청을 하셨습니다.";
			  String loc = "index.action";
			   
			  req.setAttribute("msg", msg);
			  req.setAttribute("loc", loc);			   
			   
			  return "msg.notiles";
			  
		}else {
			
			m = service.getFriendAdd(map); 
		}
		
	
		req.setAttribute("m", m); 

		return "friend/friendAddEnd.tiles2";
	}
	
	@RequestMapping(value="/friendDel.action", method= {RequestMethod.GET})
	public String friendDel(HttpServletRequest req, HttpServletResponse res) {		
		
		// 버튼을 눌러서 가져온 상대방 이메일
		String friend_email = req.getParameter("email"); 
			
		HttpSession session = req.getSession();
		
		// 본인 이메일
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		String fk_email = loginuser.getEmail();					
			
		// 친구 삭제
		HashMap<String,String> map = new HashMap<String,String>();

		map.put("fk_email", fk_email);
		map.put("friend_email", friend_email);


		int m = service.getFriendDel(map); 

	
		req.setAttribute("m", m); 

		return "friend/friendDelEnd.tiles2";
	}
}