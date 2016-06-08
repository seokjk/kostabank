package org.kosta.kostabank.model.service;

import java.text.ParseException;
import java.util.List;

import org.kosta.kostabank.model.vo.DealDetailVO;
//
public interface DealDetailService {

	List<DealDetailVO> getDetail(DealDetailVO dealDetailVO);

	List<DealDetailVO> getDetailByGap(String gapChecked, String accountNo);
}
