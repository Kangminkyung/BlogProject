package com.spring.blog;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.member.model.MemberVO;
import com.spring.member.service.InterMemberService;
import com.spring.timeline.model.PhotoVO;
import com.spring.timeline.model.TimeLineVO;
import com.spring.timeline.service.InterTimeLineService;

@Controller
@Component
public class TimeLineController {

	@Autowired
	private InterTimeLineService service;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/timeLine.action", method= {RequestMethod.GET})
	public String timeLine(HttpServletRequest req, HttpServletResponse res) {
		
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		String email = req.getParameter("email");
		req.setAttribute("email", email);

	//	System.out.println(email);
		List<TimeLineVO> timeLineList = service.getTimeLine(email);
	 	req.setAttribute("timeLineList", timeLineList);
	 	

//	 	System.out.println(timeLineList.get(1));
 		return "timeline/timeLine.tiles2";			

	}
		@RequestMapping(value="/timeLineAdd.action", method= {RequestMethod.GET})
		public String requireLogin_timeLineAdd(HttpServletRequest req, HttpServletResponse res) {
			
			String email = req.getParameter("email");
			
			req.setAttribute("email", email);
		
			return "timeline/timeLineAdd.tiles2";
		} 
		
		@RequestMapping(value="/timeLineAddEnd.action", method= {RequestMethod.POST})
		public String timeLineAddEnd(TimeLineVO timelinevo, MultipartHttpServletRequest req, HttpSession session) {
			
			
			String content = timelinevo.getContent().replaceAll("\r\n", "<br>");
			String status = timelinevo.getStatus();
			
			System.out.println(status);
			timelinevo.setContent(content);
			timelinevo.setStatus(status);
			
			int n = 0;
		
			n = service.addTimeLine(timelinevo);
			req.setAttribute("n", n);

		
			return "timeline/timeLineAddEnd.tiles2";
		} 
		
	
		
