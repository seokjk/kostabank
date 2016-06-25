package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.SavingsVO;

public interface SavingsDAO {
   public abstract List<AccountTypeVO> savingsProductlist(String accountType);
   public AccountRatesVO accountNameFindAccountList(String accountName);
   double getRatesByTerm(int accountSeq);
   void createAccount(AccountVO vo);
   void createSavings(SavingsVO savingsVO);
   int findRatesSeq(AccountRatesVO accountRatesVO);
   List<AccountRatesVO> accountSeqByName(String accountName);
   int getTermBySeq(int accountSeq);
   public void deposit();
   public List<SavingsVO> savingsList();
   public void withdraw(List<SavingsVO> list);
   public void transfer(List<SavingsVO> list);
   public List<SavingsVO> salvation();
   public void reset(List<SavingsVO> list);
   public abstract int selectRatesBySeq(String seq);
}