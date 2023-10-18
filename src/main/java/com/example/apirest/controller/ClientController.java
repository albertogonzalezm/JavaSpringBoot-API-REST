/*
 * 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirest.controller;

import com.example.apirest.model.dto.ClientDto;
import com.example.apirest.model.entity.Client;
import com.example.apirest.model.payload.ResponseMessage;
import com.example.apirest.service.IClient;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gonza
 */
@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private IClient clientService;

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto) {
        Client clientSave = null;
        try {
            Client findClient = clientService.findById(clientDto.getId());

            if (findClient != null) {
                return new ResponseEntity<>(
                        ResponseMessage.builder()
                                .message("Client has already Exist")
                                .object(null)
                                .build(),
                        HttpStatus.CONFLICT);
            } else {
                clientSave = clientService.save(clientDto);
                clientDto = ClientDto.builder()
                        .id(clientSave.getId())
                        .name(clientSave.getName())
                        .lastname(clientSave.getLastname())
                        .email(clientSave.getEmail())
                        .created_at(clientSave.getCreated_at())
                        .build();

                return new ResponseEntity<>(
                        ResponseMessage.builder()
                                .message("A new client was saved")
                                .object(clientDto)
                                .build(),
                        HttpStatus.CREATED);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(e.getMessage())
                            .object(clientSave)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("client/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDto clientDto, @PathVariable Integer id) {
        Client clientUpdate = null;
        try {
            Client findClient = clientService.findById(id);

            if (findClient != null) {
                clientUpdate = clientService.save(clientDto);
                clientDto = ClientDto.builder()
                        .id(findClient.getId())
                        .name(clientUpdate.getName())
                        .lastname(clientUpdate.getLastname())
                        .email(clientUpdate.getEmail())
                        .created_at(findClient.getCreated_at())
                        .build();

                return new ResponseEntity<>(
                        ResponseMessage.builder()
                                .object(clientDto)
                                .build(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        ResponseMessage.builder()
                                .message("Client Not Found")
                                .object(clientUpdate)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            }

        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(e.getMessage())
                            .object(clientUpdate)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Client removedClient = null;
        try {
            removedClient = clientService.findById(id);
            clientService.delete(removedClient);

            return new ResponseEntity<>(removedClient, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(e.getMessage())
                            .object(removedClient)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {

        Client client = clientService.findById(id);

        if (client == null) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("Client not found")
                            .object(client)
                            .build(),
                    HttpStatus.NOT_FOUND);
        }

        ClientDto clientDto = ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .lastname(client.getLastname())
                .email(client.getEmail())
                .created_at(client.getCreated_at())
                .build();

        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .object(clientDto)
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("clients")
    public ResponseEntity<?> showAll() {

        List<Client> clients = clientService.findAll();

        if (clients == null || clients.isEmpty()) {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("Client list is Empty")
                            .list(clients)
                            .build(),
                    HttpStatus.NOT_FOUND);
        }

        List<ClientDto> clientsDto = clients.stream().map(client -> ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .lastname(client.getLastname())
                .email(client.getEmail())
                .created_at(client.getCreated_at())
                .build()).collect(Collectors.toList());

        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .list(clientsDto)
                        .build(), HttpStatus.OK);
    }

}
