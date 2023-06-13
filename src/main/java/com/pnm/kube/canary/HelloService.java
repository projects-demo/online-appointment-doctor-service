package com.pnm.kube.canary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloService {	
		 @RequestMapping("/svc")
	    public String hello() {		 
		    	log.debug("In Testing Logs HelloService Service. debug");
		    	log.trace("In Testing Logs HelloService Service. trace");
		    	log.info("In Testing Logs HelloService Service. info");
		      return String.format("doctor-service This is hello from doctor service!!");	 
		      
	    }	
}

