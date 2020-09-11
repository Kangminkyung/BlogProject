package com.spring.blog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.member.model.MemberVO;


@Controller
@Component

public class HomeController {

	@RequestMapping(value="/index.action", method= {RequestMethod.GET})
	public String index(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginuser");
		String email = req.getParameter("email");		
		String pwd = req.getParameter("pwd");
		
		session.setAttribute("emailSearchCount", "yes");
		return "main/index.tiles";
	}
	
	@RequestMapping(value="/signup.action", method= {RequestMethod.GET})
	public String member(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");
				
		return "main/index.tiles";
	}
		
}
