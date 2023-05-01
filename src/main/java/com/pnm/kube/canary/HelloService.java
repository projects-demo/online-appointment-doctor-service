package com.pnm.kube.canary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloService {	
		 @RequestMapping("/svc")
	    public String hello() {		 
		      return String.format("doctor-service This is hello from doctor service!!");	     
	    }	
}

