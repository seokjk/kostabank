package org.kosta.kostabank.model.service;

import java.text.ParseException;
import java.util.List;

import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.DealListVO;
//
public interface DealDetailService {

	//List<DealDetailVO> getDetail(DealDetailVO dealDetailVO);

	//List<DealDetailVO> getDetailByGap(String gapChecked, DealDetailVO dealDetailVO);

	//DealListVO getDetail(DealDetailVO dealDetailVO, String page);

	//DealListVO getDetail(DealDetailVO dealDetailVO, int page);

		List<DealDetailVO> getDetail(DealDetailVO dealDetailVO);

		DealListVO getDetailPaging(DealDetailVO dealDetailVO, int page);

		int numberOfContent(DealDetailVO dealDetailVO);

		int numberOfContentByType(DealDetailVO dealDetailVO);

		DealListVO getDetailByGapPaging(String gapChecked,DealDetailVO dealDetailVO, int page);
}
