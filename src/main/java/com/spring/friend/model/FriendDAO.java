package com.spring.friend.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FriendDAO implements InterFriendDAO {

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public int totalFriendCount(HashMap<String, String> map) {
		int n = sqlsession.selectOne("friend.getTotalFriendCount" , map);
		return n;
	}

	@Override
	public List<FriendVO> getFriendList(HashMap<String, String> map) {
		List<FriendVO> friendList = sqlsession.selectList("friend.getFriendList",map);
		return friendList;
	}


	@Override
	public List<FriendVO> getFriendAllowList(HashMap<String, String> map) {
		List<FriendVO> friendList = sqlsession.selectList("friend.getFriendAllowList",map);
		return friendList;
	}

	@Override
	public int getFriendAllow(HashMap<String, String> map) {
		int n = sqlsession.update("friend.getFriendAllow", map);
		return n;
	}

	@Override
	public int getFriendAllowDel(HashMap<String, String> map) {
		int n = sqlsession.update("friend.getFriendAllowDel", map);
		return n;
	}
	
	@Override
	public int checkFriendAdd(HashMap<String, String> map) {
		int n = sqlsession.selectOne("friend.checkFriendAdd",map);
		return n;
	}

	@Override
	public int getFriendAdd(HashMap<String, String> map) {
		int n = sqlsession.insert("friend.getFriendAdd",map);
		return n;
	}

	@Override
	public int getFriendDel(HashMap<String, String> map) {
		int n = sqlsession.delete("friend.getFriendDel",map);
		return n;
	}

	@Override
	public List<FriendVO> getsearchfriendList(HashMap<String, String> map) {
		List<FriendVO> friendList = sqlsession.selectList("friend.getsearchfriendList",map);
		return friendList;
	}

	
}
