package org.kosta.kostabank.model.service;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.SecureCardDAO;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.SecureCardVO;
import org.springframework.stereotype.Service;

@Service
public class SecureCardServiceImpl implements SecureCardService {
	@Resource
	private SecureCardDAO dao;
	@Override
	public void registerSecureCard(SecureCardVO scvo,CustomerVO customerVo){
		Random random = new Random();
		String one, two, three, four, five, six, seven, eight, nine, ten;
		String eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen, twenty;
		String twenty_one, twenty_two, twenty_three, twenty_four, twenty_five, twenty_six, twenty_seven, twenty_eight, twenty_nine, thirty;
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<30; i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<4; j++){
				sb.append(random.nextInt(10));
			}
			list.add(sb.toString());
		}
		SecureCardVO vo = new SecureCardVO(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5),list.get(6),list.get(7),list.get(8),list.get(9),list.get(10),list.get(11),list.get(12),list.get(13),list.get(14),list.get(15),list.get(16),list.get(17),list.get(18),list.get(19),list.get(20),list.get(21),list.get(22),list.get(23),list.get(24),list.get(25),list.get(26),list.get(27),list.get(28),list.get(29),scvo.getEmail());
		dao.registerSecureCard(vo);
		dao.updateSecurityCard(vo);
		customerVo.setSecurity_card(Integer.toString(vo.getNo()));
	}
	@Override
	public SecureCardVO selectSecureCard(String security_card) {
		return dao.selectSecureCard(security_card);
	}
	@Override
	public void updateSecureCard(SecureCardVO selectVO) {
		Random random = new Random();
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<30; i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<4; j++){
				sb.append(random.nextInt(10));
			}
			list.add(sb.toString());
		}
		selectVO.setOne(list.get(0));
		selectVO.setTwo(list.get(1));
		selectVO.setThree(list.get(2));
		selectVO.setFour(list.get(3));
		selectVO.setFive(list.get(4));
		selectVO.setSix(list.get(5));
		selectVO.setSeven(list.get(6));
		selectVO.setEight(list.get(7));
		selectVO.setNine(list.get(8));
		selectVO.setTen(list.get(9));
		selectVO.setEleven(list.get(10));
		selectVO.setTwelve(list.get(11));
		selectVO.setThirteen(list.get(12));
		selectVO.setFourteen(list.get(13));
		selectVO.setFifteen(list.get(14));
		selectVO.setSixteen(list.get(15));
		selectVO.setSeventeen(list.get(16));
		selectVO.setEighteen(list.get(17));
		selectVO.setNineteen(list.get(18));
		selectVO.setTwenty(list.get(19));
		selectVO.setTwenty_one(list.get(20));
		selectVO.setTwenty_two(list.get(21));
		selectVO.setTwenty_three(list.get(22));
		selectVO.setTwenty_four(list.get(23));
		selectVO.setTwenty_five(list.get(24));
		selectVO.setTwenty_six(list.get(25));
		selectVO.setTwenty_seven(list.get(26));
		selectVO.setTwenty_eight(list.get(27));
		selectVO.setTwenty_nine(list.get(28));
		selectVO.setThirty(list.get(29));
		dao.updateSecureCard(selectVO);
	}

	@Override
	public void deleteSecureCard(CustomerVO vo) {
		dao.deleteSecureCard(vo.getSecurity_card());
		dao.deleteupdateSecurity_card(vo.getEmail());
		vo.setSecurity_card("0");
	}
	@Override
	public void secureCardFail(String security_card) {
		dao.secureCardFail(security_card);
	}
	@Override
	public void secureCardOK(String security_card) {
		dao.secureCardOK(security_card);
	}
	
	

}
