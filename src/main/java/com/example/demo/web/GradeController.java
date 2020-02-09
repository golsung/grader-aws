package com.example.demo.web;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Account;
import com.example.demo.domain.ChPwInfo;
import com.example.demo.domain.Student;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.StudentService;

@Controller
public class GradeController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("loginForm")
    String loginForm() {
        return "loginForm";
    }
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public ModelAndView list(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Student student, ModelAndView mav) {
		if (userDetails.getUsername().equals("root")) {
			mav.setViewName("main");
			Iterable<Student> students = studentService.findAll();
			mav.addObject("students", students);
		} else {
			mav.setViewName("myGrade");
			student = studentService.find(userDetails.getUsername());
			mav.addObject("name", student.getName());
			mav.addObject("mail", student.getMail());
			mav.addObject("midScore", student.getMidScore());
			mav.addObject("finalScore", student.getFinalScore());
			mav.addObject("hwScore", student.getHwScore());
			mav.addObject("grade", student.getGrade());
		}
		return mav;
	}
	
	@RequestMapping(value= {"/addStudent"}, method=RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute @Validated Student student, BindingResult result, ModelAndView mav) {
		if (!result.hasErrors()) {
			accountService.addStudent(student);
			return new ModelAndView("redirect:/dashboard");
		} else {
			mav.setViewName("main");
			Iterable<Student> students = studentService.findAll();
			mav.addObject("students", students);
			return mav;
		}
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam String mail, ModelAndView mav){
		
		Student student = studentService.find(mail);
		mav.setViewName("edit");
		mav.addObject("student", student);
		return mav;
	}
	
	@RequestMapping(value="/changePw", method=RequestMethod.GET)
	public ModelAndView changePwForm(ModelAndView mav){
		mav.setViewName("chPwForm");
		ChPwInfo pwInfo = new ChPwInfo();
		mav.addObject("pwInfo", pwInfo);
		return mav;
	}
	
	@RequestMapping(value="/updatePw", method=RequestMethod.POST)
	public ModelAndView updatePw(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ChPwInfo pwInfo, ModelAndView mav){
		
		if (!(pwInfo.getPw1().equals(pwInfo.getPw2()))) return new ModelAndView("redirect:/changePw");

		Account acct = accountService.find(pwInfo.getMail());
		acct.setPassword(new BCryptPasswordEncoder().encode(pwInfo.getPw2()));
		accountService.save(acct);
		mav.addObject("mail", pwInfo.getMail());
		mav.addObject("pw1", pwInfo.getPw1());
		mav.addObject("pw2", acct.getPassword());
		mav.setViewName("redirect:/dashboard");
		return mav;
	}
	
//	@RequestMapping(value="/updatePw", method=RequestMethod.POST)
//	public ModelAndView updatePw(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ChPwInfo pwInfo, ModelAndView mav){
////		if (!(userDetails.getUsername().equals(pwInfo.getMail()))) return new ModelAndView("redirect:/changePw");
////		if (!(userDetails.getPassword().equals(pwInfo.getMail()))) return new ModelAndView("redirect:/changePw");
//
//		Student student = studentService.find(pwInfo.getMail());
//		Account acct = accountService.find(pwInfo.getMail());
//		mav.addObject("student", student);
//		mav.addObject("mail", pwInfo.getMail());
//		mav.addObject("curpwd", pwInfo.getCurPw());
//		mav.addObject("newpwd", pwInfo.getNewPw());
//		mav.setViewName("result-test");
//		return mav;
//	}

	@RequestMapping(value="/deleteall", method=RequestMethod.POST)
	public ModelAndView deleteAll(@ModelAttribute Student student, ModelAndView mav) {
		studentService.deleteAll();
		ModelAndView r= new ModelAndView("redirect:/dashboard");
		return r;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ModelAndView delete(@RequestParam String mail, ModelAndView mav) {
		accountService.deleteStudent(mail);
		return new ModelAndView("redirect:/dashboard");
	}

}
