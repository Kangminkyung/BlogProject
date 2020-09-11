package com.spring.timeline.service;

import java.util.HashMap;
import java.util.List;

import com.spring.timeline.model.TimeLineVO;
import com.spring.member.model.MemberVO;

public interface InterTimeLineService {

	List<TimeLineVO> getTimeLine(String email);

	int addTimeLine(TimeLineVO timelinevo);

	int addTimeLine_withFile(TimeLineVO timelinevo);

	int delTimeLine(HashMap<String, String> map);

	TimeLineVO getViewTimeLine(String seq);

	int getTimeLineEdit(TimeLineVO timelinevo);



}
