package br.com.employeems.module.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.employeems.module.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameIgnoreCaseContaining(String name);

    List<Employee> findByClientId(Long id);

    Boolean existsByClientId(Long id);

}