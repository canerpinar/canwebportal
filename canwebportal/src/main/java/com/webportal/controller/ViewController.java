package com.webportal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webportal.entity.Information;

@Controller
public class ViewController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String indexPage(){
		return "index";
	}
	
	@RequestMapping(value="/hakkimizda",method=RequestMethod.GET)
	public String hakkimizdaPageLoad(){		
		return "hakkimizda";
	}
	@RequestMapping(value="/iletisim",method=RequestMethod.GET)
	public ModelAndView iletisimPageLoad(){
		ModelAndView model = new ModelAndView();
		model.addObject("information", new Information());
		return model;
	}
	
	@RequestMapping(value="/iletisim",method=RequestMethod.POST)
	@ResponseBody
	public void informationProcess(HttpServletResponse response,@ModelAttribute Information information){
		System.out.println(information.getAd() +" "+ information.getSoyad());
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
