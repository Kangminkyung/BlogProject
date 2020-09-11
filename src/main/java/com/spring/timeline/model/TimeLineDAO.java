package com.spring.timeline.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.member.model.MemberVO;

@Repository
public class TimeLineDAO implements InterTimeLineDAO {

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<TimeLineVO> getTimeLine(String email) {
		List<TimeLineVO> timeline = sqlsession.selectList("timeline.getTimeLine", email);
		return timeline;	
	}

	@Override
	public int addTimeLine(TimeLineVO timelinevo) {
		int n = sqlsession.insert("timeline.addtimeline", timelinevo);
		return n;
	}

	@Override
	public int addTimeLine_withFile(TimeLineVO timelinevo) {
		int n = sqlsession.insert("timeline.addtimeline_withFile", timelinevo);
		return n;
	}

	@Override
	public int delTimeLine(HashMap<String, String> map) {
		int n = sqlsession.update("timeline.delTimeLine", map);
		return n;
	}

	@Override
	public TimeLineVO getViewTimeLine(String seq) {
		TimeLineVO timeline = sqlsession.selectOne("timeline.getViewTimeLine", seq);
		return timeline;	
	}

	@Override
	public int updateContent(TimeLineVO timelinevo) {
		int n = sqlsession.update("timeline.updateContent", timelinevo);
		return n;
	}
}
