package org.kosta.kostabank.model.dao;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.SecureCardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SecureCardDAOImpl implements SecureCardDAO {
	@Resource
	private SqlSessionTemplate template;

	public void registerSecureCard(SecureCardVO vo){
		template.insert("secure.registerSecureCard",vo);	
	}

	@Override
	public void updateSecurityCard(SecureCardVO vo) {
		template.update("secure.updateSecurityCard",vo);
	}

	@Override
	public SecureCardVO selectSecureCard(String security_card) {
		return template.selectOne("secure.selectSecureCard",security_card);
	}

	@Override
	public void updateSecureCard(SecureCardVO selectVO) {
		template.update("secure.updateSecureCard",selectVO);
	}

	@Override
	public void deleteSecureCard(String security_card) {
		template.delete("secure.deleteSecureCard",security_card);
	}

	@Override
	public void deleteupdateSecurity_card(String email) {
		template.update("secure.deleteupdateSecureCard",email);
	}

	@Override
	public void secureCardFail(String security_card) {
		template.update("secure.secureCardFail",security_card);
	}

	@Override
	public void secureCardOK(String security_card) {
		template.update("secure.secureCardOK",security_card);
	}




}
