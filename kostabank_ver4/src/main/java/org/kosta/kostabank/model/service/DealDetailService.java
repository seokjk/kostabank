package org.kosta.kostabank.model.service;

import java.util.List;

import org.kosta.kostabank.model.vo.AccountVO;
import org.kosta.kostabank.model.vo.DealDetailVO;
import org.kosta.kostabank.model.vo.DealListVO;
import org.kosta.kostabank.model.vo.TransferVO;
//
public interface DealDetailService {


		List<DealDetailVO> getDetail(DealDetailVO dealDetailVO);

		DealListVO getDetailPaging(DealDetailVO dealDetailVO, int page);

		int numberOfContent(DealDetailVO dealDetailVO);

		int numberOfContentByType(DealDetailVO dealDetailVO);

		DealListVO getDetailByGapPaging(String gapChecked,DealDetailVO dealDetailVO, int page);

		DealDetailVO insertTransDetail(DealDetailVO dealDetailVO);

		AccountVO insertTrans(TransferVO tvo);
}
