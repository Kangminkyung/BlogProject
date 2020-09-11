package com.spring.friend.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.friend.model.FriendVO;
import com.spring.friend.model.InterFriendDAO;
import com.spring.member.model.MemberVO;


@Service
public class FriendService implements InterFriendService {

	@Autowired
	private InterFriendDAO dao;

	@Override
	public int getTotalFriendCount(HashMap<String, String> map) {
		int n = dao.totalFriendCount(map);
		return n;
	}

	@Override
	public List<FriendVO> getFriendList(HashMap<String, String> map) {
		List<FriendVO> friendList = dao.getFriendList(map);
		return friendList;
	}


	@Override
	public List<FriendVO> getFriendAllowList(HashMap<String, String> map) {
		List<FriendVO> friendList = dao.getFriendAllowList(map);
		return friendList;
	}


	@Override
	public int getFriendAllow(HashMap<String, String> map) {
		int n = dao.getFriendAllow(map);
		return n;
	}

	@Override
	public int getFriendAllowDel(HashMap<String, String> map) {
		int n = dao.getFriendAllowDel(map);
		return n;
	}
	
	@Override
	public int checkFriendAdd(HashMap<String, String> map) {
		int n = dao.checkFriendAdd(map);
		return n;
	}

	@Override
	public int getFriendAdd(HashMap<String, String> map) {
		int n = dao.getFriendAdd(map);
		return n;
	}

	@Override
	public int getFriendDel(HashMap<String, String> map) {
		int n = dao.getFriendDel(map);
		return n;
	}

	@Override
	public List<FriendVO> getsearchfriendList(HashMap<String, String> map) {
		List<FriendVO> friendList = dao.getsearchfriendList(map);
		return friendList;
	}

	

	
	
	
}
