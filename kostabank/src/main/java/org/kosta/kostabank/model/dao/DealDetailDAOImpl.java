package org.kosta.kostabank.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		System.out.println("55");
		return sqlSessionTemplate.selectList("deal.getDetail", dealDetailVO);
	}
	@Override
	public List<DealDetailVO> getDetailByType(DealDetailVO dealDetailVO) {
		return sqlSessionTemplate.selectList("deal.getDetailByType", dealDetailVO);
	} 
	@Override
	public List<DealDetailVO> getDetailPaging(HashMap<String, String> paramMap) {
		System.out.println("그냥 페이징 sql전");
		return sqlSessionTemplate.selectList("deal.getDetailPaging", paramMap);
	}
	@Override
	public List<DealDetailVO> getDetailByTypePaging(HashMap<String, String> paramMap) {
		System.out.println("타입 페이징 sql전");
		return sqlSessionTemplate.selectList("deal.getDetailByTypePaging", paramMap);
	} 
	@Override
	public int numberOfContent(DealDetailVO dealDetailVO){
		return sqlSessionTemplate.selectOne("deal.numberOfContent",dealDetailVO);
	}
	@Override
	public int numberOfContentByType(DealDetailVO dealDetailVO){
		return sqlSessionTemplate.selectOne("deal.numberOfContentByType",dealDetailVO);
	}
}




