package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.AccountRatesVO;
import org.kosta.kostabank.model.vo.AccountTypeVO;
import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.SavingsVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SavingsDAOImpl implements SavingsDAO {
@Resource
private SqlSessionTemplate template;

   @Override
   public List<AccountTypeVO> savingsProductlist(String accountType){
      return template.selectList("savings.savingsProductlist",accountType);
   }
   @Override
   public AccountRatesVO accountNameFindAccountList(String accountName){
      return template.selectOne("savings.accountNameFindAccountList",accountName);
   }
   @Override
   public double getRatesByTerm(int accountSeq){
      return template.selectOne("savings.getRatesByTerm",accountSeq);
   }
   @Override
   public void createAccount(AccountVO accountVO) {
      template.insert("savings.createAccount", accountVO);
   }
   @Override
   public void createSavings(SavingsVO savingsVO){
      template.insert("savings.createSavings", savingsVO);
   }
   @Override
   public int findRatesSeq(AccountRatesVO accountRatesVO){
      return template.selectOne("savings.findRatesSeq", accountRatesVO);
   }
   @Override
   public List<AccountRatesVO>accountSeqByName(String accountName){
      return template.selectList("savings.accountSeqByName", accountName);
   }
   @Override
   public int getTermBySeq(int accountSeq){
      return template.selectOne("savings.getTermBySeq",accountSeq);
   }
   public void deposit(){
      template.update("savings.deposit");
   }
   public List<SavingsVO> savingsList(){
      return template.selectList("savings.savingsList");
   }
   public void withdraw(List<SavingsVO> list){
    for(int i=0;i<list.size();i++){
       template.update("savings.withdraw",list.get(i));
    }
   }
   public void transfer(List<SavingsVO> list){
      for(int i=0;i<list.size();i++){
         template.insert("savings.transferWithdraw",list.get(i));
         template.insert("savings.transferDeposit",list.get(i));
      }
   }
   public List<SavingsVO> salvation(){
      return template.selectList("savings.salvation");
   }
   public void reset(List<SavingsVO> list){
      for(int i=0;i<list.size();i++){
         template.update("savings.reset",list.get(i));
      }
   }
   @Override
   public int selectRatesBySeq(String seq) {
      return template.selectOne("savings.selectRatesBySeq",seq);
   }
}