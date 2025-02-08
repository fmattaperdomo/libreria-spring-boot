package com.fmattaperdomo.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorDto {
    private Long autorId;
    private String nombre;
    private String nacionalidad;
    private Integer edad;
    private Date createdDate;
    private Date updatedDate;
}
