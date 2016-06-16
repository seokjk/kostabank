package org.kosta.kostabank.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.kostabank.model.service.NoticeService;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.NoticeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class NoticeController {
	@Resource(name="noticeServiceImpl")
	private NoticeService noticeService;
	
	///////////////////////////////////////////////////////////////
	///////	title : write									///////
	/////// dec : 공지사항 글쓰기								///////
	///////////////////////////////////////////////////////////////
	@RequestMapping(value="notice_write.bank",method=RequestMethod.POST)
	public ModelAndView write(HttpServletRequest request, NoticeVO nvo){
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginInfo") == null) {
			return new ModelAndView("redirect:kangbank/templates/needLogin.jsp");
		}
		nvo.setCustomerVO((CustomerVO) session.getAttribute("loginInfo"));
		noticeService.write(nvo);
		return new ModelAndView("redirect:notice_showContentNoHit.bank?no="+nvo.getNo());
	}

	///////////////////////////////////////////////////////////////
	///////	title : list									///////
	/////// dec : 공지사항 목록 보기							///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("notice_list.bank")
	public ModelAndView list(String pageNo){
		return new ModelAndView("notice_list","nlvo",noticeService.getNoticeList(pageNo));
	}

	///////////////////////////////////////////////////////////////
	///////	title : showContent								///////
	/////// dec : 공지사항 게시물 보기(조회수 증가o)				///////
	///////////////////////////////////////////////////////////////
	@RequestMapping("notice_showContent.bank")
	public ModelAndView showContent(int no){
		noticeService.updateCount(no);
		return new ModelAndView("redirect:notice_showContentNoHit.bank?no="+no);
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : showContentNoHit						///////
	/////// dec : 공지사항 게시물 보기(조회수 증가x)				///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("notice_showContentNoHit.bank")
	public ModelAndView showContentNoHit(int no){
		return new ModelAndView("notice_showContent","nvo",noticeService.showContentNoHit(no));
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : deleteNotice							///////
	/////// dec : 공지사항 게시물 삭제							///////
	///////////////////////////////////////////////////////////////		
	@RequestMapping("notice_deleteNotice.bank")
	public ModelAndView deleteNotice(int no){
		noticeService.deleteNotice(no);
		return new ModelAndView("redirect:notice_list.bank","lvo",noticeService.getNoticeList());
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : updateView								///////
	/////// dec : 공지사항 게시물 수정하기 페이지로 이동			///////
	///////////////////////////////////////////////////////////////		
	@RequestMapping("notice_updateView.bank")
	public ModelAndView updateView(int no){
		return new ModelAndView("notice_update","nvo",noticeService.showContentNoHit(no));
	}
	
	///////////////////////////////////////////////////////////////
	///////	title : updateNotice							///////
	/////// dec : 공지사항 게시물 수정하기						///////
	///////////////////////////////////////////////////////////////	
	@RequestMapping("notice_updateNotice.bank")
	public ModelAndView updateNotice(NoticeVO nvo){
		noticeService.updateNotice(nvo);
		return new ModelAndView("notice_showContent","nvo",noticeService.showContentNoHit(nvo.getNo()));
	}
	
	

}

