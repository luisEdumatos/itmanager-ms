package br.com.clientms.module.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.clientms.module.client.dto.ClientRequest;
import br.com.clientms.module.client.dto.ClientResponse;
import br.com.clientms.module.client.model.Client;
import br.com.clientms.module.client.repository.ClientRepository;
import br.com.clientms.util.exceptions.ClientNotFoundException;
import br.com.clientms.util.response.Response;
import br.com.clientms.util.validation.ClientValidation;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientResponse> listAll() {
        return clientRepository
                .findAll()
                .stream()
                .map(ClientResponse::of)
                .toList();
    }

    public ClientResponse findById(Long id) throws ClientNotFoundException {
        return clientRepository
                .findById(id)
                .map(ClientResponse::of)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public Response createClient(ClientRequest clientRequest) {
        ClientValidation.clientCreateValidation(clientRequest,
                                                existsByName(clientRequest.getName()),
                                                existsByCnpj(clientRequest.getCnpj()));

        Client savedClient = clientRepository.save(Client.of(clientRequest));

        return new Response(HttpStatus.CREATED.value(), "Created client with ID " + savedClient.getId());
    }

    public Response updateById(Long id, ClientRequest clientRequest) throws ClientNotFoundException {
        ClientResponse clientResponse = findById(id);

        ClientValidation.clientUpdateValidation(clientRequest, clientResponse, existsByName(clientRequest.getName()), existsByCnpj(clientRequest.getCnpj()));

        clientRepository.save(createClientToUpdate(id, clientRequest));

        return new Response(HttpStatus.OK.value(), "Updated client with ID " + id);
    }

    private Client createClientToUpdate(Long id, ClientRequest request) {
        var clientToUpdate = Client.of(request);
        clientToUpdate.setId(id);
        return clientToUpdate;
    }

    public Response delete(Long id) throws ClientNotFoundException {
        findById(id);
        clientRepository.deleteById(id);

        return new Response(HttpStatus.OK.value(), "Deleted client with ID " + id);
    }

    public boolean existsByName(String name) {
        return clientRepository.existsByNameIgnoreCaseContaining(name);
    }

    public boolean existsByCnpj(String cnpj) {
        return clientRepository.existsByCnpj(cnpj);
    }
}