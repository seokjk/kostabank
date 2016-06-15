package org.kosta.kostabank.model.dao;

import org.kosta.kostabank.model.vo.SecureCardVO;

public interface SecureCardDAO {
	public void registerSecureCard(SecureCardVO vo);

	public void updateSecurityCard(SecureCardVO vo);

	public SecureCardVO selectSecureCard(String security_card);

	public void updateSecureCard(SecureCardVO selectVO);

	public void deleteSecureCard(String security_card);

	public void deleteupdateSecurity_card(String email);

	public void secureCardFail(String security_card);

	public void secureCardOK(String security_card);



}