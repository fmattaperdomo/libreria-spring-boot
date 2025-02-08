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
@Table(name = "editoriales")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "editorial_id")
    private Integer editorialId;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "ubicacion", length = 50, nullable = false)
    private String ubicacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedDate;

    @OneToMany(mappedBy = "editorial",cascade = CascadeType.ALL)
    private List<Libro> libros;
}
