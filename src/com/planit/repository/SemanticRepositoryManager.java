package com.planit.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.planit.service.BaseService;
import com.planit.util.PropertyManager;


public abstract class SemanticRepositoryManager implements BaseService {
	
	
	static final Properties defaultProps = new Properties();
	static final Properties configProps = new Properties(defaultProps);

	public final List<String> ontologyFiles = loadOntologyFiles();
	public final List<String> kbFiles = loadKbFiles();
	static final String ONTOLOGY_FILES_TAG = "ontologyFiles";
	static final String KB_FILES_TAG = "kbFiles"; 
	static final String CONFIG_FILE = "SemanticRepositoryManager.properties";
	static {
        InputStream in = PropertyManager.openInputStream(CONFIG_FILE);
        if (in != null) {
           try {
               configProps.load(in);
               System.out.println(configProps.toString());
           } catch (IOException e) {
        	   System.out.println("Error loading configuration"+e.toString());
           } finally {
               try {
                   if (in != null)
                       in.close();
               } catch (IOException e) {
               	e.printStackTrace();
               }
           }
       }
    }
	
	

	static final String base = "http://www.owl-ontologies.com/travel_dest.owl";
	// properties
	final String type = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
	final String label = "http://www.w3.org/2000/01/rdf-schema#label";
	static final String hasAccommodation = new StringBuffer(base).append("#hasAccommodation").toString();
	static final String hasRating = new StringBuffer(base).append("#hasRating").toString();
	static final String hasActivity = new StringBuffer(base).append("#hasActivity").toString();
	static final String hasTransport = new StringBuffer(base).append("#hasTransport").toString();
	static final String hasParking = new StringBuffer(base).append("#hasParking").toString();
	static final String hasSwimmingPool = new StringBuffer(base).append("#hasSwimmingPool").toString();
	static final String hasFitnessRoom = new StringBuffer(base).append("#hasFitnessRoom").toString();
	static final String isOpen = new StringBuffer(base).append("#isOpen").toString();
	static final String isOpenFitnessRoom = new StringBuffer(base).append("#isOpenFitnessRoom").toString();
	static final String isOpenSwimmingPool = new StringBuffer(base).append("#isOpenSwimmingPool").toString();
	static final String hasURL = new StringBuffer(base).append("#hasURL").toString();
	// classes
	static final String destination = new StringBuffer(base).append("#Destination").toString();
	static final String accommodation = new StringBuffer(base).append("#Accommodation").toString();
	static final String accommodationRating = new StringBuffer(base).append("#AccommodationRating").toString();
	static final String parkingPlace = new StringBuffer(base).append("#Parking_place").toString();
	static final String fitnessRoom = new StringBuffer(base).append("#Fitness_room").toString();
	static final String swimmingPool = new StringBuffer(base).append("#Swimming_pool").toString();

	// transport classes
	static final String airplaneService = new StringBuffer(base).append("#AirplaneService").toString();
	static final String busService = new StringBuffer(base).append("#BusService").toString();
	static final String trainService = new StringBuffer(base).append("#TrainService").toString();
	static final String carService = new StringBuffer(base).append("#CarService").toString();
	// activities classes
	static final String shopping = new StringBuffer(base).append("#Shopping").toString();
	static final String sightSeeing = new StringBuffer(base).append("#Sightseeing").toString();
	static final String hiking = new StringBuffer(base).append("#Hiking").toString();
	static final String surfing = new StringBuffer(base).append("#Surfing").toString();
	static final String skiing = new StringBuffer(base).append("#Skiing").toString();

	static final String baseTravel = "http://www.owl-ontologies.com/travel.owl";
	// classes
	final String oneStarRating = new StringBuffer(baseTravel).append("#OneStarRating").toString();
	final String twoStarRating = new StringBuffer(baseTravel).append("#TwoStarRating").toString();
	final String threeStarRating = new StringBuffer(baseTravel).append("#ThreeStarRating").toString();
	final String fourStarRating = new StringBuffer(baseTravel).append("#FourStarRating").toString();
	final String fiveStarRating = new StringBuffer(baseTravel).append("#FiveStarRating").toString();
	
	abstract public Object getModel();
	abstract public Object loadModel();
	abstract public void writeModelIntoFile();
	abstract public List viewDestinations();
	abstract public String loadProperties(Object model);
	abstract public List executeSearch (String x, String y, String yName);
	abstract public Map viewDetails (String resourceURI);
	abstract public boolean insertNewDestination(Map destination);
	abstract public void deleteStatements(String[] resourceURIs);
	
public void load() {
	  
		
	}
	/*ontologyFiles=travel_dest.owl
	kbFiles=travel_d.owl,travel_new.owl*/

	public List<String> loadOntologyFiles() {

		Properties props = new Properties(configProps);
		try {
			List<String> ontologyFilesList = new ArrayList<>();
			String ontologyFile = props.getProperty(ONTOLOGY_FILES_TAG);
			String[] ontologyFilesSeparated = ontologyFile.split(",");
			for (int j = 0; j < ontologyFilesSeparated.length; j++) {
				ontologyFilesList.add(ontologyFilesSeparated[j]);
			}
			return ontologyFilesList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public List<String> loadKbFiles() {
		Properties props = new Properties(configProps);
		try {
			List<String> kbFilesList = new ArrayList<>();
			String kbFilesString = props.getProperty(KB_FILES_TAG);
			String[] kbFilesSeparated = kbFilesString.split(",");
			for (int k = 0; k < kbFilesSeparated.length; k++) {
				kbFilesList.add(kbFilesSeparated[k]);
			}
			return kbFilesList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