		@RequestMapping(value="/image/photoUpload.action", method={RequestMethod.POST})
		public String photoUpload(PhotoVO photovo, HttpServletRequest req) {
		    
			String callback = photovo.getCallback();
		    String callback_func = photovo.getCallback_func();
		    String file_result = "";
		    
			if(!photovo.getFiledata().isEmpty()) {
				// 파일이 존재한다라면
				
				/*
				   1. 사용자가 보낸 파일을 WAS(톰캣)의 특정 폴더에 저장해주어야 한다.
				   >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
				        우리는 WAS 의 webapp/resources/photo_upload 라는 폴더로 지정해준다.
				 */
				
				// WAS 의 webapp 의 절대경로를 알아와야 한다. 
				HttpSession session = req.getSession();
				String root = session.getServletContext().getRealPath("/"); 
				String path = root + "resources"+File.separator+"photo_upload";
				// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
				
			//	System.out.println(">>>> 확인용 path ==> " + path); 
				// >>>> 확인용 path ==> C:\SpringWorkspaceTeach\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\photo_upload
				
				// 2. 파일첨부를 위한 변수의 설정 및 값을 초기화한 후 파일올리기
				String newFilename = "";
				// WAS(톰캣) 디스크에 저장할 파일명 
				
				byte[] bytes = null;
				// 첨부파일을 WAS(톰캣) 디스크에 저장할때 사용되는 용도 
							
				try {
					bytes = photovo.getFiledata().getBytes(); 
					// getBytes()는 첨부된 파일을 바이트단위로 파일을 다 읽어오는 것이다. 
					/* 2-1. 첨부된 파일을 읽어오는 것
						    첨부한 파일이 강아지.png 이라면
						    이파일을 WAS(톰캣) 디스크에 저장시키기 위해
						    byte[] 타입으로 변경해서 받아들인다.
					*/
					// 2-2. 이제 파일올리기를 한다.
					String original_name = photovo.getFiledata().getOriginalFilename();
					//  photovo.getFiledata().getOriginalFilename() 은 첨부된 파일의 실제 파일명(문자열)을 얻어오는 것이다. 
					newFilename = fileManager.doFileUpload(bytes, original_name, path);
					
			//      System.out.println(">>>> 확인용 newFileName ==> " + newFileName); 
					
					int width = fileManager.getImageWidth(path+File.separator+newFilename);
			//		System.out.println("확인용 >>>>>>>> width : " + width);
					
					if(width > 600)
						width = 600;
			//		System.out.println("확인용 >>>>>>>> width : " + width);
					
					String CP = req.getContextPath();  // board
					file_result += "&bNewLine=true&sFileName="+newFilename+"&sWidth="+width+"&sFileURL="+CP+"/resources/photo_upload/"+newFilename; 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				// 파일이 존재하지 않는다라면
				file_result += "&errstr=error";
			}
		    
			return "redirect:" + callback + "?callback_func="+callback_func+file_result;
			
		}// end of String photoUpload(PhotoVO photovo, HttpServletRequest req)-------------------    
		
	@RequestMapping(value="/image/multiplePhotoUpload.action", method={RequestMethod.POST})
	public void multiplePhotoUpload(HttpServletRequest req, HttpServletResponse res) {
		    
		/*
			1. 사용자가 보낸 파일을 WAS(톰캣)의 L특정 폴더에 저장해주어야 한다.
		   >>>> 파일이 업로드 되어질 특정 경로(폴더)지정해주기
		        우리는 WAS 의 webapp/resources/photo_upload 라는 폴더로 지정해준다.
			*/
			
			// WAS 의 webapp 의 절대경로를 알아와야 한다. 
		HttpSession session = req.getSession();
		String root = session.getServletContext().getRealPath("/"); 
			String path = root + "resources"+File.separator+"photo_upload";
		// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다. 
			
		//	System.out.println(">>>> 확인용 path ==> " + path); 
			// >>>> 확인용 path ==> C:\SpringWorkspaceTeach\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Board\resources\photo_upload   
			
		File dir = new File(path);
		if(!dir.exists())
			dir.mkdirs();
			
		String strURL = "";
			
		try {
			if(!"OPTIONS".equals(req.getMethod().toUpperCase())) {
				String filename = req.getHeader("file-name"); //파일명을 받는다 - 일반 원본파일명
		    		
		    //		System.out.println(">>>> 확인용 filename ==> " + filename); 
		    		// >>>> 확인용 filename ==> berkelekle%ED%8A%B8%EB%9E%9C%EB%94%9405.jpg
		    		
		    	InputStream is = req.getInputStream();
		    	/*
		          	요청 헤더의 content-type이 application/json 이거나 multipart/form-data 형식일 때,
		          	혹은 이름 없이 값만 전달될 때 이 값은 요청 헤더가 아닌 바디를 통해 전달된다. 
		          	이러한 형태의 값을 'payload body'라고 하는데 요청 바디에 직접 쓰여진다 하여 'request body post data'라고도 한다.

	               	서블릿에서 payload body는 Request.getParameter()가 아니라 
	            	Request.getInputStream() 혹은 Request.getReader()를 통해 body를 직접 읽는 방식으로 가져온다. 	
		    	 */
		    	String newFilename = fileManager.doFileUpload(is, filename, path);
		    	
				int width = fileManager.getImageWidth(path+File.separator+newFilename);
				
				if(width > 600)
					width = 600;
					
			//		System.out.println(">>>> 확인용 width ==> " + width);
					// >>>> 확인용 width ==> 600
					// >>>> 확인용 width ==> 121
		    	
				String CP = req.getContextPath(); // board
				
				strURL += "&bNewLine=true&sFileName="; 
	           	strURL += newFilename;
	           	strURL += "&sWidth="+width;
	           	strURL += "&sFileURL="+CP+"/resources/photo_upload/"+newFilename;
		    }
			
		    	/// 웹브라우저상에 사진 이미지를 쓰기 ///
				PrintWriter out = res.getWriter();
				out.print(strURL);
		} catch(Exception e){
				e.printStackTrace();
		}
			
	}// end of void multiplePhotoUpload(HttpServletRequest req, HttpServletResponse res)---------------- 

	@RequestMapping(value="/timeLineDel.action", method= {RequestMethod.GET})
	public String timeLineDel(HttpServletRequest req, HttpServletResponse response) {		
		
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
//		String email = req.getParameter("email");
		String email = loginuser.getEmail();
		req.setAttribute("email", email);
		
		String seq = req.getParameter("seq"); 

		System.out.println(email);		
		System.out.println(seq);		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("email", email);

		int n = service.delTimeLine(map);
		req.setAttribute("n", n);
			
		return "timeline/timeLineDelEnd.tiles2";
		
		
	}
	
	@RequestMapping(value="/timeLineEdit.action", method= {RequestMethod.GET})
	public String timeLineEdit(HttpServletRequest req, HttpServletResponse response) {		
		
		String seq = req.getParameter("seq"); // �닔�젙�빐�빞�븷 湲�踰덊샇 媛��졇�삤湲�
		
		TimeLineVO timelinevo = service.getViewTimeLine(seq);
		
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(!timelinevo.getEmail().equals(loginuser.getEmail())) {
			String msg = "작성자만 수정할 수 있습니다.";
			String loc = "javascript:history.back()";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			return "msg.notiles";
		}
		else {
			// 媛��졇�삩 1媛쒓��쓣 request �쁺�뿭�뿉 ���옣�떆耳쒖꽌 view�떒 �럹�씠吏�濡� �꽆湲대떎.
			req.setAttribute("timelinevo", timelinevo);
		}
		return "timeline/timeLineEdit.tiles2";
	}
	
	
	@RequestMapping(value="/timeLineEditEnd.action", method= {RequestMethod.POST})
	public String timeLineEditEnd(TimeLineVO timelinevo, HttpServletRequest req) {
		
		String seq = timelinevo.getSeq();
		String content = timelinevo.getContent().replaceAll("\r\n", "<br>");
		timelinevo.setContent(content);
		timelinevo.setSeq(seq);

		int n = service.getTimeLineEdit(timelinevo);
		
		req.setAttribute("n", n);
		req.setAttribute("seq", timelinevo.getSeq());
		
		return "timeline/timeLineEditEnd.tiles2";
	}
	
	
	@RequestMapping(value="/comment.action", method= {RequestMethod.GET})
	public String comment(HttpServletRequest req) {
		
		
		return "comment";
	}
	
	@RequestMapping(value="/commentDetail.action", method= {RequestMethod.POST})
	public String quizupdate(HttpServletRequest req) {
	
		String useremail = req.getParameter("useremail");
		String fk_seq = req.getParameter("fk_seq");
		
	
		HttpSession session = req.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		String email = loginuser.getEmail();
		req.setAttribute("useremail", useremail);
		req.setAttribute("fk_seq", fk_seq);
		req.setAttribute("email", email);

		System.out.println(useremail);
		System.out.println(fk_seq);

		String loc = "timeline/timeLine.tiles";
		req.setAttribute("loc", loc);

		return "msg.notiles";
		
	} 
}
