package br.com.employeems.module.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @JsonProperty("client_id")
    private Long clientId;
    private String name;
    @JsonProperty("admission_date")
    private String admissionDate;
    @JsonProperty("integration_date")
    private String integrationDate;
    @JsonProperty("resignation_date")
    private String resignationDate;
    @JsonProperty("phone_number")
    private String phoneNumber;
}
