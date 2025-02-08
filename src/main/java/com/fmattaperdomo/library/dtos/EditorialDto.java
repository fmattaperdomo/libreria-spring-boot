package com.fmattaperdomo.library.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditorialDto {
    private Long editorialId;
    private String nombre;
    private String ubicacion;
    private Date createdDate;
    private Date updatedDate;
}
