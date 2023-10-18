package com.example.apirest.model.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private Date created_at;

}
