package com.fmattaperdomo.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id")
    private Integer autorId;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "nacionalidad", length = 50, nullable = false)
    private String nacionalidad;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedDate;

    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL)
    private List<Libro> libros;

}
