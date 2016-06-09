package org.kosta.kostabank.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.NoticeDAO;
import org.kosta.kostabank.model.vo.ListVO;
import org.kosta.kostabank.model.vo.ListVO2;
import org.kosta.kostabank.model.vo.NoticeVO;
import org.kosta.kostabank.model.vo.PagingBean;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Resource(name="noticeDAOImpl")
	private NoticeDAO noticeDAO;
	@Resource(name="pagingConfig")
	private Map<String,Integer> pagingConfig;
	

	@Override
	public void write(NoticeVO nvo){
		noticeDAO.write(nvo);
	}
	
	@Override
	public ListVO2 getNoticeList(){
		return getNoticeList(1);
	}
	
	@Override
	public ListVO2 getNoticeList(String pageNo){
		if(pageNo==null||pageNo==""){
			pageNo="1";
		}
		int pno = Integer.parseInt(pageNo);
		return getNoticeList(pno);
	}
	

	@Override
	public ListVO2 getNoticeList(int pageNo){
		HashMap<String,Integer> paramMap=new HashMap<String,Integer>();
		paramMap.put("pageNo", pageNo);
		paramMap.put("numberOfContent", pagingConfig.get("numberOfContent"));
		List<NoticeVO> list2=noticeDAO.getNoticeList(paramMap);
		int total=noticeDAO.totalContent();
		PagingBean paging=new PagingBean(total,pageNo,pagingConfig);
		ListVO2 lvo2=new ListVO2(list2,paging);
		return lvo2;
	}
	

	@Override
	public NoticeVO showContent(int no){
		noticeDAO.updateCount(no);
		return noticeDAO.showContent(no);
	}
	

	@Override
	public void updateCount(int no){
		noticeDAO.updateCount(no);
	}
	
	@Override
	public NoticeVO showContentNoHit(int no){
		return noticeDAO.showContent(no);
	}
	

	@Override
	public void deleteNotice(int no){
		noticeDAO.deleteNotice(no);
	}
	

	@Override
	public void updateNotice(NoticeVO nvo){
		noticeDAO.updateNotice(nvo);
	}	

}
