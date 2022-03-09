package br.com.employeems.config.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.employeems.module.employee.dto.ClientResponse;

@FeignClient(name = "client-ms")
public interface ClientProxy {

	@GetMapping(value = "/api/client/{id}")
    public ClientResponse findById(@PathVariable Long id);
}
