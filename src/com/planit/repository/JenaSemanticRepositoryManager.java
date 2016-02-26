package com.planit.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.log4j.Logger;

import com.planit.model.UserConstraints;
import com.planit.service.ServiceManager;
import com.planit.util.KIMClientConstants;


public class JenaSemanticRepositoryManager extends SemanticRepositoryManager {

	static Logger log = Logger.getLogger(JenaSemanticRepositoryManager.class);
	// user.home gives the location of user's home dir eg, C:/Users/lenovo/
	static final String ROOT_DIR = new StringBuffer(System.getProperty("user.home")).append("/").append("KIM")
			.append("/").append("ROOT").append("/").toString();
	static final String ONTOLOGIES_ROOT_DIR = new StringBuffer(ROOT_DIR).append("ontologies").append("/").toString();
	static final String KB_ROOT_DIR = new StringBuffer(ROOT_DIR).append("kb").append("/").toString();

	private final OntModel model;

	// getting properties from the ontology travel_dest so that they can be used
	// when creating instances
	final Property hasAccommodationProperty;

	final Property hasRatingProperty;

	final Property hasActivityProperty;

	final Property hasTransportProperty;

	final Property hasParkingProperty;

	final Property hasSwimmingPoolProperty;

	final Property hasFitnessRoomProperty;

	final Property isOpenProperty;

	final Property isOpenFitnessRoomProperty;

	final Property isOpenSwimmingPoolProperty;

	final Property hasURLProperty;

	// getting them from the ontology
	final OntClass destinationClass;

	final OntClass accommodationClass;

	// Resource accommodationRatingResource =
	// ((OntModel)getModel()).getResource(accommodationRating);

	final Resource parkingPlaceResource;

	final Resource fitnessRoomResource;

	final Resource swimmingPoolResource;

	final Resource airplaneServiceResource;

	final Resource busServiceResource;

	final Resource trainServiceResource;

	final Resource carServiceResource;

	// resources for the activity classes
	final Resource shoppingResource;

	final Resource sightseeingResource;

	final Resource hikingResource;

	final Resource surfingResource;

	final Resource skiingResource;

	// resources for classes
	final Resource oneStarRatingResource;

	final Resource twoStarRatingResource;

	final Resource threeStarRatingResource;

	final Resource fourStarRatingResource;

	final Resource fiveStarRatingResource;

	public JenaSemanticRepositoryManager() {

		model = (OntModel) loadModel();
		hasAccommodationProperty = ((OntModel) getModel()).getProperty(hasAccommodation);
		hasRatingProperty = ((OntModel) getModel()).getProperty(hasRating);
		hasActivityProperty = ((OntModel) getModel()).getProperty(hasActivity);
		hasTransportProperty = ((OntModel) getModel()).getProperty(hasTransport);
		hasParkingProperty = ((OntModel) getModel()).getProperty(hasParking);
		hasSwimmingPoolProperty = ((OntModel) getModel()).getProperty(hasSwimmingPool);
		hasFitnessRoomProperty = ((OntModel) getModel()).getProperty(hasFitnessRoom);
		isOpenProperty = ((OntModel) getModel()).getProperty(isOpen);
		isOpenFitnessRoomProperty = ((OntModel) getModel()).getProperty(isOpenFitnessRoom);
		isOpenSwimmingPoolProperty = ((OntModel) getModel()).getProperty(isOpenSwimmingPool);
		hasURLProperty = ((OntModel) getModel()).getProperty(hasURL);

		// getting them from the ontology
		destinationClass = ((OntModel) getModel()).getOntClass(destination);
		accommodationClass = ((OntModel) getModel()).getOntClass(accommodation);
		// Resource accommodationRatingResource =
		// ((OntModel)getModel()).getResource(accommodationRating);
		parkingPlaceResource = ((OntModel) getModel()).getResource(parkingPlace);
		fitnessRoomResource = ((OntModel) getModel()).getResource(fitnessRoom);
		swimmingPoolResource = ((OntModel) getModel()).getResource(swimmingPool);

		airplaneServiceResource = ((OntModel) getModel()).getResource(airplaneService);
		busServiceResource = ((OntModel) getModel()).getResource(busService);
		trainServiceResource = ((OntModel) getModel()).getResource(trainService);
		carServiceResource = ((OntModel) getModel()).getResource(carService);

		// resources for the activity classes
		shoppingResource = ((OntModel) getModel()).getResource(shopping);
		sightseeingResource = ((OntModel) getModel()).getResource(sightSeeing);
		hikingResource = ((OntModel) getModel()).getResource(hiking);
		surfingResource = ((OntModel) getModel()).getResource(surfing);
		skiingResource = ((OntModel) getModel()).getResource(skiing);

		// resources for classes
		oneStarRatingResource = ((OntModel) getModel()).getResource(oneStarRating);
		twoStarRatingResource = ((OntModel) getModel()).getResource(twoStarRating);
		threeStarRatingResource = ((OntModel) getModel()).getResource(threeStarRating);
		fourStarRatingResource = ((OntModel) getModel()).getResource(fourStarRating);
		fiveStarRatingResource = ((OntModel) getModel()).getResource(fiveStarRating);

	}

