package com.spring.member.model;

import java.util.HashMap;
import java.util.List;

public interface InterMemberDAO {
	
	MemberVO getLoginMember(HashMap<String, String> map);

	int gettotalmember(HashMap<String, String> map);

	List<MemberVO> getMemberlist(HashMap<String, String> map); 


	int registerMember(MemberVO membervo); 

	int emailDuplicateCheck(String email); 

	String getEmail(HashMap<String, String> map); 

	int isEmailExists(HashMap<String, String> map);
	int updatePwdUser(HashMap<String, String> map);

	MemberVO getMemberOneByIdx(String idx); 

//	int getNaverDuplicate(HashMap<String, String> map); 
//	int NaverRegisterMember(MemberVO membervo);
	
	int updateMember(MemberVO membervo);
	
	MemberVO getMemberOneByIdx(HashMap<String, String> map); 
	
	int deleteMember(String idx);

//	int getEmailCount(String emailSearchWord);

	int getEmailSearchTotalCount(HashMap<String, String> map);

	List<MemberVO> getEmailSearch(HashMap<String, String> map);

	List<String> getEmailSearchJSON(String searchword);

	int updateMyInfo(HashMap<String, String> map); 
}
