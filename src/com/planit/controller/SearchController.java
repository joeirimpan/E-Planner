package com.planit.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.planit.configuration.AppConfig;
import com.planit.model.Student;
import com.planit.model.UserConstraints;
import com.planit.model.View;
import com.planit.repository.JenaSemanticRepositoryManager;
import com.planit.repository.RepositoryManager;
import com.planit.repository.SemanticRepositoryManager;
import com.planit.service.ServiceManager;
import com.planit.util.KIMClientConstants;


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
	
	 @RequestMapping(value = "/student.html", method = RequestMethod.GET)
	   public ModelAndView student() {
	      return new ModelAndView("student", "command", new Student());
	   }
	   
	 /*  @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView getData() {
		   ServiceManager serviceManager = AppConfig.serviceManager;
			 RepositoryManager repositoryManager = serviceManager.getRepositoryManager();
		     SemanticRepositoryManager semanticRepositoryManager = serviceManager.getSemanticRepositoryManager();
		   
		   List<String> list = semanticRepositoryManager.viewDestinations();
			//return back to index.jsp
			ModelAndView model = new ModelAndView("viewDis");
			model.addObject("lists", list);
	      return model;
	   }
	   
	 */
	   
	   
	   @RequestMapping(value = "/view.html", method = RequestMethod.GET)
	   public ModelAndView getData(HttpServletRequest request,
	           HttpServletResponse response) throws Exception {
		   
	//	   ServiceManager serviceManager = AppConfig.serviceManager;
	//		 RepositoryManager repositoryManager = serviceManager.getRepositoryManager();
	//	     SemanticRepositoryManager semanticRepositoryManager = serviceManager.getSemanticRepositoryManager();
		  
		     JenaSemanticRepositoryManager repomanager = new JenaSemanticRepositoryManager();
		   List<String> list = repomanager.viewDestinations();
		   request.setAttribute("resultsList", repomanager.viewDestinations());
			//return back to index.jsp
		//	ModelAndView model = new ModelAndView("viewTwo");
			
		//	model.addObject("lists", list);
		   return new ModelAndView("viewTwo", "command", new View());
	   }
	   
	  @RequestMapping(value = "/showDetails", method = RequestMethod.GET)
	   public ModelAndView showDetails(HttpServletRequest request,
	           HttpServletResponse response) throws Exception {
		   
		//   ServiceManager serviceManager = AppConfig.serviceManager;
		//	 RepositoryManager repositoryManager = serviceManager.getRepositoryManager();
		//     SemanticRepositoryManager semanticRepositoryManager = serviceManager.getSemanticRepositoryManager();
		   
		     JenaSemanticRepositoryManager repomanager = new JenaSemanticRepositoryManager();
		     String JSP_DEST = null;
		        try {
				String resourceURI = URLDecoder.decode(request.getParameter("resourceURI"), KIMClientConstants.DEFAULT_ENCODING);
		        request.setAttribute("resource",resourceURI);
		        request.setAttribute("result",repomanager.viewDetails(resourceURI));
		        JSP_DEST="success";
		        } catch (Exception e) {
		        	JSP_DEST="error";
		        	e.printStackTrace();
		        }
		   return new ModelAndView("showDetails", "command", new View());
	   }
	 
	   
	   @RequestMapping(value = "/deleteDestinations", method = RequestMethod.POST)
	   public String deleteDestinations(@ModelAttribute("Spring.web")View view, 
	   ModelMap model) throws UnsupportedEncodingException {
		   
		   
		   
	  Logger log = Logger.getLogger(SearchController.class);
			   
	  //ServiceManager serviceManager = AppConfig.serviceManager;
	  //RepositoryManager repositoryManager = serviceManager.getRepositoryManager();
	  //SemanticRepositoryManager semanticRepositoryManager = serviceManager.getSemanticRepositoryManager();
	
	  JenaSemanticRepositoryManager repomanager = new JenaSemanticRepositoryManager();
		
	     String JSP_DEST = "";
	 	log.info("Starting  action...");         
	 	String[] resourceURIs = null;
	 	String res=null;
	 	
	 	resourceURIs=view.getResourceURI();
	 	
	 	for(int i=0;i<resourceURIs.length;++i)
	 	{
	     System.out.println("hello : "+resourceURIs[i]);		
	 	}
	 	
	 //    System.out.println("hello: "+view.getResourceURI());
//	 	 res = view.getResourceURI();
//	 	 resourceURIs[0]=res;
	 	 repomanager.deleteStatements(resourceURIs); 

	     JSP_DEST = "success";
	     log.info("Ending action...");  
	  
	     return "success";
	   }
	   
	   
	   
	   @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	   public String addStudent(@ModelAttribute("Spring.web")Student student, 
	   ModelMap model) throws UnsupportedEncodingException {
		   
		 Logger log = Logger.getLogger(SearchController.class);
		   
	//	 ServiceManager serviceManager = AppConfig.serviceManager;
	//	 RepositoryManager repositoryManager = serviceManager.getRepositoryManager();
	//     SemanticRepositoryManager semanticRepositoryManager = serviceManager.getSemanticRepositoryManager();
		
		JenaSemanticRepositoryManager repomanager = new JenaSemanticRepositoryManager();
	    Map destination = new HashMap();
	    String destination_label = null;
	    String accommodation_label = null;
	   	String rating_value = null;
	 	String parking_value = null;
	 	String sPool_value = null;
	 	String fitnessRoom_value = null;
	 	String board_value = null;
	 	String[] board_values = null;
	 	String train_value = null;
	 	String airplane_value = null;
	 	String bus_value = null;
	 	String car_value = null;
	 	String train_name = null;
	 	String airplane_name = null;
	 	String bus_name = null;
	 	String skiing_value = null;
	 	String surfing_value = null;
	 	String hiking_value = null;
	 	String sightseeing_value = null;
	 	String shopping_value = null;
	 	String skiing_name = null;
	 	String surfing_name = null;
	 	String hiking_name = null;
	 	String sightseeing_name = null;
	 	String shopping_name = null;
	 	String destinationURL = null;
	     String JSP_DEST=null;
	     
	      destination_label=student.getName();
	      model.addAttribute("name", student.getName());
	      
	      if(student.getAge()!=null);
	      {
	      model.addAttribute("age", student.getAge());
	      destination.put("destinationURL", URLDecoder.decode(student.getAge(), KIMClientConstants.DEFAULT_ENCODING));
	      }
	     // model.addAttribute("id", student.getId());
	      
	      
	      model.addAttribute("board", student.getBoard());
	      
	      accommodation_label=student.getNam();
	      model.addAttribute("nam", student.getNam());
	      
	     
	      model.addAttribute("accomodationType", student.getAccomodationType());
	      
	      rating_value=student.getRatingSelected();
	      model.addAttribute("ratingSelected", student.getRatingSelected());
	      
	      parking_value=student.getParkingSelected();
	      model.addAttribute("parkingSelected", student.getParkingSelected());
	      
	      fitnessRoom_value=student.getFitnessRoomSelected();
	      model.addAttribute("fitnessRoomSelected", student.getFitnessRoomSelected());
	      
	      
	     // model.addAttribute("ratingSelected", student.getRatingSelected());
	    //  model.addAttribute("parkingSelected", student.getParkingSelected());
	      sPool_value=student.getsPoolSelected();
	      model.addAttribute("sPoolSelected", student.getsPoolSelected());
	    //  model.addAttribute("fitnessRoomSelected", student.getFitnessRoomSelected());
	      hiking_value=student.getHiking();
	      model.addAttribute("hiking", student.getHiking());

	      hiking_name=student.getHikingName();
	      model.addAttribute("hikingName", student.getHikingName());
	      
	      sightseeing_value=student.getSightseeing();
	      model.addAttribute("sightseeing", student.getSightseeing());
	    
	      sightseeing_name=student.getSightseeingName();
	      model.addAttribute("sightseeingName", student.getSightseeingName());
	      System.out.println(student.getSightseeing());
	      
	      surfing_value=student.getSurfing();
	      model.addAttribute("surfing", student.getSurfing());
	      
	      surfing_name=student.getSurfingName();
	      model.addAttribute("surfingName", student.getSurfingName());
	      
	      skiing_value=student.getSkiing();
	      model.addAttribute("skiing", student.getSkiing());
	      
	      skiing_name=student.getSkiingName();
	      model.addAttribute("skiingName", student.getSkiingName());
	      
	      shopping_value=student.getShopping();
	      model.addAttribute("shopping", student.getShopping());

	      shopping_name=student.getShoppingName();
	      model.addAttribute("shoppingName", student.getShoppingName());
	      
	      bus_value=student.getBus();
	      model.addAttribute("bus", student.getBus());
	      
	      bus_name=student.getBusName();
	      model.addAttribute("busName", student.getBusName());
	      
	      train_value=student.getTrain();
	      model.addAttribute("train", student.getTrain());
	      
	      train_name=student.getTrainName();
	      model.addAttribute("trainName", student.getTrainName());
	      
	      airplane_value=student.getAirplane();
	      model.addAttribute("airplane", student.getAirplane());
	      
	      airplane_name=student.getAirplaneName();
	      model.addAttribute("airplaneName", student.getBoard());
	      
	      car_value=student.getCar();
	      model.addAttribute("car", student.getCar());
	      
	      
	      destination.put("destinationName",destination_label);
	      destination.put("accommodationName",accommodation_label);
	      destination.put("ratingSelected",rating_value);
	      destination.put("parkingSelected",parking_value);
	      destination.put("sPoolSelected",sPool_value);
	      destination.put("fitnessRoomSelected",fitnessRoom_value);
	      destination.put("train",train_value);
		  destination.put("trainName",train_name);
		  destination.put("bus",bus_value);
	      destination.put("busName",bus_name);
	      destination.put("airplane",airplane_value);
		  destination.put("airplaneName",airplane_name);
		  destination.put("car",car_value);
		  destination.put("hiking",hiking_value);
		  destination.put("hikingName",hiking_name);
		  destination.put("skiing",skiing_value);
		  destination.put("skiingName",skiing_name);
		  destination.put("shopping",shopping_value);
		  destination.put("shoppingName",shopping_name);
		  destination.put("surfing",surfing_value);
		  destination.put("surfingName",surfing_name);
		  destination.put("sightseeing",sightseeing_value);
		  destination.put("sightseeingName",sightseeing_name);
		  
	      
	      System.out.println(destination_label);
	      
	      if (repomanager.insertNewDestination(destination))
	        JSP_DEST="success";
	      else
	      	JSP_DEST="error";
	      
	      model.addAttribute("value", JSP_DEST);
	      
	      return "res";
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