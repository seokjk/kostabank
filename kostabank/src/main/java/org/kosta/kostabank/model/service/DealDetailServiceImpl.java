package org.kosta.kostabank.model.service;
//
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountDAO;
import org.kosta.kostabank.model.dao.DealDetailDAO;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.DealListVO;
import org.kosta.kostabank.model.vo.ListVO;
import org.kosta.kostabank.model.vo.PagingBean;
import org.springframework.stereotype.Service;
@Service
public class DealDetailServiceImpl implements DealDetailService{
	@Resource
	private DealDetailDAO dealDetailDAO;
	@Resource
	private Map<String, Integer> pagingConfig;
	
	@Override
	/*public DealListVO getDetail(DealDetailVO dealDetailVO,int page){*/
	public List<DealDetailVO> getDetail(DealDetailVO dealDetailVO){
		System.out.println("22");
		String end = dealDetailVO.getEndDay();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date end1;
		try {
			end1 = sdf.parse(end);
			Calendar cal = Calendar.getInstance();
			cal.setTime(end1);
			cal.add(Calendar.DATE, 1);
			String endDay = sdf.format(cal.getTime());
			dealDetailVO.setEndDay(endDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("33");
		if(dealDetailVO.getDealType().equals("deposit")||dealDetailVO.getDealType().equals("withdraw")){
			return dealDetailDAO.getDetailByType(dealDetailVO);
		}
		System.out.println("44");
		return dealDetailDAO.getDetail(dealDetailVO);
		
/*		HashMap<String, String> paramMap = new HashMap<String, String>();
		String page1 = String.valueOf(page);
		String numberOfContent = String.valueOf(pagingConfig.get("numberOfContent"));
		paramMap.put("page", page1);
		paramMap.put("numberOfContent", numberOfContent);
		paramMap.put("accountNo", dealDetailVO.getAccountNo());
		paramMap.put("startDay", dealDetailVO.getStartDay());
		String end = dealDetailVO.getEndDay();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date end1;
		try {
			end1 = sdf.parse(end);
			Calendar cal = Calendar.getInstance();
			cal.setTime(end1);
			cal.add(Calendar.DATE, 1);
			String endDay = sdf.format(cal.getTime());
			dealDetailVO.setEndDay(endDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("아아아아아");
		

		if(dealDetailVO.getDealType().equals("deposit")||dealDetailVO.getDealType().equals("withdraw")){
			System.out.println(paramMap);
			List<DealDetailVO> list = dealDetailDAO.getDetailByTypePaging(dealDetailVO);
			System.out.println(list);
			int total=dealDetailDAO.numberOfContentByType(dealDetailVO);
			PagingBean paging=new PagingBean(total,page,pagingConfig);
			DealListVO dvo=new DealListVO(list,paging);
			return dvo;
		}
		
		List<DealDetailVO> list = dealDetailDAO.getDetailPaging(dealDetailVO);
		System.out.println(1);
		System.out.println(list);
		int total=dealDetailDAO.numberOfContent(dealDetailVO);
		System.out.println(total);
		PagingBean paging=new PagingBean(total,page,pagingConfig);
		DealListVO dvo=new DealListVO(list,paging);
		System.out.println("되는거야?");
		System.out.println(dvo);
		return dvo;*/
	}
	@Override
	public DealListVO getDetailPaging(DealDetailVO dealDetailVO,int page){
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String page1 = String.valueOf(page);
		String numberOfContent = String.valueOf(pagingConfig.get("numberOfContent"));
		paramMap.put("page", page1);
		paramMap.put("numberOfContent", numberOfContent);
		paramMap.put("accountNo", dealDetailVO.getAccountNo());
		paramMap.put("startDay", dealDetailVO.getStartDay());
		String end = dealDetailVO.getEndDay();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date end1;
		try {
			end1 = sdf.parse(end);
			Calendar cal = Calendar.getInstance();
			cal.setTime(end1);
			cal.add(Calendar.DATE, 1);
			String endDay = sdf.format(cal.getTime());
			dealDetailVO.setEndDay(endDay);
			paramMap.put("endDay", dealDetailVO.getEndDay());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("아아아아아");
		
		if(dealDetailVO.getDealType().equals("deposit")||dealDetailVO.getDealType().equals("withdraw")){
			System.out.println(paramMap);
			List<DealDetailVO> list = dealDetailDAO.getDetailByTypePaging(paramMap);
			System.out.println(list);
			int total=dealDetailDAO.numberOfContentByType(dealDetailVO);
			PagingBean paging=new PagingBean(total,page,pagingConfig);
			DealListVO dvo=new DealListVO(list,paging);
			return dvo;
			
		}
		System.out.println(paramMap);

		List<DealDetailVO> list = dealDetailDAO.getDetailPaging(paramMap);
		System.out.println(1);
		System.out.println(list);
		int total=dealDetailDAO.numberOfContent(dealDetailVO);
		System.out.println(total);
		PagingBean paging=new PagingBean(total,page,pagingConfig);
		DealListVO dvo=new DealListVO(list,paging);
		System.out.println("되는거야?");
		System.out.println(dvo);
		return dvo;

	}
	
/*	@Override
	public List<DealDetailVO> getDetailByGap(String gapChecked, DealDetailVO dealDetailVO){
		System.out.println(2);
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
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
		String dealType = dealDetailVO.getDealType();
		System.out.println(dealType);
		if(dealDetailVO.getDealType().equals("deposit")||dealDetailVO.getDealType().equals("withdraw")){
			//System.out.println("뀨뀨");
			return dealDetailDAO.getDetailByType(dealDetailVO);
		}
		return dealDetailDAO.getDetail(dealDetailVO);	
		//return dealDetailDAO.getDetail(dealDetailVO);
	
		System.out.println(2);
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
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
		String dealType = dealDetailVO.getDealType();
		System.out.println(dealType);
		if(dealDetailVO.getDealType().equals("deposit")||dealDetailVO.getDealType().equals("withdraw")){
			//System.out.println("뀨뀨");
			return dealDetailDAO.getDetailByType(dealDetailVO);
		}
		return dealDetailDAO.getDetail(dealDetailVO);	
		//return dealDetailDAO.getDetail(dealDetailVO);
	}*/
	@Override
	public DealListVO getDetailByGapPaging(String gapChecked, DealDetailVO dealDetailVO,int page){
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String page1 = String.valueOf(page);
		String numberOfContent = String.valueOf(pagingConfig.get("numberOfContent"));
		paramMap.put("page", page1);
		paramMap.put("numberOfContent", numberOfContent);
		paramMap.put("accountNo", dealDetailVO.getAccountNo());
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, 1);
		Date d = cal.getTime();
		String endDay = sdf.format(d);
		System.out.println("today" + endDay);
		dealDetailVO.setEndDay(endDay);
		paramMap.put("endDay", dealDetailVO.getEndDay());
		if(gapChecked.equals("today")){
			cal.setTime(today);
			cal.add(Calendar.DATE, 0);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		}else if (gapChecked.equals("oneMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -1);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		}else if (gapChecked.equals("threeMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -3);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		}else if (gapChecked.equals("sixMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -6);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		}else if(gapChecked.equals("oneYear")){
			cal.setTime(today);
			cal.add(Calendar.YEAR, -1);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		}
		String dealType = dealDetailVO.getDealType();
		System.out.println(dealType);
		if(dealDetailVO.getDealType().equals("deposit")||dealDetailVO.getDealType().equals("withdraw")){
			//System.out.println("뀨뀨");
			System.out.println(paramMap);
			List<DealDetailVO> list = dealDetailDAO.getDetailByTypePaging(paramMap);
			System.out.println(list);
			int total=dealDetailDAO.numberOfContentByType(dealDetailVO);
			PagingBean paging=new PagingBean(total,page,pagingConfig);
			DealListVO dvo=new DealListVO(list,paging);
			return dvo;
		}
		System.out.println(paramMap);

		List<DealDetailVO> list = dealDetailDAO.getDetailPaging(paramMap);
		System.out.println(1);
		System.out.println(list);
		int total=dealDetailDAO.numberOfContent(dealDetailVO);
		System.out.println(total);
		PagingBean paging=new PagingBean(total,page,pagingConfig);
		DealListVO dvo=new DealListVO(list,paging);
		System.out.println("되는거야?");
		System.out.println(dvo);
		return dvo;
	
	}
	@Override
	public int numberOfContent(DealDetailVO dealDetailVO){
		return dealDetailDAO.numberOfContent(dealDetailVO);
	}
	@Override
	public int numberOfContentByType(DealDetailVO dealDetailVO){
		return dealDetailDAO.numberOfContentByType(dealDetailVO);
	}
}
