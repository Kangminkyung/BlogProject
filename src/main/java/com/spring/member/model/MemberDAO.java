package com.spring.member.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO implements InterMemberDAO {

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public MemberVO getLoginMember(HashMap<String, String> map) {
		MemberVO loginuser = sqlsession.selectOne("member.getLoginMember",map);
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
		int n = sqlsession.insert("member.registerMember",membervo);
		return n;
	}
	
	@Override
	public int emailDuplicateCheck(String email) {
		int n = sqlsession.selectOne("member.emailDuplicateCheck",email);
		return n;
	}
	
	@Override
	public String getEmail(HashMap<String, String> map) {
		String email = sqlsession.selectOne("member.getEmail",map);
		return email;
	}

	@Override
	public int isEmailExists(HashMap<String, String> map) {
		int n = sqlsession.selectOne("member.isEmailExists",map);
		return n;
	}
	
	@Override
	public int updatePwdUser(HashMap<String, String> map) {
		int n = sqlsession.update("member.updatePwdUser", map);
		return n;
	}
	
	// ȸ�� ���� ����
	@Override
	public MemberVO getMemberOneByIdx(String idx) {
		MemberVO loginuser = sqlsession.selectOne("member.getMemberOneByIdx",idx);
		return loginuser;
	}

	// ȸ�� ���� ���� ���� ó��	
	@Override
	public int updateMember(MemberVO membervo) {
		int n = sqlsession.update("member.updateMember",membervo);
		return n;
	}
	
	// ȸ�� ���� ����
	@Override
	public MemberVO getMemberOneByIdx(HashMap<String, String> map) {
		MemberVO loginuser = sqlsession.selectOne("member.getMemberOneByIdx",map);
		return loginuser;
	}
	
	// ȸ�� ���� �ϱ�
	@Override
	public int deleteMember(String idx) {
		int n = sqlsession.update("member.deleteMember", idx);
		return n;
	}

/*
	@Override
	public int getEmailCount(String emailSearchWord) {
		int n = sqlsession.update("member.getEmailCount",emailSearchWord);
		return n;
	}
*/
	@Override
	public int getEmailSearchTotalCount(HashMap<String, String> map) {
		int totalCount = sqlsession.selectOne("member.getEmailSearchTotalCount", map);
		return totalCount;
	}

	@Override
	public List<MemberVO> getEmailSearch(HashMap<String, String> map) {
		List<MemberVO> memberList = sqlsession.selectList("member.getEmailSearch", map);
		return memberList;
	}

	@Override
	public List<String> getEmailSearchJSON(String searchword) {
		 List<String> emailList =  sqlsession.selectList("member.getEmailSearchJSON",searchword);
			return emailList;
	}

	@Override
	public int updateMyInfo(HashMap<String, String> map) {
		int n = sqlsession.update("member.updateMyInfo", map);
		return n;
	}


	
}
