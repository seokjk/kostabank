package org.kosta.kostabank.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kosta.kostabank.model.vo.DealDetailVO;

public interface DealDetailDAO {

	List<DealDetailVO> getDetail(DealDetailVO dealDetailVO);

	List<DealDetailVO> getDetailByType(DealDetailVO dealDetailVO);

	List<DealDetailVO> getDetailPaging(HashMap<String, String> paramMap);

	List<DealDetailVO> getDetailByTypePaging(HashMap<String, String> paramMap);

	int numberOfContent(DealDetailVO dealDetailVO);

	int numberOfContentByType(DealDetailVO dealDetailVO);


}
