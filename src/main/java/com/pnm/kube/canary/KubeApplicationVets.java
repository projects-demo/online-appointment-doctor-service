package com.pnm.kube.canary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KubeApplicationVets {
	public static void main(String[] args) {
    	log.debug("In Testing Logs KubeApplicationVets Service. debug");
    	log.trace("In Testing Logs KubeApplicationVets Service. trace");
    	log.info("In Testing Logs KubeApplicationVets Service. info");
		SpringApplication.run(KubeApplicationVets.class, args);
	}

}

