package org.kosta.kostabank.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.kosta.kostabank.model.dao.QNADAO;
import org.kosta.kostabank.model.vo.PagingBean;
import org.kosta.kostabank.model.vo.QNAListVO;
import org.kosta.kostabank.model.vo.QNAVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QNAServiceImpl implements QNAService {
   @Resource
   private QNADAO qnaDAO;
   @Resource(name = "pagingConfig")
   private Map<String, Integer> pagingConfig;

   /*
    * @Resource(name = "uploadPath") private String uploadPath;
    */

   // 목록 등록 (이메일로 자신것만 찾기위함)
   /*
    * (non-Javadoc)
    * 
    * @see
    * org.kosta.kostabank.model.service.QNAService#qnaList(java.lang.String,
    * int)
    */
   @Override
   public QNAListVO qnaList(int page, String email) {
      HashMap<String, String> paramMap = new HashMap<String, String>();
      String numOfCont = String.valueOf(pagingConfig.get("numberOfContent"));
      String pageValue = String.valueOf(page);
      paramMap.put("page", pageValue);
      paramMap.put("numOfCont", numOfCont);
      paramMap.put("email", email);
      List<QNAVO> list = qnaDAO.qnaList(paramMap);
      int total = qnaDAO.totalContent(email);
      PagingBean paging = new PagingBean(total, page, pagingConfig);
      QNAListVO Qlvo = new QNAListVO(list, paging);
      return Qlvo;
   }
   
   @Override
   public QNAListVO qnaNoList(int page) {
      HashMap<String, String> paramMap = new HashMap<String, String>();
      String numOfCont = String.valueOf(pagingConfig.get("numberOfContent"));
      String pageValue = String.valueOf(page);
      paramMap.put("page", pageValue);
      paramMap.put("numOfCont", numOfCont);
      List<QNAVO> list = qnaDAO.qnaNoList(paramMap);
      List<QNAVO> tempList = qnaDAO.tempList(paramMap);
      ArrayList<Integer> temp = new ArrayList<Integer>();
      for(int i = 0 ; i < list.size() ; i++){
    	  for(int j = 0 ; j < tempList.size() ; j++){
    		  if(list.get(i).getQnaNo()==tempList.get(j).getQnaNo()){
    			  temp.add(i);
    		  }
    	  }
      }
      for(int i = temp.size()-1 ; i >= 0 ; i--){
    	  int j = temp.get(i);
    	  list.remove(j);
      }
      int total = qnaDAO.NototalContent();
      PagingBean paging = new PagingBean(total, page, pagingConfig);
      QNAListVO Qlvo = new QNAListVO(list, paging);
      return Qlvo;
   }

   // (현재시간별)파일 첨부가 가능한 질문 등록
   /*
    * (non-Javadoc)
    * 
    * @see
    * org.kosta.kostabank.model.service.QNAService#qnaPosting(org.kosta.kostabank
    * .model.vo.QNAVO)
    */
   @Override
   public void qnaPosting(QNAVO vo) {
      String now = new SimpleDateFormat("yyyyMMdd_Hms").format(new Date());
      System.out.println(vo.getUploadFile().getOriginalFilename());
      if (vo.getUploadFile().getOriginalFilename().isEmpty()) {
         vo.setQnaFileAddress("");
      } else {
         vo.setQnaFileAddress("kangbank/upload/" + now + "_"
               + vo.getUploadFile().getOriginalFilename());
      }
      MultipartFile file = vo.getUploadFile();

      if (!file.isEmpty()) {
         qnaDAO.qnaPosting(vo);
         File uploadFile = new File(
               "C:\\Users\\kosta\\git\\kostabank\\kostabank_ver3\\src\\main\\webapp\\kangbank\\upload\\"
                     + now + "_" + file.getOriginalFilename());
         try {
            file.transferTo(uploadFile);

         } catch (Exception e) {
            e.printStackTrace();
         }
      } else {
         qnaDAO.qnaPosting(vo);
      }
   }

   public QNAVO showContent(int qnaNo) {
      return qnaDAO.showContent(qnaNo);
   }

   public void rePosting(QNAVO vo) {
      qnaDAO.rePosting(vo);
   }

   public void deleteRe(int qnaNo) {
      qnaDAO.deleteRe(qnaNo);
   }
}