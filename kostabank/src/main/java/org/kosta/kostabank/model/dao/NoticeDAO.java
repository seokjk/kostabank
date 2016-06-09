package org.kosta.kostabank.model.dao;

import java.util.List;
import java.util.Map;

import org.kosta.kostabank.model.vo.NoticeVO;

public interface NoticeDAO {

	public abstract void write(NoticeVO nvo);

	public abstract List<NoticeVO> getNoticeList(String pageNo);

	public abstract NoticeVO showContent(int no);

	public abstract void updateCount(int no);

	public abstract void deleteNotice(int no);

	public abstract void updateNotice(NoticeVO nvo);

	public abstract int totalContent();

	public abstract List<NoticeVO> getNoticeList(Map<String, Integer> pagingConfig);

}