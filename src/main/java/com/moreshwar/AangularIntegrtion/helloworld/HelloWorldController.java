package com.moreshwar.AangularIntegrtion.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins="http://localhost:4200")
@RestController
//@Controller
public class HelloWorldController {

	@Autowired
	HelloWorldBean helloWorldBean;
	//@ResponseBody
	@GetMapping(path="/hello-world")
	public String getTodo() {
		System.out.println("in Hello world controller");
		return "Hello world";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		System.out.println("in Hello world controller");
		helloWorldBean.setMessage("HELLO WORLD Changed");
		//throw new RuntimeException("Error has occured in hello-world-bean please contact support");
		return helloWorldBean;
	}
	
	@GetMapping(path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		System.out.println("in Hello world controller");
		helloWorldBean.setMessage(String.format("Hello %s",name));
		return helloWorldBean;
	}
}
