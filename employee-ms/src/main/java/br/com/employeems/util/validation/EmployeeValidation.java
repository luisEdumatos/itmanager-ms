package br.com.employeems.util.validation;

import static org.springframework.util.ObjectUtils.isEmpty;

import br.com.employeems.module.employee.dto.EmployeeRequest;
import br.com.employeems.util.exception.ValidationException;

public class EmployeeValidation {
    private static final int DATE_SIZE = 10;

    public static void employeeCreateOrUpdateValidation(EmployeeRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The employee's name was not informed.");
        }

        if (isEmpty(request.getAdmissionDate())) {
            throw new ValidationException("The employee's admission date was not informed.");
        }

        if (isEmpty(request.getClientId())) {
            throw new ValidationException("The client ID was not informed.");
        }

        if (request.getAdmissionDate().length() != DATE_SIZE) {
            throw new ValidationException("The employee's admission date must have exactly 10 digits, including the separator bars.");
        }

        if (request.getIntegrationDate() != null && request.getIntegrationDate().length() != DATE_SIZE) {
            throw new ValidationException("The employee's integration date must have exactly 10 digits, including the separator bars.");
        }

        if (request.getResignationDate() != null && request.getResignationDate().length() != DATE_SIZE) {
            throw new ValidationException("The employee's resignation date must have exactly 10 digits, including the separator bars.");
        }
    }
}
