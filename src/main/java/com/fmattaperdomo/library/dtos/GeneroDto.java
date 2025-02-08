package com.fmattaperdomo.library.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneroDto {
    private Long generoId;
    private String nombre;
    private Date createdDate;
    private Date updatedDate;
}
