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
	
	//글쓰기
	@RequestMapping(value="notice_write.bank",method=RequestMethod.POST)
	public ModelAndView write(HttpServletRequest request, NoticeVO nvo){
		HttpSession session = request.getSession(false);
		if(session!=null){
			CustomerVO cvo = (CustomerVO) session.getAttribute("loginInfo");
			if(cvo!=null){
				nvo.setCustomerVO(cvo);
			}
		}
		noticeService.write(nvo);
		return new ModelAndView("redirect:notice_showContentNoHit.bank?no="+nvo.getNo());
	}
	//리스트
	@RequestMapping("notice_list.bank")
	public ModelAndView list(String pageNo){
		return new ModelAndView("notice_list","nlvo",noticeService.getNoticeList(pageNo));
	}
	// 게시물 보여주기
	@RequestMapping("notice_showContent.bank")
	public ModelAndView showContent(int no){
		noticeService.updateCount(no);
		return new ModelAndView("redirect:notice_showContentNoHit.bank?no="+no);
	}
	
	
	@RequestMapping("notice_showContentNoHit.bank")
	public ModelAndView showContentNoHit(int no){
		return new ModelAndView("notice_showContent","nvo",noticeService.showContentNoHit(no));
	}
	@RequestMapping("notice_deleteNotice.bank")
	public ModelAndView deleteNotice(int no){
		noticeService.deleteNotice(no);
		return new ModelAndView("redirect:notice_list.bank","lvo",noticeService.getNoticeList());
	}
	@RequestMapping("notice_updateView.bank")
	public ModelAndView updateView(int no){
		return new ModelAndView("notice_update","nvo",noticeService.showContentNoHit(no));
	}
	@RequestMapping("notice_updateNotice.bank")
	public ModelAndView updateNotice(NoticeVO nvo){
		noticeService.updateNotice(nvo);
		return new ModelAndView("notice_showContent","nvo",noticeService.showContentNoHit(nvo.getNo()));
	}
	
	

}

