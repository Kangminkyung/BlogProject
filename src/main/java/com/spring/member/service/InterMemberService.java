package com.spring.member.service;

import java.util.HashMap;
import java.util.List;

import com.spring.member.model.MemberVO;

public interface InterMemberService {
	
	MemberVO getLoginMember(HashMap<String, String> map); // �α��� ���� ó��

	int gettotalmember(HashMap<String, String> map);	// ȸ�� �ѿ� ���ϱ� -- ����¡ó����

	List<MemberVO> getMemberlist(HashMap<String, String> map); // ����¡ ó�� - ȸ����� ����

	int registerMember(MemberVO membervo); // ȸ������ ���� ó��

	int emailDuplicateCheck(String email); // ȸ������ �Ҷ� Email �ߺ� ���� üũ

	String getEmail(HashMap<String, String> map); // email ã��

	int isEmailExists(HashMap<String, String> map); // ��й�ȣ ã�⸦ ���� ���� email �� ������ ����ڰ� �����ϴ��� �������ִ� �� ����

	int updatePwdUser(HashMap<String, String> map); // ��ȣ�� ����ȣ�� �����ϴ� �� ����

	MemberVO getMemberOneByIdx(String idx); // ȸ�� ���� ����

	int updateMember(MemberVO membervo); // ȸ�� ���� ���� ���� ó��

	MemberVO getMemberOneByIdx(HashMap<String, String> map); // ȸ�� ���� ����
	
	int deleteMember(String idx); // ȸ�� ���� �ϱ�
	
//	int getEmailCount(String emailSearchWord);

	int getEmailSearchTotalCount(HashMap<String, String> map);

	List<MemberVO> getEmailSearch(HashMap<String, String> map);

	List<String> getEmailSearchJSON(String searchword);

	int updateMyInfo(HashMap<String, String> map);

	
}
