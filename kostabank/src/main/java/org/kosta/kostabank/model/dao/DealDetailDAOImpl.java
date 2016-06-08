package org.kosta.kostabank.model.dao;

import java.util.List;

import javax.annotation.Resource;

import org.kosta.kostabank.model.vo.DealDetailVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DealDetailDAOImpl implements DealDetailDAO {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public List<DealDetailVO> getDetail(DealDetailVO dealDetailVO) {
		sqlSessionTemplate.selectList("deal.getDetail", dealDetailVO);
		System.out.println(3);
		return sqlSessionTemplate.selectList("deal.getDetail", dealDetailVO);
	}
	/*	@Override
	public List<DealDetailVO> getDetailByGap(String gapChecked) {
		return sqlSessionTemplate.selectList("deal.getDetailByGap", gapChecked);
	}*///
}




