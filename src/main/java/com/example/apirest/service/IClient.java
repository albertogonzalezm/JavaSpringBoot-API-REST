/*
 * 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirest.service;

import com.example.apirest.model.entity.Client;
import com.example.apirest.model.dto.ClientDto;
import java.util.List;

/**
 *
 * @author gonza
 */
public interface IClient {

    Client save(ClientDto clientDto);

    List<Client> findAll();
    
    Client findById(Integer id);
    
    void delete(Client client);
}
