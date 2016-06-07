package org.kosta.kostabank.model.dao;

import java.util.List;

import org.kosta.kostabank.model.vo.DealDetailVO;

public interface DealDetailDAO {

	List<DealDetailVO> getDetail(DealDetailVO dealDetailVO);

	//List<DealDetailVO> getDetailByGap(String gapChecked);

}
