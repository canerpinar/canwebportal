package com.webportal.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webportal.entity.Information;
import com.webportal.entity.Menu;
import com.webportal.service.WebService;
@Controller
public class ViewController {
	
	@Autowired
	private WebService webService;
	@Autowired
	private ServletContext servletContex;
	
	private List<Menu>  menuList = new ArrayList<>();

	
	@PostConstruct
	public void init() {
		menuList = webService.getListMenu();
		System.out.println("post constructr");
	}
	
	@RequestMapping(value="/")
	public ModelAndView homePage(){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("menuList",menuList);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/{url}")
	public ModelAndView dispatcherURL(HttpServletRequest request,HttpServletResponse response,@PathVariable String url){
		ModelAndView modelAndView = null;
		if(url.equals("hakkimizda")){
			modelAndView = new ModelAndView(url);
			modelAndView.addObject("menuList", menuList);
		}else if(url.equals("iletisim")){
			modelAndView = new ModelAndView(url);
			modelAndView.addObject("menuList", menuList);
			modelAndView.addObject("information", new Information());
		}else if(url.equals("login")){
			modelAndView = new ModelAndView("login/".concat(url));
			modelAndView.addObject("menu", new Menu());
		}else if(url.equals("referanslar")){
			modelAndView = new ModelAndView(url);
			modelAndView.addObject("menuList", menuList);
		}else if(url.equals("aksa")){
			modelAndView = new ModelAndView(url);
		}
		
		else if(url.equals("logout")){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null){
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("menuList", menuList);
		}
		
		return modelAndView;
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
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/saveMenu")
	@ResponseBody
	public void saveMenu(@ModelAttribute Menu menu) throws IOException{
		String fileName = "/template/".concat(menu.getPath()).concat(".html");
		ClassLoader classLoader = getClass().getClassLoader();
		//classLoader.getResourceAsStream("deneme.html");
		File file = new File(classLoader.getResource(".").getFile().concat(fileName));
		if (file.createNewFile()) {
			webService.saveMenu(menu);
		    System.out.println("File is created!"+classLoader.getResource(".").getPath());
		} else {
		    System.out.println("File already exists."+classLoader.getResource(".").getPath());
		}

		
		
	}
	
}
