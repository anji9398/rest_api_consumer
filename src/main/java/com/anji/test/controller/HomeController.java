package com.anji.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.anji.test.dt.EmpDto;


@RestController
public class HomeController {

	
	@PostMapping("load/emp/recordes")
	public ResponseEntity<List> loadAllDetails()
	{
		String url = "http://localhost:8080/anji/load/all";
		
		RestTemplate template = new RestTemplate();
		ResponseEntity<List> exchange = template.exchange(url,HttpMethod.GET ,null,List.class);
		return exchange;
	}
	
	
	@PostMapping("/add/object")
	public ResponseEntity<EmpDto> addObject(@RequestBody EmpDto dto)
	{
		String url = "http://localhost:8080/anji/add/object";
		
		HttpEntity<EmpDto> entity = new HttpEntity<EmpDto>(dto);
		
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<EmpDto> exchange = template.exchange(url,HttpMethod.POST,entity,EmpDto.class);
		return exchange;
	}
	
	@PostMapping("/load/emp/data")
	public ResponseEntity<List> loadById(@RequestParam("id") Integer id1,@RequestParam Integer id2, @RequestParam Integer id3) 
	{
		String url = "http://localhost:8080/anji/load/by/id?id1="+id1+"&id2="+id2+"&id3="+id3;
		
		RestTemplate template = new RestTemplate();
		ResponseEntity<List> exchange = template.exchange(url, HttpMethod.GET,null,List.class);
		return exchange;
	}	
	
	@PostMapping("get/single/object/{id}")
	public ResponseEntity<EmpDto> loadSingleObject(@PathVariable(name = "id") Integer id)
	{
	    String url="http://localhost:8080/anji/get/single/object/"+id;
	    
	    RestTemplate template = new RestTemplate();
	    Map<String, Integer> map = new HashMap<>();
	    map.put("id", id);
	    
//	    ResponseEntity<EmpDto> exchange = template.exchange(url,HttpMethod.GET, null, EmpDto.class);
	    
	    ResponseEntity<EmpDto> exchange = template.exchange(url, HttpMethod.GET, null, EmpDto.class, map);
	    return exchange;
	}
	
	@PostMapping("delete/by/id/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) 
	{
	      String url = "http://localhost:8080/anji/delete/emp/"+id;
	      
	      RestTemplate template = new RestTemplate();
	      ResponseEntity<String> exchange = template.exchange(url,HttpMethod.DELETE, null, String.class);
	      return exchange;    
	}
	
	@PostMapping("update/sal/{id}/{sal}")
	public ResponseEntity<String> deleteById(@PathVariable(name = "id") Integer id,@PathVariable("sal") Double sal)
	{
	    String url="http://localhost:8080/anji/update/emp/salary/"+id+"/"+sal;	
	    // http://localhost:8080/anji/update/emp/salary/1234/12
	    RestTemplate template = new RestTemplate();
	    ResponseEntity<String> exchange = template.exchange(url,HttpMethod.PUT, null, String.class);
	    
	    return exchange;
	}
	
}
