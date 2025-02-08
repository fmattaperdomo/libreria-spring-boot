package com.fmattaperdomo.library.services;

import com.fmattaperdomo.library.dtos.LibroResponse;
import com.fmattaperdomo.library.dtos.LibroDto;

public interface LibroService {
    LibroResponse buscarTodosLibros(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    LibroDto buscarUnLibro(Integer libroId);
    LibroDto crearLibro(Integer editorialId, Integer autorId, LibroDto libroDto);
    LibroDto borrarLibro(Integer libroId);
    LibroDto modificarLibro(Integer libroId, LibroDto libroDto);
    LibroResponse buscarLibroByAutor(Integer autorId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    LibroResponse buscarLibroByEditorial(Integer editorialId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
