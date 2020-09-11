package com.spring.timeline.model;

import java.util.HashMap;
import java.util.List;

public interface InterTimeLineDAO {

	List<TimeLineVO> getTimeLine(String email);

	int addTimeLine(TimeLineVO timelinevo);

	int addTimeLine_withFile(TimeLineVO timelinevo);
	
	int delTimeLine(HashMap<String, String> map);

	TimeLineVO getViewTimeLine(String seq);

	int updateContent(TimeLineVO timelinevo);


}
