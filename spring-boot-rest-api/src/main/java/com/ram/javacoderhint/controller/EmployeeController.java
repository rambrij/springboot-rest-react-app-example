package com.ram.javacoderhint.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.javacoderhint.dao.EmployeeRepository;
import com.ram.javacoderhint.model.ApiResponse;
import com.ram.javacoderhint.model.Employee;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepository;

	@PostMapping
	public ApiResponse<Employee> saveUser(@RequestBody Employee user) {
		Employee newEmp = new Employee();
		newEmp.setUsername(user.getUsername());
		newEmp.setFirstName(user.getFirstName());
		newEmp.setLastName(user.getLastName());
		newEmp.setPassword(user.getPassword());
		newEmp.setAge(user.getAge());
		newEmp.setSalary(user.getSalary());
		return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.", empRepository.save(newEmp));
	}

	@GetMapping
	public ApiResponse<List<Employee>> listUser() {
		List<Employee> list = new ArrayList<>();
		empRepository.findAll().iterator().forEachRemaining(list::add);
		return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", list);
	}

	@GetMapping("/{id}")
	public ApiResponse<Employee> getOne(@PathVariable int id) {

		Optional<Employee> optionalUser = empRepository.findById(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",
				optionalUser.isPresent() ? optionalUser.get() : null);
	}

	@PutMapping("/{id}")
	public ApiResponse<Employee> update(@RequestBody Employee userDto) {
		Optional<Employee> optionalUser = empRepository.findById(userDto.getId());
		if (optionalUser.isPresent()) {
			BeanUtils.copyProperties(userDto, optionalUser.get(), "password", "username");
		}
		return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", empRepository.save(optionalUser.get()));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> delete(@PathVariable int id) {
		empRepository.deleteById(id);
		return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
	}

}
