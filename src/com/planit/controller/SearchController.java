package com.planit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.planit.model.UserConstraints;
import com.planit.repository.JenaSemanticRepositoryManager;

@Controller
public class SearchController {

	private List<Map<String, String>> destinations;

	public SearchController() {
		this.destinations = new ArrayList<>();
	}
	
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public ModelAndView initApp() {
		ModelAndView model = new ModelAndView("search", "userconstraints", new UserConstraints());
		return model;
	}

	@RequestMapping(value = "/addConstraints.html", method = RequestMethod.POST)
	public ModelAndView addConstraints(@ModelAttribute("userconstraints") UserConstraints userConstraints,
			BindingResult result, Model model1) {

		System.out.println("destinationName :" + userConstraints.getDestinationName());
		System.out.println("ratingSelected :" + userConstraints.getRatingSelected());
		System.out.println("parkingSelected :" + userConstraints.isParkingSelected());
		System.out.println("board :" + userConstraints.getBoard());
		System.out.println("activities :" + userConstraints.getActivities());
		System.out.println("sPoolSelected :" + userConstraints.issPoolSelected());
		System.out.println("fitnessRoomSelected :" + userConstraints.isFitnessRoomSelected());
		System.out.println("transport :" + userConstraints.getTransport());

		JenaSemanticRepositoryManager repomanager = new JenaSemanticRepositoryManager();
		destinations = repomanager.searchDestinations(userConstraints);

		for (int i = 0; i < destinations.size(); i++)
			System.out.println(destinations.get(i));

		ModelAndView model = new ModelAndView("result");
		model.addObject("queryresult", destinations);
		return model;

	}

}