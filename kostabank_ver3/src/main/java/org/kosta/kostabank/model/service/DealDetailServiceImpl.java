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
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.DealListVO;
import org.kosta.kostabank.model.vo.PagingBean;
import org.kosta.kostabank.model.vo.TransferVO;
import org.springframework.stereotype.Service;

@Service
public class DealDetailServiceImpl implements DealDetailService {
	@Resource
	private DealDetailDAO dealDetailDAO;
	@Resource
	private AccountDAO accountDAO;
	@Resource
	private Map<String, Integer> pagingConfig;

	@Override
	public List<DealDetailVO> getDetail(DealDetailVO dealDetailVO) {
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
		if (dealDetailVO.getDealType().equals("deposit")
				|| dealDetailVO.getDealType().equals("withdraw")) {
			return dealDetailDAO.getDetailByType(dealDetailVO);
		}
		return dealDetailDAO.getDetail(dealDetailVO);

	}

	@Override
	public DealListVO getDetailPaging(DealDetailVO dealDetailVO, int page) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String page1 = String.valueOf(page);
		String numberOfContent = String.valueOf(pagingConfig
				.get("numberOfContent"));
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
		String dealType = dealDetailVO.getDealType();
		if (dealDetailVO.getDealType().equals("deposit")
				|| dealDetailVO.getDealType().equals("withdraw")) {
			paramMap.put("dealType", dealType);
			System.out.println(paramMap);
			List<DealDetailVO> list = dealDetailDAO
					.getDetailByTypePaging(paramMap);
			System.out.println(list);
			int total = dealDetailDAO.numberOfContentByType(dealDetailVO);
			System.out.println(total);
			PagingBean paging = new PagingBean(total, page, pagingConfig);
			DealListVO dvo = new DealListVO(list, paging);
			return dvo;

		}
		System.out.println(paramMap);

		List<DealDetailVO> list = dealDetailDAO.getDetailPaging(paramMap);
		System.out.println(list);
		int total = dealDetailDAO.numberOfContent(dealDetailVO);
		System.out.println(total);
		PagingBean paging = new PagingBean(total, page, pagingConfig);
		DealListVO dvo = new DealListVO(list, paging);
		System.out.println(dvo);
		return dvo;

	}

	@Override
	public DealListVO getDetailByGapPaging(String gapChecked,
			DealDetailVO dealDetailVO, int page) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		String page1 = String.valueOf(page);
		String numberOfContent = String.valueOf(pagingConfig
				.get("numberOfContent"));
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
		if (gapChecked.equals("today")) {
			cal.setTime(today);
			cal.add(Calendar.DATE, 0);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		} else if (gapChecked.equals("oneMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -1);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		} else if (gapChecked.equals("threeMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -3);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		} else if (gapChecked.equals("sixMonth")) {
			cal.setTime(today);
			cal.add(Calendar.MONTH, -6);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		} else if (gapChecked.equals("oneYear")) {
			cal.setTime(today);
			cal.add(Calendar.YEAR, -1);
			String startDay = sdf.format(cal.getTime());
			System.out.println("startDay" + startDay);
			dealDetailVO.setStartDay(startDay);
			paramMap.put("startDay", dealDetailVO.getStartDay());
		}
		String dealType = dealDetailVO.getDealType();
		System.out.println("dealType:" + dealType);
		if (dealDetailVO.getDealType().equals("deposit")
				|| dealDetailVO.getDealType().equals("withdraw")) {
			System.out.println("타입있음");
			paramMap.put("dealType", dealType);
			System.out.println(paramMap);

			List<DealDetailVO> list = dealDetailDAO
					.getDetailByTypePaging(paramMap);
			System.out.println(list);
			int total = dealDetailDAO.numberOfContentByType(dealDetailVO);
			PagingBean paging = new PagingBean(total, page, pagingConfig);
			DealListVO dvo = new DealListVO(list, paging);
			System.out.println(dvo);
			return dvo;
		}
		System.out.println(paramMap);
		System.out.println("타입없음");
		List<DealDetailVO> list = dealDetailDAO.getDetailPaging(paramMap);
		System.out.println(list);
		int total = dealDetailDAO.numberOfContent(dealDetailVO);
		System.out.println(total);
		PagingBean paging = new PagingBean(total, page, pagingConfig);
		DealListVO dvo = new DealListVO(list, paging);
		System.out.println(dvo);
		return dvo;

	}

	@Override
	public int numberOfContent(DealDetailVO dealDetailVO) {
		return dealDetailDAO.numberOfContent(dealDetailVO);
	}

	@Override
	public int numberOfContentByType(DealDetailVO dealDetailVO) {
		return dealDetailDAO.numberOfContentByType(dealDetailVO);
	}

	@Override
	public DealDetailVO insertTransDetail(DealDetailVO dealDetailVO) {
		return dealDetailDAO.insertTransDetail(dealDetailVO);
	}

	@Override
	public AccountVO insertTrans(TransferVO tvo) {
		DealDetailVO dvo = new DealDetailVO(tvo.getaccount(),
				tvo.getOtheraccountNo(), "withdraw", tvo.getMoney());
		accountDAO.withdraw(dvo);
		dealDetailDAO.insertTransDetail(dvo);
		dvo = new DealDetailVO(tvo.getOtheraccountNo(),
				tvo.getaccount(), "deposit", tvo.getMoney());
		accountDAO.deposit(dvo);
		dealDetailDAO.insertTransDetail(dvo);
		String name = accountDAO.accountBAndN(tvo.getaccount()).getCustomerVO().getName();
		long balance = accountDAO.accountBAndN(tvo.getOtheraccountNo()).getBalance();
		AccountVO vo = new AccountVO(balance, new CustomerVO(null, null, name, null, null, null, null, 0));
		return vo;
	}
}
