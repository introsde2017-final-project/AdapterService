package introsde.adapter.ws;

import javax.jws.WebService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import introsde.adapter.ws.Interface;
import sun.misc.BASE64Encoder;

import introsde.adapter.model.*;

@WebService(endpointInterface = "introsde.adapter.ws.Interface",serviceName="AdapterService")
public class Implementation implements Interface{
	
	final static private String APP_KEY="11e7eec8475249a685bd218b07da5897";
	final static private String APP_URL="http://platform.fatsecret.com/rest/server.api";
	final static private String APP_SECRET = "dd6626f98f2b49c099a0782284f74e17";
	final static private String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	@Override
	public String[] createPerson(int id) {
		System.out.println("Create Person");
		return sendPersonRequest("profile.create", id);
	}
	
	
	@Override
	public String[] get_auth(int id) {
		System.out.println("Get auth");
		return sendPersonRequest("profile.get_auth", id);
	}
	
	
	
	public String[] sendPersonRequest(String method, int id){
		List<String> params = new ArrayList<>(Arrays.asList(generateOauthParams()));
        String[] template = new String[1];
        params.add("method="+method);
        params.add("user_id="+id);
        params.add("oauth_signature=" + sign("GET", params.toArray(template)));
        
		WebTarget service;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		service = client.target(APP_URL +"?" + paramify(params.toArray(template)));
		Response resp = service.request().get();
	    String json = resp.readEntity(String.class);
	    JsonNode node;
	    String[] result= new String[2];
		try {
			node = mapper.readTree(json);
			result[0] = node.path("profile").path("auth_secret").asText();
			result[1] = node.path("profile").path("auth_token").asText();	
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
		return result;
	}

	
	@Override
	public Food getFood(int food_id) {
		List<String> params = new ArrayList<>(Arrays.asList(generateOauthParams()));
        String[] template = new String[1];
        params.add("method=food.get");
        params.add("food_id="+food_id);
        params.add("oauth_signature=" + sign("GET", params.toArray(template)));
        
		WebTarget service;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		service = client.target(APP_URL +"?" + paramify(params.toArray(template)));
		Response resp = service.request().get();
	    String json = resp.readEntity(String.class);
	    System.out.println(json);
	    JsonNode node;
	    Food f = new Food();
	    try {
			node = mapper.readTree(json);
			f.setFood_id(node.path("food").path("food_id").asInt());
			f.setFood_name(node.path("food").path("food_name").asText());
			f.setFood_type(node.path("food").path("food_type").asText());
			f.setFood_url(node.path("food").path("food_url").asText());
			f.setFood_description(node.path("food").path("food_description").asText());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	    
	}
	
	@Override
	public List<Food> searchFood(String s) {
		List<String> params = new ArrayList<>(Arrays.asList(generateOauthParams()));
        String[] template = new String[1];
        params.add("method=foods.search");
        params.add("search_expression="+s);
        params.add("oauth_signature=" + sign("GET", params.toArray(template)));
        
		WebTarget service;
		ObjectMapper mapper = new ObjectMapper();
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		service = client.target(APP_URL +"?" + paramify(params.toArray(template)));
		Response resp = service.request().get();
	    String json = resp.readEntity(String.class);
	    System.out.println(json);
	    JsonNode node;
	    List<Food> foods = new ArrayList<>();
	    try {
			node = mapper.readTree(json);
			JsonNode foodsNode = node.path("foods").path("food");
			for (JsonNode n : foodsNode) {
				Food f = new Food();
				f.setFood_id(n.path("food_id").asInt());
				f.setFood_name(n.path("food_name").asText());
				f.setFood_type(n.path("food_type").asText());
				f.setFood_url(n.path("food_url").asText());
				f.setFood_description(n.path("food_description").asText());
				foods.add(f);
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return foods;
	}



	private static String[] generateOauthParams(){
		return new String[]{
				"oauth_consumer_key=" + APP_KEY,
				"oauth_signature_method=HMAC-SHA1",
				"oauth_version=1.0",
				"oauth_nonce=" + oauth_nonce(),
				"oauth_timestamp=" + Long.valueOf(System.currentTimeMillis()*2).toString(),
				"format=json"};
	}
	
	// Generate a unique random string (Timestamp combined with random string)
	private static String oauth_nonce(){
		Random r = new Random(System.currentTimeMillis());
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < r.nextInt(8) + 2; i++)
            n.append(r.nextInt(26) + 'a');
		return n.toString();
	}
	
	private static String sign(String method, String[] params){
		
		String s;
		try {
			s = method + "&" + URLEncoder.encode(APP_URL, "ISO-8859-1") + "&" + URLEncoder.encode(paramify(params), "ISO-8859-1");
			SecretKey sk = new SecretKeySpec((APP_SECRET+"&").getBytes(), HMAC_SHA1_ALGORITHM);
			Mac m = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			m.init(sk);
	        return URLEncoder.encode(new String(new BASE64Encoder().encode(m.doFinal(s.getBytes())).trim()), "ISO-8859-1");
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
			System.err.println("FatSecret_TEST FAIL error:" + e.getMessage());
            return null;
		}
        
    }
	
    private static String paramify(String[] params) {
        String[] p = Arrays.copyOf(params, params.length);
        Arrays.sort(p);
        String result = p[0];
        for (int i = 1; i < p.length; i++) {
            result += "&"+p[i];
        }
        return result;
    }




    

}