	public Object getModel() {
		return model;
	}

	public Object loadModel() {

		OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		// load model from files
		// first load ontologies into model
		// Iterator i =
		// getServiceManager().getSemanticRepositoryOntologyFiles().iterator();
		Iterator<String> i = ontologyFiles.iterator();

		while (i.hasNext()) {
			String filePath = new StringBuffer(ONTOLOGIES_ROOT_DIR).append((String) i.next()).toString();
			try {

				/*
				 * For input, both of these are the same, and fully implement
				 * the RDF Syntax Recommendation, see conformance. For output,
				 * "RDF/XML", produces regular output reasonably efficiently,
				 * but it is not readable. In contrast, "RDF/XML-ABBREV",
				 * produces readable output without much regard to efficiency.
				 */

				m.read(new FileInputStream(filePath), "RDF/XML-ABBREV");
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
			}
		}

		// then load kb into model

		// Iterator it =
		// getServiceManager().getSemanticRepositoryKbFiles().iterator();
		Iterator<String> it = kbFiles.iterator();
		while (it.hasNext()) {
			String filePath = new StringBuffer(KB_ROOT_DIR).append((String) it.next()).toString();
			try {
				m.read(new FileInputStream(filePath), "");
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
			}
		}

		return m;

	}

	public List<Map<String, String>> searchDestinations(UserConstraints userConstraints) {

		boolean parkingOpen = false;
		boolean fitnessOpen = false;
		boolean sPoolOpen = false;

		String transportChoice = null;
		if (userConstraints.isParkingSelected())
			parkingOpen = true;
		if (userConstraints.issPoolSelected())
			sPoolOpen = true;
		if (userConstraints.isFitnessRoomSelected())
			fitnessOpen = true;

		String transport = userConstraints.getTransport();

		// values for transport service
		switch (transport) {
		case "Bus":
			transportChoice = busService;
			break;
		case "Plane":
			transportChoice = airplaneService;
			break;
		case "Train":
			transportChoice = trainService;
			break;
		case "Auto":
			transportChoice = carService;
			break;
		default:
			transportChoice = carService;
			break;
		}

		String queryString = "SELECT DISTINCT ?x " + "WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <"
				+ destination + "> . " +
				// "OPTIONAL { "+
				"?x <" + hasAccommodation + "> ?y ." + "?y <" + hasRating + "> ?z . " + "?y <" + type + "> <"
				+ accommodation + "> . " + "?z <" + type + "> <" + accommodationRating + "> . " + "?z <" + label
				+ "> ?zName . " + "FILTER regex(?zName, '" + userConstraints.getRatingSelected() + "', 'i') .";

		if (userConstraints.isParkingSelected())
			queryString = queryString + "?y <" + hasParking + "> ?m . " + "?m <" + type + "> <" + parkingPlace + "> . "
					+ "?m <" + isOpen + "> ?isOpen . " + "FILTER (?isOpen=" + parkingOpen + ") . ";

		System.out.println("GetTransport :" + userConstraints.getTransport().isEmpty());

		if (!userConstraints.getTransport().isEmpty())
			queryString = queryString + "?x <" + hasTransport + "> ?t . " + "?t <" + type + "> <" + transportChoice
					+ "> . ";

		if (userConstraints.isFitnessRoomSelected())
			queryString = queryString + "?y <" + hasFitnessRoom + "> ?f . " + "?f <" + type + "> <" + fitnessRoom
					+ "> . " + "?f <" + isOpenFitnessRoom + "> ?isOpenFittnessRoom . " + "FILTER (?isOpenFittnessRoom="
					+ fitnessOpen + ") . ";

		if (userConstraints.issPoolSelected())
			queryString = queryString + "?y <" + hasSwimmingPool + "> ?s . " + "?s <" + type + "> <" + swimmingPool
					+ "> ." + "?s <" + isOpenSwimmingPool + "> ?isOpenSwimmingPool . " + "FILTER (?isOpenSwimmingPool="
					+ sPoolOpen + ") . ";

		String activityChoice = null;
		
		/**
		 *	Creating query from the activities list.
		 *	:: activity type <matched--with> acitivitylist.get(position) ::
		 *	:: FILTER( (?type=traveldest:Skiing) || (?type=traveldest:Surfing) ) . ::
		 */
		if (!userConstraints.getActivities().isEmpty()) {

			System.out.println("Activities Not Empty");
			
			Iterator<String> activitiesIterator = userConstraints.getActivities().iterator();
			// Iterate through activities and append the activities to the query
			// string
			queryString += "?x <" + hasActivity + "> ?a . " + "?a <" + type + "> ?activity . " + "FILTER( ";
			while (activitiesIterator.hasNext()) {
				
				String currentActivity = null;
				try {
					currentActivity = activitiesIterator.next();
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
				
				if (currentActivity.equals("hiking"))
					activityChoice = hiking;
				if (currentActivity.equals("surfing"))
					activityChoice = surfing;
				if (currentActivity.equals("skiing"))
					activityChoice = skiing;
				if (currentActivity.equals("shopping"))
					activityChoice = shopping;
				if (currentActivity.equals("sightseeing"))
					activityChoice = sightSeeing;

				queryString = queryString + "( ?activity=<" + activityChoice + "> ) ";

				if (activitiesIterator.hasNext())
					queryString += " || ";
			}
			queryString += ") .";

		}

		// queryString = queryString + "} }";
		queryString = queryString + " }";

		List<OntResource> resultsList = new ArrayList<>();

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, (OntModel) getModel());
		ResultSet results = qexec.execSelect();

		System.out.println(queryString);
		System.out.println("Model size :" + model.size());
		System.out.println("Results hasNext() :" + results.hasNext());

		try {
			for (; results.hasNext();) {
				System.out.println("Result exits");
				QuerySolution soln = results.nextSolution();
				RDFNode x = soln.get("x");
				resultsList.add(x.as(OntResource.class));
			}
		} finally {
			qexec.close();
		}

		Iterator<OntResource> i = resultsList.iterator();

		String localName = "";

		List<Map<String, String>> l = new ArrayList<>();
		while (i.hasNext()) {
			Map<String, String> m = new HashMap<String, String>();
			OntResource o = i.next();

			String destinationURI = "";
			if (o.getProperty((((OntModel) getModel()).getProperty(hasURL))) != null)
				destinationURI = ((Literal) (o.getProperty((((OntModel) getModel()).getProperty(hasURL))).getObject()))
						.getString();

			m.put("uri", o.getURI());
			m.put("localName", o.getLocalName());
			m.put("destinationURI", destinationURI);
			localName = o.getLocalName();

			// Test Query - Status -Working
			// Adds activities with the spot name to the Map
			if (!userConstraints.getActivities().isEmpty()) {
				System.out.println("Activities Not Empty");
				executeActivityQuery(localName, userConstraints.getActivities(), m);
			}
			
			l.add(m);
			System.out.println("result: " + m);
		}
		return l;
	}

