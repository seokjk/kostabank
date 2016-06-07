package org.kosta.kostabank.model.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.DealDetailDAO;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.springframework.stereotype.Service;
@Service
public class DealDetailServiceImpl implements DealDetailService{
	@Resource
	private DealDetailDAO dealDetailDAO;
	@Override
	public List<DealDetailVO> getDetail(DealDetailVO dealDetailVO){
		
		return dealDetailDAO.getDetail(dealDetailVO);
	}
	@Override
	public List<DealDetailVO> getDetailByGap(String gapChecked, String accountNo){
		System.out.println(2);
		DealDetailVO dealDetailVO = new DealDetailVO();
		dealDetailVO.setAccountNo(accountNo);
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
	/*	int year = cal.get(cal.YEAR);
		System.out.println(year);
		int month = cal.get(cal.MONTH);
		System.out.println(month);*/
		cal.setTime(today);
		cal.add(Calendar.DATE, 1);
		Date d = cal.getTime();
		String endDay = sdf.format(d);
		System.out.println("today" + endDay);
		dealDetailVO.setEndDay(endDay);
		
		if(gapChecked.equals("today")){
			cal.setTime(today);
			cal.add(Calendar.DATE, 0);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
		}else if (gapChecked.equals("oneMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -1);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
		}else if (gapChecked.equals("threeMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -3);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
		}else if (gapChecked.equals("sixMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -6);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
		}else if(gapChecked.equals("oneYear")){
			cal.setTime(today);
			cal.add(Calendar.YEAR, -1);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
		}
			
		return dealDetailDAO.getDetail(dealDetailVO);
	}
}
