package com.spring.friend.model;

import java.util.HashMap;
import java.util.List;

public interface InterFriendDAO {

	int totalFriendCount(HashMap<String, String> map);
	
	List<FriendVO> getFriendList(HashMap<String, String> map);

	int getFriendAllow(HashMap<String, String> map);
	int getFriendAllowDel(HashMap<String, String> map);

	List<FriendVO> getFriendAllowList(HashMap<String, String> map);

	int checkFriendAdd(HashMap<String, String> map);

	int getFriendAdd(HashMap<String, String> map);

	int getFriendDel(HashMap<String, String> map);

	List<FriendVO> getsearchfriendList(HashMap<String, String> map);


}
