package com.fmattaperdomo.library.repositories;

import com.fmattaperdomo.library.entities.Autor;
import com.fmattaperdomo.library.entities.Editorial;
import com.fmattaperdomo.library.entities.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Page<Libro> findByAutorOrderByTituloAsc(Autor autor, Pageable pageDetails);
    Page<Libro> findByEditorialOrderByTituloAsc(Editorial editorial, Pageable pageDetails);
}
