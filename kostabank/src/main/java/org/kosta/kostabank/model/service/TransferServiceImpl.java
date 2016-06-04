package org.kosta.kostabank.model.service;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.TransferDAO;
import org.kosta.kostabank.model.vo.TransferVO;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {
	@Resource
	private TransferDAO transferDAO;
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.service.TransferService#transfer(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int transfer(TransferVO tvo){
		return transferDAO.transfer(tvo);
	}
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.service.TransferService#checkBal(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int checkBal(TransferVO tvo){
		return transferDAO.checkBal(tvo);
	}
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.service.TransferService#deposit(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int deposit(TransferVO tvo){
		return transferDAO.deposit(tvo);
	}
	
	/* (non-Javadoc)
	 * @see org.kosta.kostabank.model.service.TransferService#withdraw(org.kosta.kostabank.model.vo.TransferVO)
	 */
	@Override
	public int withdraw(TransferVO tvo){
		return transferDAO.withdraw(tvo);
	}

	

}
