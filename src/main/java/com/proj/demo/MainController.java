package com.proj.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@Autowired
	MainRepository repo;
	
	@Autowired
	WeatherService weatherService;
	
	@RequestMapping("ping")
	public String PongMessage() {
		System.out.println("Pong message");
		return "pong";
	}
	@RequestMapping("/all")
	public ArrayList<Users> showall(){
		System.out.println("Query---> all users");
		ArrayList<Users> arrayList=(ArrayList<Users>) repo.findAll();
		return arrayList;
		}
	
	@GetMapping("/test/{value}")
	public ResponseEntity<String> coord(@PathVariable String value) {
		System.out.println(value);
		ArrayList<Object []> arrayList;
		try {
			int n=Integer.parseInt(value);
			arrayList=repo.findCoordById(n);
			System.out.println("When an id is used");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("When city is used");
			arrayList=repo.findCoord(value);
		}
		
		if(arrayList.isEmpty()) {
			//System.out.println("Not found");
			return null;
		}
		else {
			String coord = null;
			for (Object record : arrayList) {
				Object[] field = (Object[]) record;
				coord = (String) field[0] + "," + (String) field[1];
				break;
			}
			String dir[] = coord.split(",");
			dir[0] = dir[0].substring(dir[0].indexOf(" ") + 1);
			dir[1] = dir[1].trim();
			dir[1] = dir[1].substring(dir[1].indexOf(" ") + 1, dir[1].indexOf("}"));
			System.out.println(dir[0] + "   " + dir[1]);
//			return "Coord=("+dir[0]+","+dir[1]+")";
//	        String projectUrl="http://api.openweathermap.org/data/2.5/onecall?lat="+dir[0]+"&lon="+dir[1]+"exclude=minutely,hourly&units=metric&appid=316fab2a2e2716fa5452e5a2cf1adaae";
//	        return new ModelAndView("redirect:" + projectUrl);
			return weatherService.getWeatherData(dir[0], dir[1]);

		}
		//return null;
	}
	
	@PostMapping("/login")
	public String Loginauth(@RequestBody Users users){
		ArrayList<Object[]> arrayList=repo.findRegUser(users.getUsername(), users.getPassword());
		if(arrayList.isEmpty())
			return "{\"message\":\"Email or Password is incorrect\"}";
			
		else {
			String user = null;
			for(Object record:arrayList) {
				Object[] field=(Object[])record;
				user=(String)field[0];
			}
			String returnThis="{\"message\":\"Welcome "+user+"\"}";
			return returnThis;
		}
	}
	
	//For Registration
	@PostMapping("/reg")
	public String verify(@RequestBody Users users) {
		try {
		repo.save(users);
		//System.out.println(users.toString());
		}
		catch (Exception e) {
			// TODO: handle exception
			return "{\"message\":\"Error\"}";
//			return "Error in adding data";
		}
		String returnThis="{\"message\":\"Registration Success\"}";
		return returnThis;
	}
	
	@RequestMapping("search")
	@ResponseBody
	public List<String> search(@RequestParam(value="term",required=false,defaultValue="")String term){
		System.out.println("Search called");
		List<String> suggestions=new ArrayList<String>();
		//try {
			List<String> alList=repo.search(term);
			System.out.println(alList.size());
			//System.out.println(alList);
			for(String a:alList) {
				suggestions.add(a);
			}	
		return suggestions;
		
	}
	
	@PostMapping("getId")
	public String getId(@RequestBody City city) {
		System.out.println("Get Id called");
		String resultString=repo.findId(city.getName(), city.getCountry(),city.getState());
		//PongMessage();
		if(resultString==null)	//resultString="empty";
		System.out.println(resultString);
		return resultString;
		
	}
	
//	@RequestMapping(value="/login/{name}")
//	public ArrayList<Object[]> get(@PathVariable String name){
//		ArrayList<Object[]> arrayList=repo.findUserByUsername(name);
//		System.out.println("Hello");
//		return arrayList;
//	}
//	
	
//	@PostMapping("/homelogin")
//	public Users Loginauth(Users users){
//		System.out.println(users);
//		System.out.println(users.getUsername()+" "+users.getPassword());
//		return users;
//	}
	
	
//	@PostMapping("/test")
//	public String test(@RequestBody Users users){
//		return users.getName()+" "+users.getUsername();
//		
//	}
	
//	@RequestMapping(value="/reguser/{username}/{password}")
//	public ArrayList<Object[]> regUser(@PathVariable String username,@PathVariable String password){
//		ArrayList<Object[]> arrayList=repo.findRegUser(username, password);
//		System.out.println("Registered User or not");
//		if(arrayList.isEmpty()) {
//			System.out.println("Email id or Password is incorrect");
//			arrayList.clear();
//			return arrayList;
//		}
//		else {
//			System.out.println("Login Success "+arrayList);
//			for(Object record:arrayList) {
//				Object[] fieldObjects=(Object[]) record;
//				String userString=(String)fieldObjects[0];
//				System.out.print(userString);
//			}
//			return arrayList;
//		}
////		return arrayList;
//	}
	
//	@PostMapping("/login/{username}/{password}")
//	public String verify(@PathVariable String username,@PathVariable String password) {
//		ArrayList<Object[]> arrayList=repo.findRegUser(username, password);
//		if(arrayList.isEmpty())
//			return "Email or Password is incorrect";
//		else {
//			String user = null;
//			for(Object record:arrayList) {
//				Object[] field=(Object[])record;
//				user=(String)field[0];
//			}
//			return "Welcome "+user;
//		}
//	}
}
