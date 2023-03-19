package ITzy.OTT.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import ITzy.OTT.dto.MemberDto;
import ITzy.OTT.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public String main() {
//		System.out.println("MemberController login " + new Date());
		return "main";
	}
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
//		System.out.println("MemberController login " + new Date());
		return "login";
	}
	
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
//		System.out.println("MemberController regi " + new Date());
		return "regi";
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST)
	public String idcheck(String id) {
//		System.out.println("MemberController idcheck " + new Date());
		
		boolean isS = service.idCheck(id);
		if(isS) {
			return "NO";		// id가 있음
		}
		return "YES";			// id가 없음
	}	
	
	
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	public String regiAf(Model model, MemberDto dto) {
//		System.out.println("MemberController regiAf " + new Date());
		boolean isS = service.addMember(dto);
		String message = "";
		if (isS) {
			message = "MEMBER_ADD_YES";
		}else {
			message = "MEMBER_ADD_NO";
		}
		model.addAttribute("message", message);
		return "message";
	}
	
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String loginAf(HttpServletRequest req, Model model,MemberDto dto) {
//		System.out.println("MemberController login " + new Date());
		
		MemberDto mem = service.login(dto);
		String msg ="";
		if (mem != null) {
			req.getSession().setAttribute("login", mem);
			req.getSession().setMaxInactiveInterval(7200);
			msg = "LOGIN_OK"; 
			System.out.println();
		}else {
			msg = "LOGIN_FAIL"; 
			System.out.println();
		}
		model.addAttribute("login",msg);
		return "message";
	}
	@RequestMapping(value = "BizloginAf.do", method = RequestMethod.POST)
	public String BizloginAf(HttpServletRequest req, Model model,MemberDto dto) {
//		System.out.println("MemberController login " + new Date());
		
		MemberDto mem = service.Bizlogin(dto);
		String msg ="";
		if (mem != null) {
			req.getSession().setAttribute("login", mem);
			req.getSession().setMaxInactiveInterval(7200);
			msg = "LOGIN_OK"; 
			System.out.println();
		}else {
			msg = "LOGIN_FAIL"; 
			System.out.println();
		}
		model.addAttribute("login",msg);
		return "message";
	}
	
	@RequestMapping(value = "regiAfBiz.do", method = RequestMethod.POST)
	public String regiAfBiz(Model model, MemberDto dto) {
//		System.out.println("MemberController regiAf " + new Date());
		boolean isS = service.BizAadmember(dto);
		String message = "";
		if (isS) {
			message = "MEMBER_ADD_YES";
		}else {
			message = "MEMBER_ADD_NO";
		}
		model.addAttribute("message", message);
		return "message";
	}


	
	// session check
	@RequestMapping(value = "sessionOut.do", method = RequestMethod.GET)
	public String sessionOut(Model model) {
		String sessionOut = "logout";
		model.addAttribute("sessionOut", sessionOut);
		return "message";
	}
}