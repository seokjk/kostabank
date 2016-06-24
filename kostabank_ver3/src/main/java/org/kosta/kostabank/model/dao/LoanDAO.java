package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.LoanAccountVO;
import org.kosta.kostabank.model.vo.LoanVO;

public interface LoanDAO {
   
   int checkLoan(int maxMoney);

   LoanAccountVO loanData(String name);

   LoanAccountVO checkPeriod(String period);


   void createAccount(AccountVO accountVO);

   void createLoan(LoanVO vo);

   LoanVO selectLoan(String accountNo);



   void withdraw();

   void nowBalance();

   void transfer(List<LoanVO> list);

   List<LoanVO> selectDealDetail();

   List<LoanVO> dailyCheckBalance();

   void dailyCheckWithdraw(List<LoanVO> list);

   void dailyCheckUpdate(List<LoanVO> list);


}