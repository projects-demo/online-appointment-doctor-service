
package com.pnm.kube.canary;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/vets")
@RestController
@RequiredArgsConstructor
class VetResource {

    private final VetRepository vetRepository;

    @GetMapping
    public List<Vet> showResourcesVetList() {
    	System.err.println("In Vets Service...");
        return vetRepository.findAll();
    }
    

    @GetMapping("/service")
    @ResponseBody
    String serviceName() {
        return "vet-service";
    }

	@RequestMapping(value = "/recommended")
	@ResponseBody
	public String readingList() {
		return "NOT Circuit breaker... response from vet-service";
	}

}
