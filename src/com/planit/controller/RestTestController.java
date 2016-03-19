package com.planit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.planit.model.UserConstraints;

@Controller
public class RestTestController {
	
	private List<Map<String, String>> destinations;

	public RestTestController() {
		this.destinations = new ArrayList<>();
	}
	

	@RequestMapping(value = "/rating",produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody UserConstraints testapp(@ModelAttribute("userconstraints") UserConstraints userConstraints) {
		return userConstraints;
	}
	
	@RequestMapping(value = "/rating",consumes = "application/json", method = RequestMethod.GET)
	public ModelAndView testapp1() {
		String url="http://localhost:8080/E-Planner/rating";
		RestTemplate template=new RestTemplate();
		UserConstraints modelclass = template.getForObject(url, UserConstraints.class);
		ModelAndView model = new ModelAndView("NewFile");
		model.addObject("userclass", modelclass.toString());
		return model;
	}
	
	

}
