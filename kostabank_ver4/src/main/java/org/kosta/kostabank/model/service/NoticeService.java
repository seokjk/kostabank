package org.kosta.kostabank.model.service;

import org.kosta.kostabank.model.vo.ListVO2;
import org.kosta.kostabank.model.vo.NoticeVO;

public interface NoticeService {

	public abstract void write(NoticeVO nvo);

	public abstract ListVO2 getNoticeList();

	public abstract ListVO2 getNoticeList(String pageNo);

	public abstract ListVO2 getNoticeList(int pageNo);

	public abstract NoticeVO showContent(int no);

	public abstract void updateCount(int no);

	public abstract NoticeVO showContentNoHit(int no);

	public abstract void deleteNotice(int no);

	public abstract void updateNotice(NoticeVO nvo);

}