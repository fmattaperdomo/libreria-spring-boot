package com.fmattaperdomo.library.repositories;

import com.fmattaperdomo.library.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
