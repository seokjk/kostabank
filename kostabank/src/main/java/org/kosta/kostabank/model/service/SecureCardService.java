package org.kosta.kostabank.model.service;

import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.SecureCardVO;

public interface SecureCardService {

	public abstract void registerSecureCard(SecureCardVO vo,CustomerVO customerVo);

	public abstract SecureCardVO selectSecureCard(String security_card);

	public abstract void updateSecureCard(SecureCardVO selectVO);

	public abstract void deleteSecureCard(CustomerVO vo);


}