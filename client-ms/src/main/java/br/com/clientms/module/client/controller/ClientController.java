package br.com.clientms.module.client.controller;

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

import br.com.clientms.module.client.dto.ClientRequest;
import br.com.clientms.module.client.dto.ClientResponse;
import br.com.clientms.module.client.service.ClientService;
import br.com.clientms.util.exceptions.ClientNotFoundException;
import br.com.clientms.util.response.Response;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/client")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private ClientService clientService;

    @GetMapping(produces = "application/json")
    public List<ClientResponse> listAll() {
        return clientService.listAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ClientResponse findById(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.findById(id);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createClient(@RequestBody @Valid ClientRequest request) {
        return clientService.createClient(request);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public Response updateById(@PathVariable Long id, @RequestBody @Valid ClientRequest request) throws ClientNotFoundException {
        return clientService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteById(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.delete(id);
    }
}
