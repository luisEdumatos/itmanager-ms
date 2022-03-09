package br.com.employeems.module.employee.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.employeems.module.employee.dto.EmployeeRequest;
import br.com.employeems.module.employee.dto.EmployeeResponse;
import br.com.employeems.module.employee.service.EmployeeService;
import br.com.employeems.util.exception.EmployeeNotFoundException;
import br.com.employeems.util.response.Response;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping(produces = "application/json")
    public List<EmployeeResponse> listAll() {
        return employeeService.listAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public EmployeeResponse findById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.findById(id);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createEmployee(@RequestBody @Valid EmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Response updateByID(@PathVariable Long id, @RequestBody @Valid EmployeeRequest request) throws EmployeeNotFoundException {
        return employeeService.updateByID(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteById(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeeService.delete(id);
    }
}