package com.spring.member.service;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.member.model.InterMemberDAO;
import com.spring.member.model.MemberVO;

@Service
public class MemberService implements InterMemberService {

	@Autowired
	private InterMemberDAO dao;
	
	@Override
	public MemberVO getLoginMember(HashMap<String, String> map) {
		MemberVO loginuser = dao.getLoginMember(map);
		return loginuser;
	}

	@Override
	public int gettotalmember(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> getMemberlist(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int registerMember(MemberVO membervo) {
		int n = dao.registerMember(membervo);
		return n;
	}

	@Override
	public int emailDuplicateCheck(String email) {
		int n = dao.emailDuplicateCheck(email);
		return n;
	}

	@Override
	public String getEmail(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int isEmailExists(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePwdUser(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO getMemberOneByIdx(String idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberVO membervo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO getMemberOneByIdx(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMember(String idx) {
		// TODO Auto-generated method stub
		return 0;
	}

/*
	@Override
	public int getEmailCount(String emailSearchWord) {
		int n = dao.getEmailCount(emailSearchWord);
		return n;
	}
*/
	@Override
	public int getEmailSearchTotalCount(HashMap<String, String> map) {
		int totalCount = dao.getEmailSearchTotalCount(map);
		return totalCount;
	}

	@Override
	public List<MemberVO> getEmailSearch(HashMap<String, String> map) {
		List<MemberVO> memberList = dao.getEmailSearch(map);
		return memberList;
	}

	@Override
	public List<String> getEmailSearchJSON(String searchword) {
		List<String> emailList = dao.getEmailSearchJSON(searchword);
		return emailList;
	}

	@Override
	public int updateMyInfo(HashMap<String, String> map) {
		int n = dao.updateMyInfo(map);
		return n;
	}

}
