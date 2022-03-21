package br.com.employeems.module.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.employeems.config.proxy.ClientProxy;
import br.com.employeems.module.employee.dto.ClientResponse;
import br.com.employeems.module.employee.dto.EmployeeRequest;
import br.com.employeems.module.employee.dto.EmployeeResponse;
import br.com.employeems.module.employee.model.Employee;
import br.com.employeems.module.employee.repository.EmployeeRepository;
import br.com.employeems.util.exception.EmployeeNotFoundException;
import br.com.employeems.util.exception.ValidationException;
import br.com.employeems.util.response.Response;
import br.com.employeems.util.validation.EmployeeValidation;
import feign.FeignException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    
    private ClientProxy clientProxy; 

    public List<EmployeeResponse> listAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(EmployeeResponse::of)
                .toList();
    }

    public EmployeeResponse findById(Long id) throws EmployeeNotFoundException {
        return employeeRepository
                .findById(id)
                .map(EmployeeResponse::of)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Response createEmployee(EmployeeRequest request) {
        EmployeeValidation.employeeCreateOrUpdateValidation(request);

        var client = getClient(request.getClientId());  
                        		
        var employee = employeeRepository.save(Employee.of(request, client));

        return new Response(HttpStatus.CREATED.value(), "Created employee with ID " + employee.getId());
    }

    public Response updateByID(Long id, EmployeeRequest request) throws EmployeeNotFoundException {
        findById(id);

        EmployeeValidation.employeeCreateOrUpdateValidation(request);

        employeeRepository.save(createEmployeeToUpdate(id, request));

        return new Response(HttpStatus.OK.value(), "Updated employee with ID " + id);
    }

    private Employee createEmployeeToUpdate(Long id, EmployeeRequest request) {

    	var client = getClient(request.getClientId());  
        
        var employeeToUpdate = Employee.of(request, client);
        employeeToUpdate.setId(id);

        return  employeeToUpdate;
    }
    
    private ClientResponse getClient(Long clientID) {       
        try {
        	var client = clientProxy.findById(clientID);
        	return client; 
        } catch (FeignException ex) {
        	throw new ValidationException("Client not found." + ex.getMessage());
        }
    }
    
    public Response delete(Long id) throws EmployeeNotFoundException {
        findById(id);
        employeeRepository.deleteById(id);

        return new Response(HttpStatus.OK.value(), "Deleted employee with ID " + id);
    }
}