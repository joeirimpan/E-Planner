package com.planit.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.planit.model.UserConstraints;

@RestController
public class RestTestController {
	
	UserConstraints userConstraints = new UserConstraints();
	
	@RequestMapping(value = "/{rating}", method = RequestMethod.GET, produces="application/json")
	public UserConstraints testapp(@PathVariable("rating") String one) {
		userConstraints.setRatingSelected(one);
		return userConstraints;
	}

}
