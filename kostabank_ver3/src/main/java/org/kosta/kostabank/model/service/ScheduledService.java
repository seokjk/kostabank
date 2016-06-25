package org.kosta.kostabank.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.AccountDAO;
import org.kosta.kostabank.model.dao.LoanDAO;
import org.kosta.kostabank.model.dao.SavingsDAO;
import org.kosta.kostabank.model.vo.LoanVO;
import org.kosta.kostabank.model.vo.SavingsVO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {
	@Resource
	private AccountDAO accountDAO;
	@Resource
	private SavingsDAO savingsDAO;
	@Resource
	private LoanDAO loanDAO;
	   //fixedDelay=20000
	   @Scheduled(cron = "0 0 0 27 * ?")
	   public void savingsTransfer(){
	      savingsDAO.deposit();
	      List<SavingsVO> list = savingsDAO.savingsList();
	      savingsDAO.withdraw(list);
	      savingsDAO.transfer(list);
	   }
	 //fixedDelay=20000
	   @Scheduled(cron = "0 0 13 * * ?")
	   public void savingsUpdate(){
	      List<SavingsVO> list = savingsDAO.salvation();
	      savingsDAO.withdraw(list);
	      savingsDAO.reset(list);
	      savingsDAO.transfer(list);
	   }
	   
	/*   @Transactional */
	 //fixedDelay=20000
	@Scheduled(cron="0 0 0 28 * ?")
	public void withdraw(){
	   loanDAO.nowBalance();
	   loanDAO.withdraw();
	   List<LoanVO>list= loanDAO.selectDealDetail();
	   loanDAO.transfer(list);
	}
	  
	/*@Transactional*/
	//fixedDelay=20000
	@Scheduled(cron="0 0 12 * * ?")
	public void checkBalance(){   
	   List<LoanVO> list = loanDAO.dailyCheckBalance();
	   System.out.println(list);
	   loanDAO.dailyCheckWithdraw(list);
	   loanDAO.transfer(list);
	   loanDAO.dailyCheckUpdate(list);
	}
	//입출금 : 매일 12시에 일수와 잔액합계가 업데이트
	@Scheduled(cron="0 0 12 * * ?")
	public void scheduled() {
		accountDAO.scheduled();
		System.out.println("scheduled");
	}
	//입출금 : 각 금리달에 알맞은 달이 되면 업데이트
	@Scheduled(cron="0 0 0 28 1 ?")
	public void ratesMonth1() {
		accountDAO.ratesMonth(1);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 2 ?")
	public void ratesMonth2() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 3 ?")
	public void ratesMonth3() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(3);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 4 ?")
	public void ratesMonth4() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(4);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 5 ?")
	public void ratesMonth5() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(5);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 6 ?")
	public void ratesMonth6() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(3);
		accountDAO.ratesMonth(6);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 7 ?")
	public void ratesMonth7() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(7);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 8 ?")
	public void ratesMonth8() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(4);
		accountDAO.ratesMonth(8);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 9 ?")
	public void ratesMonth9() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(3);
		accountDAO.ratesMonth(9);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 10 ?")
	public void ratesMonth10() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(5);
		accountDAO.ratesMonth(10);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 11 ?")
	public void ratesMonth11() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(11);
		System.out.println("ratesMonth");
	}
	@Scheduled(cron="0 0 0 28 12 ?")
	public void ratesMonth12() {
		accountDAO.ratesMonth(1);
		accountDAO.ratesMonth(2);
		accountDAO.ratesMonth(3);
		accountDAO.ratesMonth(4);
		accountDAO.ratesMonth(6);
		accountDAO.ratesMonth(12);
		System.out.println("ratesMonth");
	}
}
