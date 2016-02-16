package com.planit.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.planit.model.UserConstraints;
import com.planit.repository.JenaSemanticRepositoryManager;

@Controller
public class SearchController {
	
	
	private List destinations;
	
	public SearchController(){
		this.destinations = new ArrayList<>();
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView initApp() {
		ModelAndView model = new ModelAndView("search","userconstraints",new UserConstraints());
		return model;
	}

	@RequestMapping(value = "/addConstraints", method = RequestMethod.POST)
	public ModelAndView addConstraints(@ModelAttribute("userconstraints") UserConstraints userConstraints, BindingResult result) {
		
		System.out.println("destinationName :"+userConstraints.getDestinationName());
		System.out.println("ratingSelected :"+userConstraints.getRatingSelected());
		System.out.println("parkingSelected :"+userConstraints.isParkingSelected());
		System.out.println("board :"+userConstraints.getBoard());
		System.out.println("activities :"+userConstraints.getActivities());
		System.out.println("sPoolSelected :"+userConstraints.issPoolSelected());
		System.out.println("fitnessRoomSelected :"+userConstraints.isFitnessRoomSelected());
		
		JenaSemanticRepositoryManager repomanager = new JenaSemanticRepositoryManager();
		destinations = repomanager.searchDestinations(userConstraints);
		
		for(int i=0;i<destinations.size();i++)
			System.out.println(destinations.get(i));
			
		
		
		ModelAndView model = new ModelAndView("result");
		return model;
	}
	
	

	/*
	 * public ArrayList<String> runSPARQL(){
	 * 
	 * //SPARQL String filename = "F:/Linux/desktop/Main Project/e-tourism.owl";
	 * String samplequery =
	 * "PREFIX : <http://e-tourism.deri.at/ont/e-tourism.owl#>"+ "SELECT *"+
	 * "{"+ "{SELECT ?s WHERE { ?s :hasCountry \"Austria\" .}}"+ "MINUS"+
	 * "{SELECT ?s WHERE { ?s :hasCity  \"Neustift\" .}}"+ "}";
	 * ArrayList<String> queryResult = new ArrayList<String>(); OntModel
	 * ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	 * InputStream in = null; try { in = new FileInputStream(filename);
	 * ontModel.read(in, null);
	 * 
	 * Query query= QueryFactory.create(samplequery); QueryExecution
	 * qexec=QueryExecutionFactory.create(query, ontModel); ResultSet results =
	 * qexec.execSelect();
	 * 
	 * while ( results.hasNext() ){ QuerySolution soln = results.nextSolution();
	 * System.out.print(soln);
	 * queryResult.add(HtmlUtils.htmlEscape(soln.toString())); }
	 * 
	 * in.close(); } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return queryResult;
	 * 
	 * }
	 */

}