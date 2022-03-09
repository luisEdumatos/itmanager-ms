package br.com.employeems.module.employee.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.com.employeems.module.employee.dto.ClientResponse;
import br.com.employeems.module.employee.dto.EmployeeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FK_CLIENT", nullable = false)
    private Long clientId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADMISSION_DATE", nullable = false)
    @Size(min = 10, max = 10)
    private String admissionDate;

    @Column(name = "INTEGRATION_DATE")
    @Size(min = 10, max = 10)
    private String integrationDate;

    @Column(name = "RESIGNATION_DATE")
    @Size(min = 10, max = 10)
    private String resignationDate;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public static Employee of(EmployeeRequest request, ClientResponse clientResponse) {
       
        return Employee
                .builder()
                .name(request.getName())
                .clientId(clientResponse.getId())
                .admissionDate(request.getAdmissionDate())
                .integrationDate(request.getIntegrationDate())
                .resignationDate(request.getResignationDate())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
}
