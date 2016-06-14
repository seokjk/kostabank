package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.TransferVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransferDAOImpl implements TransferDAO {
	@Resource
	private SqlSessionTemplate template;
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.TransferDAO#deposit(int)
	 */
	@Override
	public int deposit(int money){
		return template.update("transfer.deposit",money);
	}
	

	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.TransferDAO#withdraw(int)
	 */
	@Override
	public int withdraw(int money){
		return template.update("transfer.withdraw",money);
	}
	


	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.TransferDAO#checkBal(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int checkBal(String myaccountNo){
		return template.selectOne("transfer.checkBal",myaccountNo);
	}


	@Override
	public List<TransferVO> findAccount(){
		return template.selectList("transfer.findAccount");
	}
	@Override
	public int checkPw(int accountPass){
		return template.selectOne("transfer.checkPw",accountPass);
	}
	

}
