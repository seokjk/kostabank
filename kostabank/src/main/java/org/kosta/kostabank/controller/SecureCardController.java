package org.kosta.kostabank.controller;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.kostabank.model.service.CustomerService;
import org.kosta.kostabank.model.service.SecureCardService;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.kosta.kostabank.model.vo.SecureCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecureCardController {
	@Autowired
	private JavaMailSender mailSender;
	@Resource
	private SecureCardService service;
	@Resource
	private CustomerService customerService;
	@RequestMapping("registerSecureCard.bank")
	public String registerSecureCard(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO)session.getAttribute("loginInfo");
		if(vo!=null){
			SecureCardVO scvo = new SecureCardVO(vo.getEmail());
			service.registerSecureCard(scvo,vo);
			session.setAttribute("loginInfo", vo);
			SecureCardVO selectVO = service.selectSecureCard(vo.getSecurity_card());
			try {
				String msg="";
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "utf-8");
				messageHelper.setFrom("kosta116bank@gmail.com");
				messageHelper.setTo(vo.getEmail());
				messageHelper.setSubject(vo.getName()+"님 KANG BANK 보안카드 발급");
				msg="<html><head></head><body><br><br><br>보안카드 NO: "+vo.getSecurity_card()+"<br><form><table border='1' cellpadding='5'><tr><td>1</td><td>"+selectVO.getOne()+"</td><td>2</td><td>"+selectVO.getTwo()+"</td><td>3</td><td>"+selectVO.getThree()+"</td><td>4</td><td>"+selectVO.getFour()+"</td><td>5</td><td>"+selectVO.getFive()+"</td></tr>";
				msg+="<tr><td>6</td><td>"+selectVO.getSix()+"</td><td>7</td><td>"+selectVO.getSeven()+"</td><td>8</td><td>"+selectVO.getEight()+"</td><td>9</td><td>"+selectVO.getNine()+"</td><td>10</td><td>"+selectVO.getTen()+"</td></tr>";
				msg+="<tr><td>11</td><td>"+selectVO.getEleven()+"</td><td>12</td><td>"+selectVO.getTwelve()+"</td><td>13</td><td>"+selectVO.getThirteen()+"</td><td>14</td><td>"+selectVO.getFourteen()+"</td><td>15</td><td>"+selectVO.getFifteen()+"</td></tr>";
				msg+="<tr><td>16</td><td>"+selectVO.getSixteen()+"</td><td>17</td><td>"+selectVO.getSeventeen()+"</td><td>18</td><td>"+selectVO.getEighteen()+"</td><td>19</td><td>"+selectVO.getNineteen()+"</td><td>20</td><td>"+selectVO.getTwenty()+"</td></tr>";
				msg+="<tr><td>21</td><td>"+selectVO.getTwenty_one()+"</td><td>22</td><td>"+selectVO.getTwenty_two()+"</td><td>23</td><td>"+selectVO.getTwenty_three()+"</td><td>24</td><td>"+selectVO.getTwenty_four()+"</td><td>25</td><td>"+selectVO.getTwenty_five()+"</td></tr>";
				msg+="<tr><td>26</td><td>"+selectVO.getTwenty_six()+"</td><td>27</td><td>"+selectVO.getTwenty_seven()+"</td><td>28</td><td>"+selectVO.getTwenty_eight()+"</td><td>29</td><td>"+selectVO.getTwenty_nine()+"</td><td>30</td><td>"+selectVO.getThirty()+"</td></tr></table></form></body></html>";
				messageHelper.setText(msg,true);
				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return ("redirect:home.bank");
	}
	
	@RequestMapping("reissueSecureCard.bank")
	public String reissueSecureCard(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO)session.getAttribute("loginInfo");
		if(vo!=null){
			SecureCardVO selectVO = service.selectSecureCard(vo.getSecurity_card());
			service.updateSecureCard(selectVO);	
			try {
				String msg="";
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "utf-8");
				messageHelper.setFrom("kosta116bank@gmail.com");
				messageHelper.setTo(vo.getEmail());
				messageHelper.setSubject(vo.getName()+"님 KANG BANK 보안카드 재발급");
				msg="<html><head></head><body><br><br><br>보안카드 NO: "+vo.getSecurity_card()+"<br><form><table border='1'><tr><td>1</td><td>"+selectVO.getOne()+"</td><td>2</td><td>"+selectVO.getTwo()+"</td><td>3</td><td>"+selectVO.getThree()+"</td><td>4</td><td>"+selectVO.getFour()+"</td><td>5</td><td>"+selectVO.getFive()+"</td></tr>";
				msg+="<tr><td>6</td><td>"+selectVO.getSix()+"</td><td>7</td><td>"+selectVO.getSeven()+"</td><td>8</td><td>"+selectVO.getEight()+"</td><td>9</td><td>"+selectVO.getNine()+"</td><td>10</td><td>"+selectVO.getTen()+"</td></tr>";
				msg+="<tr><td>11</td><td>"+selectVO.getEleven()+"</td><td>12</td><td>"+selectVO.getTwelve()+"</td><td>13</td><td>"+selectVO.getThirteen()+"</td><td>14</td><td>"+selectVO.getFourteen()+"</td><td>15</td><td>"+selectVO.getFifteen()+"</td></tr>";
				msg+="<tr><td>16</td><td>"+selectVO.getSixteen()+"</td><td>17</td><td>"+selectVO.getSeventeen()+"</td><td>18</td><td>"+selectVO.getEighteen()+"</td><td>19</td><td>"+selectVO.getNineteen()+"</td><td>20</td><td>"+selectVO.getTwenty()+"</td></tr>";
				msg+="<tr><td>21</td><td>"+selectVO.getTwenty_one()+"</td><td>22</td><td>"+selectVO.getTwenty_two()+"</td><td>23</td><td>"+selectVO.getTwenty_three()+"</td><td>24</td><td>"+selectVO.getTwenty_four()+"</td><td>25</td><td>"+selectVO.getTwenty_five()+"</td></tr>";
				msg+="<tr><td>26</td><td>"+selectVO.getTwenty_six()+"</td><td>27</td><td>"+selectVO.getTwenty_seven()+"</td><td>28</td><td>"+selectVO.getTwenty_eight()+"</td><td>29</td><td>"+selectVO.getTwenty_nine()+"</td><td>30</td><td>"+selectVO.getThirty()+"</td></tr></table></form></body></html>";
				messageHelper.setText(msg,true);
				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return ("redirect:home.bank");
	}
	
	@RequestMapping("deleteSecureCard.bank")
	public String deleteSecureCard(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		CustomerVO vo = (CustomerVO) session.getAttribute("loginInfo");
		service.deleteSecureCard(vo);
		session.setAttribute("loginInfo", vo);
		return ("redirect:home.bank");
	}
}
