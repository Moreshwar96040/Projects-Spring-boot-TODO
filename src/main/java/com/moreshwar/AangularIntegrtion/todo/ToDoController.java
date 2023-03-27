package com.moreshwar.AangularIntegrtion.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ToDoController {

	@Autowired
	TodoHardCodedService todoHardCodedService;
	
	@GetMapping("/users/{userName}/todos")
	List<Todo> getAllTodos(@PathVariable String userName){
		return todoHardCodedService.findAll();
	}
	
	@GetMapping("/users/{userName}/todos/{id}")
	Todo getTodo(@PathVariable String userName,@PathVariable long id){
		return todoHardCodedService.findById(id);
	}
	
	@PutMapping("/users/{userName}/todos/{id}")
	ResponseEntity<Todo> updateTodo(@PathVariable String userName,@PathVariable long id,@RequestBody Todo todo){
		Todo todoUpdated=todoHardCodedService.save(todo);
		return new ResponseEntity<Todo>(todo,HttpStatus.OK);
	}
	
	@PostMapping("/users/{userName}/todos")
	ResponseEntity<Todo> saveTodo(@PathVariable String userName,@RequestBody Todo todo){
		Todo createdTodo=todoHardCodedService.save(todo);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return  ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{userName}/todos/{id}")
	  ResponseEntity<Void> deleteTodo(@PathVariable String userName,@PathVariable long id){
		 Todo todo=todoHardCodedService.deleteById(id);
		 if(null!=todo) {
			 return ResponseEntity.noContent().build();
		 }
		 return  ResponseEntity.notFound().build();
	}
}
