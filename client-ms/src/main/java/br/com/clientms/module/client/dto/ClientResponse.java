package br.com.clientms.module.client.dto;

import org.springframework.beans.BeanUtils;

import br.com.clientms.module.client.model.Client;
import lombok.Data;

@Data
public class ClientResponse {

    private Long id;
    private String name;
    private String cnpj;
    private String address;

    public static ClientResponse of(Client client) {
        var response = new ClientResponse();
        BeanUtils.copyProperties(client, response);
        return  response;
    }
}