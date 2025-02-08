package com.fmattaperdomo.library.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genero_id")
    private Integer generoId;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedDate;

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "libros_has_generos",
            joinColumns = @JoinColumn(name = "genero_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id"))
    private List<Libro> libros = new ArrayList<>();

}
