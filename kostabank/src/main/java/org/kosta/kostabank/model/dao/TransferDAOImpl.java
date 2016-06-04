package org.kosta.kostabank.model.dao;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.TransferVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransferDAOImpl implements TransferDAO {
	@Resource
	private SqlSessionTemplate template;
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.TransferDAO#deposit(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int deposit(TransferVO tvo){
		return template.update("transfer.deposit",tvo);
	}
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.TransferDAO#withdraw(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int withdraw(TransferVO tvo){
		return template.update("transfer.withdraw",tvo);
	}
	

	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.TransferDAO#checkBal(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int checkBal(TransferVO tvo){
		return template.selectOne("transfer.checkBal",tvo);
	}
	
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.dao.TransferDAO#transfer(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int transfer(TransferVO tvo){
		return template.selectOne("transfer.transfer",tvo);
	}
	

}
