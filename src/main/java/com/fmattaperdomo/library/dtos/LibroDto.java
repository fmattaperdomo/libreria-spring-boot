package com.fmattaperdomo.library.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDto {
    private Long libroId;
    private String titulo;
    private String descripcion;
    private Date createdDate;
    private Date updatedDate;
}
