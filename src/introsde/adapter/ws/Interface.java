package introsde.adapter.ws;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import introsde.adapter.model.*;


@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Interface {
    
    @WebMethod(operationName="createPerson")
    public Person createPerson(@WebParam(name="personId") int id);
    
    @WebMethod(operationName="getAuth_info")
    public Person get_auth(@WebParam(name="personId") int id);	
    
    @WebMethod(operationName="setInfo")
    public boolean setInfo(@WebParam(name="user") Person user, @WebParam(name="weight") double weight, @WebParam(name="height") double height, @WebParam(name="weight_goal") double weight_goal);
    
    @WebMethod(operationName="weightUpdate")
    public boolean weightUpdate(@WebParam(name="user") Person user, @WebParam(name="weight") int weight);
    
    @WebMethod(operationName="getExercises")
    public List<Exercise> getExercises();
    
    @WebMethod(operationName="getFood")
    public Food getFood(@WebParam(name="foodId") int id);
    
    @WebMethod(operationName="searchFood")
    public List<Food> searchFood(@WebParam(name="text") String s);
    
    //Exercise Diary
    @WebMethod(operationName="getExerciseEntry")
    public List<Exercise> getExerciseEntry(@WebParam(name="user") Person user, @WebParam(name="date") int date);
    
    @WebMethod(operationName="editExerciseEntry")
    public boolean editExerciseEntry(@WebParam(name="oauth_token") Person user, @WebParam(name="id_exercise") int id, @WebParam(name="minutes") int minutes);
    
    @WebMethod(operationName="saveTemplate")
    public boolean saveTemplate(@WebParam(name="oauth_token") Person user, @WebParam(name="days") int days);
    
    @WebMethod(operationName="commitDay")
    public boolean commitDay(@WebParam(name="oauth_token") Person user);

    
}
