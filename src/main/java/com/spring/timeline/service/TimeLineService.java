package com.spring.timeline.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.member.model.InterMemberDAO;
import com.spring.member.model.MemberVO;
import com.spring.timeline.model.InterTimeLineDAO;
import com.spring.timeline.model.TimeLineVO;

@Service
public class TimeLineService implements InterTimeLineService {

	@Autowired
	private InterTimeLineDAO dao;

	@Override
	public List<TimeLineVO> getTimeLine(String email) {
		List<TimeLineVO> timeline = dao.getTimeLine(email);
		return timeline;
	}
	
	@Override
	public int addTimeLine(TimeLineVO timelinevo){
		int n = dao.addTimeLine(timelinevo);
		return n;
	}

	@Override
	public int addTimeLine_withFile(TimeLineVO timelinevo) {
		int n = dao.addTimeLine_withFile(timelinevo);
		return n;
	}

	@Override
	public int delTimeLine(HashMap<String, String> map) {
		int n = dao.delTimeLine(map);
		return n;
	}

	@Override
	public TimeLineVO getViewTimeLine(String seq) {
		TimeLineVO timelinevo = dao.getViewTimeLine(seq);
		return timelinevo;
	}

	@Override
	public int getTimeLineEdit(TimeLineVO timelinevo) {
		
		int n = dao.updateContent(timelinevo);
		
		return n;
	}

	
	
	
}
