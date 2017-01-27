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
    public String[] createPerson(@WebParam(name="personId") int id);
    
    @WebMethod(operationName="getAuth_info")
    public String[] get_auth(@WebParam(name="personId") int id);
    
    @WebMethod(operationName="getFood")
    public Food getFood(@WebParam(name="foodId") int id);
    
    @WebMethod(operationName="searchFood")
    public List<Food> searchFood(@WebParam(name="text") String s);

}
