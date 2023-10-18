package com.example.apirest.service.impl;

import com.example.apirest.model.dao.ClientDao;
import com.example.apirest.model.entity.Client;
import com.example.apirest.model.dto.ClientDto;
import com.example.apirest.service.IClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gonza
 */
@Service
public class ClientImpl implements IClient {

    @Autowired
    private ClientDao clientDao;

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .lastname(clientDto.getLastname())
                .email(clientDto.getEmail())
                .created_at(clientDto.getCreated_at())
                .build();
        return clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Integer id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public List<Client> findAll() {
        return (List) clientDao.findAll();
    }

}
