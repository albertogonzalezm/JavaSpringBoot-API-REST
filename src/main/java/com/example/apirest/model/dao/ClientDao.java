/*
 * 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.apirest.model.dao;

import com.example.apirest.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author gonza
 */
public interface ClientDao extends CrudRepository<Client, Integer>{
    
}
