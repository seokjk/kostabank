package org.kosta.kostabank.model.service;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountDAO;
import org.kosta.kostabank.model.dao.LoanDAO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {
	@Resource
	private AccountDAO accountDAO;
	@Resource
	private LoanDAO loanDAO;
	
	//대출 : 매달 15일 오전 10시 15분 balanceSum에 이자 업데이트
	@Scheduled(cron="0 15 10 15 * ?")
	public void balanceSumUpdate() {
		System.out.println("balanceSumUpdate");
		loanDAO.balanceSumUpdate();
	}
	//입출금 : 매일 12시에 일수와 잔액합계가 업데이트
	@Scheduled(cron="0 0 12 * * ?")
	public void scheduled() {
		System.out.println("scheduled");
		accountDAO.scheduled();
	}
	//입출금 : 각 금리달에 알맞은 달이 되면 업데이트
	@Scheduled(cron="0 0 0 28 1 ?")
	public void ratesMonth1() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
	}
	@Scheduled(cron="0 0 0 28 2 ?")
	public void ratesMonth2() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
	}
	@Scheduled(cron="0 0 0 28 3 ?")
	public void ratesMonth3() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(3);
	}
	@Scheduled(cron="0 0 0 28 4 ?")
	public void ratesMonth4() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(4);
	}
	@Scheduled(cron="0 0 0 28 5 ?")
	public void ratesMonth5() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(5);
	}
	@Scheduled(cron="0 0 0 28 6 ?")
	public void ratesMonth6() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(3);
		accountDAO.ratesMonth(6);
	}
	@Scheduled(cron="0 0 0 28 7 ?")
	public void ratesMonth7() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(7);
	}
	@Scheduled(cron="0 0 0 28 8 ?")
	public void ratesMonth8() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(4);
		accountDAO.ratesMonth(8);
	}
	@Scheduled(cron="0 0 0 28 9 ?")
	public void ratesMonth9() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(3);
		accountDAO.ratesMonth(9);
	}
	@Scheduled(cron="0 0 0 28 10 ?")
	public void ratesMonth10() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(5);
		accountDAO.ratesMonth(10);
	}
	@Scheduled(cron="0 0 0 28 11 ?")
	public void ratesMonth11() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(11);
	}
	@Scheduled(cron="0 0 0 28 12 ?")
	public void ratesMonth12() {
		System.out.println("ratesMonth");
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(3);
		accountDAO.ratesMonth(4);
		accountDAO.ratesMonth(6);
		accountDAO.ratesMonth(12);
	}
}
