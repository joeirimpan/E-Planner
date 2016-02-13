package com.planit.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class JenaSemanticRepositoryManager extends SemanticRepositoryManager {
	
	
	//user.home gives the location of user's home dir eg, C:/Users/lenovo/
	static final String ROOT_DIR = new StringBuffer(System.getProperty("user.home")).append("/").append("KIM").append("/").append("ROOT").append("/").toString();
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
	
	public JenaSemanticRepositoryManager(){
		
		model = (OntModel) loadModel();
		hasAccommodationProperty = ((OntModel) getModel())
				.getProperty(hasAccommodation);
		hasRatingProperty = ((OntModel) getModel()).getProperty(hasRating);
		hasActivityProperty = ((OntModel) getModel()).getProperty(hasActivity);
		hasTransportProperty = ((OntModel) getModel())
				.getProperty(hasTransport);
		hasParkingProperty = ((OntModel) getModel()).getProperty(hasParking);
		hasSwimmingPoolProperty = ((OntModel) getModel())
				.getProperty(hasSwimmingPool);
		hasFitnessRoomProperty = ((OntModel) getModel())
				.getProperty(hasFitnessRoom);
		isOpenProperty = ((OntModel) getModel()).getProperty(isOpen);
		isOpenFitnessRoomProperty = ((OntModel) getModel())
				.getProperty(isOpenFitnessRoom);
		isOpenSwimmingPoolProperty = ((OntModel) getModel())
				.getProperty(isOpenSwimmingPool);
		hasURLProperty = ((OntModel) getModel()).getProperty(hasURL);

		// getting them from the ontology
		destinationClass = ((OntModel) getModel()).getOntClass(destination);
		accommodationClass = ((OntModel) getModel()).getOntClass(accommodation);
		// Resource accommodationRatingResource =
		// ((OntModel)getModel()).getResource(accommodationRating);
		parkingPlaceResource = ((OntModel) getModel())
				.getResource(parkingPlace);
		fitnessRoomResource = ((OntModel) getModel()).getResource(fitnessRoom);
		swimmingPoolResource = ((OntModel) getModel())
				.getResource(swimmingPool);

		airplaneServiceResource = ((OntModel) getModel())
				.getResource(airplaneService);
		busServiceResource = ((OntModel) getModel()).getResource(busService);
		trainServiceResource = ((OntModel) getModel())
				.getResource(trainService);
		carServiceResource = ((OntModel) getModel()).getResource(carService);

		// resources for the activity classes
		shoppingResource = ((OntModel) getModel()).getResource(shopping);
		sightseeingResource = ((OntModel) getModel()).getResource(sightSeeing);
		hikingResource = ((OntModel) getModel()).getResource(hiking);
		surfingResource = ((OntModel) getModel()).getResource(surfing);
		skiingResource = ((OntModel) getModel()).getResource(skiing);

		// resources for classes
		oneStarRatingResource = ((OntModel) getModel())
				.getResource(oneStarRating);
		twoStarRatingResource = ((OntModel) getModel())
				.getResource(twoStarRating);
		threeStarRatingResource = ((OntModel) getModel())
				.getResource(threeStarRating);
		fourStarRatingResource = ((OntModel) getModel())
				.getResource(fourStarRating);
		fiveStarRatingResource = ((OntModel) getModel())
				.getResource(fiveStarRating);
		
	}
	
	public Object getModel() {
		return model;
	}
	
	public Object loadModel() {

		OntModel m = ModelFactory.createOntologyModel();
		// load model from files
		// first load ontologies into model
		// Iterator i =
		// getServiceManager().getSemanticRepositoryOntologyFiles().iterator();
		Iterator i = ontologyFiles.iterator();

		while (i.hasNext()) {
			String filePath = new StringBuffer(ONTOLOGIES_ROOT_DIR).append(
					(String) i.next()).toString();
			try {
				
				/*For input, both of these are the same, and fully implement the RDF Syntax Recommendation, see conformance.
				For output, "RDF/XML", produces regular output reasonably efficiently, but it is not readable. 
				In contrast, "RDF/XML-ABBREV", produces readable output without much regard to efficiency.*/
				
				System.out.println(m.read(new FileInputStream(filePath), "RDF/XML-ABBREV"));
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
			}
		}

		// then load kb into model

		// Iterator it =
		// getServiceManager().getSemanticRepositoryKbFiles().iterator();
		Iterator it = kbFiles.iterator();
		while (it.hasNext()) {
			String filePath = new StringBuffer(KB_ROOT_DIR).append(
					(String) it.next()).toString();
			try {
				m.read(new FileInputStream(filePath), "");
			} catch (FileNotFoundException fe) {
				fe.printStackTrace();
			}
		}

		return m;

	}

}
