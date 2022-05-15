package com; 
import model.Power; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Power") 
public class PowerService 
{ 
 Power powerObj = new Power(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readPower() 
  { 
  return powerObj.readPower(); 
 }
 
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertPower(@FormParam("locationName") String locationName, 
  @FormParam("relatedZone") String relatedZone, 
  @FormParam("timePeriod") String timePeriod, 
  @FormParam("scheduleDate") String scheduleDate) 
 { 
  String output = powerObj.insertPower(locationName, relatedZone, timePeriod, scheduleDate); 
 return output; 
 }

 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updatePower(String powerData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject powerObject = new JsonParser().parse(powerData).getAsJsonObject(); 
 //Read the values from the JSON object
  String scheduleID = powerObject.get("scheduleID").getAsString(); 
  String locationName = powerObject.get("locationName").getAsString(); 
  String relatedZone = powerObject.get("relatedZone").getAsString(); 
  String timePeriod = powerObject.get("timePeriod").getAsString(); 
  String scheduleDate = powerObject.get("scheduleDate").getAsString(); 
  String output = powerObj.updatePower(scheduleID, locationName, relatedZone, timePeriod, scheduleDate); 
 return output; 
}
 
 

 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deletePower(String powerData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(powerData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String scheduleID = doc.select("scheduleID").text(); 
  String output = powerObj.deletePower(scheduleID); 
 return output; 
 }
}
