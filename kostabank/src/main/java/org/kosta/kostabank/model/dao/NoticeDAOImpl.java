package org.kosta.kostabank.model.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.NoticeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class NoticeDAOImpl implements NoticeDAO {
	@Resource
	private SqlSessionTemplate template;
	
	@Override
	public void write(NoticeVO nvo){
		template.insert("notice.write",nvo);
	}
	
	@Override
	public List<NoticeVO> getNoticeList(String pageNo){
		return template.selectList("notice.getNoticeList",pageNo);
	}
	
	@Override
	public NoticeVO showContent(int no){
		return (NoticeVO)template.selectOne("notice.showContent",no);
	}
	
	@Override
	public void updateCount(int no){
		template.update("notice.updateCount",no);
	}
	
	@Override
	public void deleteNotice(int no){
		template.delete("notice.deleteNotice",no);
	}
	
	@Override
	public void updateNotice(NoticeVO nvo){
		template.update("notice.updateNotice",nvo);
	}
	
	@Override
	public int totalContent(){
		return template.selectOne("notice.totalContent");
	}
	
	@Override
	public List<NoticeVO> getNoticeList(Map<String, Integer> pagingConfig){
		return template.selectList("notice.getNoticeList",pagingConfig);
	}
	

}












