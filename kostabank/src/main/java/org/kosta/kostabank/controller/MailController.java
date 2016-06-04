package org.kosta.kostabank.controller;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.kostabank.model.service.CustomerService;
import org.kosta.kostabank.model.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
	@Autowired
	private JavaMailSender mailSender;
	@Resource
	private CustomerService customerService;
	@RequestMapping(value = "certificateEmail.bank", method = RequestMethod.POST)
	@ResponseBody
	public String certificateEmail(String email, HttpServletRequest request) {
		String temp = "";
		for (int i = 0; i < 4; i++) {
			int certificate = (int) (Math.random() * 10);
			temp += certificate;
		}
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, "utf-8");
			messageHelper.setFrom("kosta116bank@gmail.com");
			messageHelper.setTo(email);
			messageHelper.setSubject("email 인증");
			messageHelper.setText("<br><br><br>인증번호 : " + temp);
			mailSender.send(message);
			HttpSession session = request.getSession();
			session.setAttribute("certificate", temp);
			session.setMaxInactiveInterval(180000);
		} catch (Exception e) {
			System.out.println(e);
		}
		return temp;
	}
	@RequestMapping(value = "findPassword.bank", method = RequestMethod.POST)
	@ResponseBody
	public int findPassword(CustomerVO vo, HttpServletRequest request) {
		String temp = "";
		for (int i = 0; i < 6; i++) {
			int certificate = (int) (Math.random() * 10);
			temp += certificate;
		}
		vo.setPassword(temp);
		int result = customerService.tempPassword(vo);
		try {
			if(result != 0){
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message,
						true, "utf-8");
				messageHelper.setFrom("kosta116bank@gmail.com");
				messageHelper.setTo(vo.getEmail());
				messageHelper.setSubject("임시비밀번호 발급");
				messageHelper.setText("<br><br><br>임시비밀번호 : " + temp);
				mailSender.send(message);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