	public void executeActivityQuery(String localName, ArrayList<String> activities, Map<String, String> m) {
		// Test query for all activities
		String queryString = "SELECT * { ";

		for (int i = 0; i < activities.size(); i++) {

			System.out.println(activities.get(i));
			queryString += "{ SELECT ?" + activities.get(i)
					+ " WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" + "<" + destination + "> ."
					+ "?x <" + hasActivity + "> ?t . " + "FILTER( regex( STR(?t), \"" + localName + "_"
					+ activities.get(i) + "\", 'i' ) ) ." + "?t <" + label + "> ?" + activities.get(i) + " } }";

		}

		queryString += " }";

		System.out.println("Query::::::" + queryString);

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, (OntModel) getModel());
		ResultSet results = qexec.execSelect();

		try {
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				for (int i = 0; i < activities.size(); i++) {
					RDFNode x = soln.get(activities.get(i));
					m.put(activities.get(i), x.toString());
					System.out.println(x.toString());
				}
			}
		} finally {
			qexec.close();
		}

	}
	
	public List viewDestinations() {

		String base = "http://www.owl-ontologies.com/travel_dest.owl";

		String destination = new StringBuffer(base).append("#Destination")
				.toString();

		String queryString = new StringBuffer("SELECT ?x ").append(
				"WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type><")
				.append(destination).append("> . ").append("}").toString();

		List resultsList = new ArrayList();

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,
				((OntModel) (getModel())));
		ResultSet results = qexec.execSelect();
		try {
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				RDFNode x = soln.get("x");
				resultsList.add(x);
			}
		} finally {
			qexec.close();
		}
		return resultsList;
	}

	public boolean insertNewDestination(Map destinationMap) {

		boolean value = false;

		// first should be checked if this destination is already in kb

		String destination_label = ((String) destinationMap
				.get("destinationName"));
		System.out.println("insertNewDestination :"+destination_label);
		
		String accommodation_label = ((String) destinationMap
				.get("accommodationName"));
		int rating_value = 0;
		if (destinationMap.get("ratingSelected") != null) {
			rating_value = (Integer.parseInt((String) destinationMap
					.get("ratingSelected")));
			log.info("rating is: " + rating_value);
		}
		String parking_value = ((String) destinationMap.get("parkingSelected"));
		String sPool_value = ((String) destinationMap.get("sPoolSelected"));
		String fitnessRoom_value = ((String) destinationMap
				.get("fitnessRoomSelected"));
		String board_value = null;
		String[] board_values = null;
		String train_value = ((String) destinationMap.get("train"));
		String airplane_value = ((String) destinationMap.get("airplane"));
		String bus_value = ((String) destinationMap.get("bus"));
		String car_value = ((String) destinationMap.get("car"));
		String train_name = ((String) destinationMap.get("trainName"));
		String airplane_name = ((String) destinationMap.get("airplaneName"));
		String bus_name = ((String) destinationMap.get("busName"));
		String skiing_value = ((String) destinationMap.get("skiing"));
		String surfing_value = ((String) destinationMap.get("surfing"));
		String hiking_value = ((String) destinationMap.get("hiking"));
		String sightseeing_value = ((String) destinationMap.get("sightseeing"));
		String shopping_value = ((String) destinationMap.get("shopping"));
		String skiing_name = ((String) destinationMap.get("skiingName"));
		String surfing_name = ((String) destinationMap.get("surfingName"));
		String hiking_name = ((String) destinationMap.get("hikingName"));
		String sightseeing_name = ((String) destinationMap
				.get("sightseeingName"));
		String shopping_name = ((String) destinationMap.get("shoppingName"));
		String destinationURL = (String) destinationMap.get("destinationURL");

		boolean parking_open = false;
		boolean fitness_room_open = false;
		boolean sPool_open = false;
		String parking_URI = null;
		String fitness_room_URI = null;
		String sPool_URI = null;
		String destination_URI = new StringBuffer(base).append("#").append(
				destination_label).append("_").append(accommodation_label)
				.toString();
		String accommodation_URI = new StringBuffer(base).append("#").append(
				destination_label).append("_hotel_")
				.append(accommodation_label).toString();
		String skiing_URI = null;
		String surfing_URI = null;
		String shopping_URI = null;
		String sightseeing_URI = null;
		String hiking_URI = null;
		// String accommodation_type_value = null;

		if ("yes".equalsIgnoreCase(parking_value))
			parking_open = true;
		if (sPool_value.equalsIgnoreCase("yes"))
			sPool_open = true;
		if (fitnessRoom_value.equalsIgnoreCase("yes"))
			fitness_room_open = true;

		Resource accommodationRatingTypeResource = null;

		switch (rating_value) {
		case 1:
			accommodationRatingTypeResource = oneStarRatingResource;
			break;
		case 2:
			accommodationRatingTypeResource = twoStarRatingResource;
			break;
		case 3:
			accommodationRatingTypeResource = threeStarRatingResource;
			break;
		case 4:
			accommodationRatingTypeResource = fourStarRatingResource;
			break;
		case 5:
			accommodationRatingTypeResource = fiveStarRatingResource;
			break;
		default:
			accommodationRatingTypeResource = threeStarRatingResource;
			break;
		}

		log.info("before creating new destination...");
		Individual touristOffer = ((OntModel) getModel()).createIndividual(
				destination_URI, destinationClass);
		log.info("new destination uri is..." + destination_URI);
		Resource newAccommodation = ((OntModel) getModel())
				.createResource(accommodation_URI);
		log.info("new accommodation uri is..." + accommodation_URI);
		newAccommodation.addProperty(hasRatingProperty,
				accommodationRatingTypeResource);
		newAccommodation.addProperty(RDFS.label, accommodation_label);
		newAccommodation.addProperty(RDF.type, accommodationClass);
		touristOffer.addProperty(RDFS.label, destination_label);
		// touristOffer.addProperty(RDF.type, destinationClass);
		touristOffer.addProperty(hasAccommodationProperty, newAccommodation);

		Literal destinationURIL = ((OntModel) getModel())
				.createTypedLiteral(new String(destinationURL));
		touristOffer.addProperty(hasURLProperty, destinationURIL);

		parking_URI = new StringBuffer(accommodation_URI).append("_").append(
				"Parking").toString();
		fitness_room_URI = new StringBuffer(accommodation_URI).append("_")
				.append("FitnessRoom").toString();
		sPool_URI = new StringBuffer(accommodation_URI).append("_").append(
				"SwimmingPool").toString();
		skiing_URI = new StringBuffer(destination_URI).append("_").append(
				"Skiing").toString();
		surfing_URI = new StringBuffer(destination_URI).append("_").append(
				"Surfing").toString();
		shopping_URI = new StringBuffer(destination_URI).append("_").append(
				"Shopping").toString();
		sightseeing_URI = new StringBuffer(destination_URI).append("_").append(
				"Sightseeing").toString();
		hiking_URI = new StringBuffer(destination_URI).append("_").append(
				"Hiking").toString();
		log.info("new parking_URI uri is..." + parking_URI);
		log.info("new fitness_room_URI uri is..." + fitness_room_URI);
		log.info("new sPool_URI uri is..." + sPool_URI);
		log.info("new skiing_URI uri is..." + skiing_URI);
		log.info("new fitness_room_URI uri is..." + fitness_room_URI);
		log.info("new surfing_URI uri is..." + surfing_URI);
		log.info("new shopping_URI uri is..." + shopping_URI);
		log.info("new sightseeing_URI uri is..." + sightseeing_URI);
		log.info("new hiking_URI uri is..." + hiking_URI);

		if (parking_value != null) {
			Resource newParking = ((OntModel) getModel())
					.createResource(parking_URI);
			newParking.addProperty(RDF.type, parkingPlaceResource);
			Literal parkingL = ((OntModel) getModel())
					.createTypedLiteral(new Boolean(parking_open));
			newParking.addProperty(isOpenProperty, parkingL);
			newAccommodation.addProperty(hasParkingProperty, newParking);
		}

		if (fitnessRoom != null) {
			Resource newFitnessRoom = ((OntModel) getModel())
					.createResource(fitness_room_URI);
			newFitnessRoom.addProperty(RDF.type, fitnessRoomResource);
			Literal fitnessRoomL = ((OntModel) getModel())
					.createTypedLiteral(new Boolean(fitness_room_open));
			newFitnessRoom.addProperty(isOpenFitnessRoomProperty, fitnessRoomL);
			newAccommodation
					.addProperty(hasFitnessRoomProperty, newFitnessRoom);
		}

		if (sPool_value != null) {
			Resource newSwimmingPool = ((OntModel) getModel())
					.createResource(sPool_URI);
			newSwimmingPool.addProperty(RDF.type, swimmingPoolResource);
			Literal sPoolL = ((OntModel) getModel())
					.createTypedLiteral(new Boolean(sPool_open));
			newSwimmingPool.addProperty(isOpenSwimmingPoolProperty, sPoolL);
			newAccommodation.addProperty(hasSwimmingPoolProperty,
					newSwimmingPool);
		}

		if (hiking_value != null) {
			Resource newHiking = ((OntModel) getModel())
					.createResource(hiking_URI);
			newHiking.addProperty(RDF.type, hikingResource);
			if (hiking_name != null && !hiking_name.equals(""))
				newHiking.addProperty(RDFS.label, hiking_name);
			touristOffer.addProperty(hasActivityProperty, newHiking);
		}

		if (skiing_value != null) {
			Resource newSkiing = ((OntModel) getModel())
					.createResource(skiing_URI);
			newSkiing.addProperty(RDF.type, skiingResource);
			if (skiing_name != null && !skiing_name.equals(""))
				newSkiing.addProperty(RDFS.label, skiing_name);
			touristOffer.addProperty(hasActivityProperty, newSkiing);
		}

		if (surfing_value != null) {
			Resource newSurfing = ((OntModel) getModel())
					.createResource(surfing_URI);
			newSurfing.addProperty(RDF.type, surfingResource);
			if (surfing_name != null && !surfing_name.equals(""))
				newSurfing.addProperty(RDFS.label, surfing_name);
			touristOffer.addProperty(hasActivityProperty, newSurfing);
		}

		if (shopping_value != null) {
			Resource newShopping = ((OntModel) getModel())
					.createResource(shopping_URI);
			newShopping.addProperty(RDF.type, shoppingResource);
			if (shopping_name != null && !shopping_name.equals(""))
				newShopping.addProperty(RDFS.label, shopping_name);
			touristOffer.addProperty(hasActivityProperty, newShopping);
		}

		if (sightseeing_value != null) {
			Resource newSightseeing = ((OntModel) getModel())
					.createResource(sightseeing_URI);
			newSightseeing.addProperty(RDF.type, sightseeingResource);
			if (sightseeing_name != null && !sightseeing_name.equals(""))
				newSightseeing.addProperty(RDFS.label, sightseeing_name);
			touristOffer.addProperty(hasActivityProperty, newSightseeing);
		}

		String busservice_URI = new StringBuffer(destination_URI).append("_")
				.append("BusService").toString();
		String trainservice_URI = new StringBuffer(destination_URI).append("_")
				.append("TrainService").toString();
		String airplaneservice_URI = new StringBuffer(destination_URI).append(
				"_").append("AirplaneService").toString();
		String carservice_URI = new StringBuffer(destination_URI).append("_")
				.append("CarService").toString();
		log.info("new busservice_URI uri is..." + busservice_URI);
		log.info("new trainservice_URI uri is..." + trainservice_URI);
		log.info("new airplaneservice_URI uri is..." + airplaneservice_URI);
		log.info("new carservice_URI uri is..." + carservice_URI);
		if (bus_value != null) {
			Resource newBusService = ((OntModel) getModel())
					.createResource(busservice_URI);
			newBusService.addProperty(RDF.type, busServiceResource);
			if (bus_name != null && !bus_name.equals(""))
				newBusService.addProperty(RDFS.label, bus_name);
			touristOffer.addProperty(hasTransportProperty, newBusService);
		}

		if (train_value != null) {
			Resource newTrainService = ((OntModel) getModel())
					.createResource(trainservice_URI);
			newTrainService.addProperty(RDF.type, trainServiceResource);
			if (train_name != null && !train_name.equals(""))
				newTrainService.addProperty(RDFS.label, train_name);
			touristOffer.addProperty(hasTransportProperty, newTrainService);
		}

		if (airplane_value != null) {
			Resource newAirplaneService = ((OntModel) getModel())
					.createResource(airplaneservice_URI);
			newAirplaneService.addProperty(RDF.type, airplaneServiceResource);
			if (airplane_name != null && !airplane_name.equals(""))
				newAirplaneService.addProperty(RDFS.label, airplane_name);
			touristOffer.addProperty(hasTransportProperty, newAirplaneService);
		}

		if (car_value != null) {
			Resource newCarService = ((OntModel) getModel())
					.createResource(carservice_URI);
			newCarService.addProperty(RDF.type, carServiceResource);
			touristOffer.addProperty(hasTransportProperty, newCarService);
		}

		// ((OntModel)getModel()).createIndividual(touristOffer);
		try {
			writeModelIntoFile();
			value = true;
		} catch (Exception e) {
			value = false;
			e.printStackTrace();
		}

		return value;
	}
	
	public void writeModelIntoFile() {

		String fullOutputFileName = new StringBuffer(KB_ROOT_DIR).append(
				"travel_new.owl").toString();
		try {
			FileOutputStream fw = new FileOutputStream(fullOutputFileName);

			((OntModel) (getModel())).write(fw, "RDF/XML-ABBREV");
			fw.close();
		} catch (FileNotFoundException fnfe) {

		} catch (IOException fnfe) {

		}

	}
	
	public Map viewDetails(String resourceURI) {

		Resource r = ((OntModel) getModel()).getResource(resourceURI);

		log.info("r: " + r.getURI());

		Map result = new HashMap();

		StmtIterator i = r.listProperties();

		while (i.hasNext()) {
			String object = null;
			String uri = null;
			Statement s = (Statement) i.next();
			log.info("statement of r: " + s);
			if (s.getObject().isResource()) {
				uri = ((Resource) s.getObject()).getURI();
				// log.info("is resource r: "+uri);
				if (uri != null) {
					try {
						object = new StringBuffer(
								"<a href='#' onclick='document.location=")
								.append('"').append(
										"/travelguides/showDetails.do?resourceURI=")
								.append(URLEncoder.encode(uri, "UTF-8"))
								.append('"').append("'").append(">")
								.append(uri).append("</a>").toString();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}

			} else if (s.getObject().isLiteral()) {
				if (s.getObject() != null) {
					object = new StringBuffer(((Literal) s.getObject())
							.getValue().toString()).toString();
				}

			} else {
				log.info("s.getObject().getClass()" + s.getObject().getClass());
			}

			// object = new
			// StringBuffer((String)((RDFNode)s.getObject()).getValue()).toString();
			log.info("key for hashmap:" + s.getPredicate().getURI());
			log.info("object for hashmap:" + object);
			result.put(s.getPredicate().getURI(), object);
		}
		return result;

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServiceManager getServiceManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadProperties(Object model) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String value = "";
		List selectedPropertyList = new ArrayList();
		StmtIterator i = ((OntModel) model).listStatements();
		while (i.hasNext()) {
			Statement s = (Statement) i.next();
			Property p = s.getPredicate();
			if (!list.contains(p.getURI()) && p != null) {
				value = new StringBuffer(value)
						.append("<OPTION value=\"")
						.append(p.getURI())
						.append("\"")
						.append(
								(selectedPropertyList.contains(p.getURI()) ? " selected "
										: "")).append(">").append(
								p.getLocalName()).append("</OPTION>")
						.toString();
				list.add(p.getURI());
			}
		}
		log.info(value);
		return value;
	}


	@Override
	public List executeSearch(String x, String y, String yName) {
		// TODO Auto-generated method stub
		String queryString = "SELECT ?x "
				+ "WHERE {?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <"
				+ x + "> . "
				+ "?y <http://www.w3.org/2000/01/rdf-schema#label> ?yName . "
				+ "?y <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <" + y
				+ "> . " + "?x ?p ?y " + "FILTER regex(?yName, '" + yName
				+ "', 'i') }";

		log.info("queryString:::::::::::" + queryString);
		List resultsList = new ArrayList();

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,
				((OntModel) getModel()));
		ResultSet results = qexec.execSelect();
		try {
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				RDFNode xg = soln.get("x");
				resultsList.add(xg);
			}
		} finally {
			qexec.close();
		}

		return resultsList;
	}
	
	public void deleteStatements(String[] resourceURIs) {

		// loading model from file, this should be moved and done through the
		// application

		for (int i = 0; i < resourceURIs.length; i++) {
			String s = resourceURIs[i];
			try {
				s = URLDecoder.decode(s, KIMClientConstants.DEFAULT_ENCODING);
			} catch (UnsupportedEncodingException uee) {
				uee.printStackTrace();
			}
			((OntModel) getModel()).remove(returnStatements(
					((OntModel) (getModel())), ((OntModel) (getModel()))
							.getResource(s), new ArrayList()));
			log.info(s);
			// delete from model
		}
		writeModelIntoFile();

	}
	
	List returnStatements(OntModel wkb, Resource r, List a) {
		a.add(r);
		List list = new ArrayList();
		StmtIterator i = wkb.listStatements(r, null, (RDFNode) null);
		while (i.hasNext()) {
			StatementImpl statement = (StatementImpl) i.next();
			RDFNode node = statement.getObject();
			if (node.isResource()) {
				Resource re = ((Resource) node.as(Resource.class));
				if (!(a.contains(re)))
					list.addAll(returnStatements(wkb, re, a));
			}
			list.add(statement);

		}// i
		return list;
	}
	

}
