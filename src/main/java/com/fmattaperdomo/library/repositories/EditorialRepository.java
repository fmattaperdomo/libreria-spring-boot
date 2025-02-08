package com.fmattaperdomo.library.repositories;

import com.fmattaperdomo.library.entities.Autor;
import com.fmattaperdomo.library.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
}
