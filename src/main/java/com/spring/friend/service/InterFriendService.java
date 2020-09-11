package com.spring.friend.service;

import java.util.HashMap;
import java.util.List;

import com.spring.friend.model.FriendVO;
import com.spring.member.model.MemberVO;

public interface InterFriendService {

	int getTotalFriendCount(HashMap<String, String> map);

	List<FriendVO> getFriendList(HashMap<String, String> map);

	List<FriendVO> getFriendAllowList(HashMap<String, String> map);

	int getFriendAllow(HashMap<String, String> map); // 친구신청수락
	int getFriendAllowDel(HashMap<String, String> map); // 친구신청취소

	int checkFriendAdd(HashMap<String, String> map);

	int getFriendAdd(HashMap<String, String> map);

	int getFriendDel(HashMap<String, String> map);

	List<FriendVO> getsearchfriendList(HashMap<String, String> map);



}
