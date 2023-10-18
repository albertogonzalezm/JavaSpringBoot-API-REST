package com.example.apirest.model.payload;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author gonza
 */
@Data
@ToString
@Builder
public class ResponseMessage implements Serializable {

    private String message;
    private Object object;
    private List list;
}
