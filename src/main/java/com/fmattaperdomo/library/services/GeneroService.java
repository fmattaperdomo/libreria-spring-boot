package com.fmattaperdomo.library.services;

import com.fmattaperdomo.library.dtos.GeneroDto;
import com.fmattaperdomo.library.dtos.LibroDto;

public interface GeneroService {
    GeneroDto crearGenero(Integer libroId, Integer generoId, GeneroDto generoDto);
    GeneroDto borrarGenero(Integer libroId);
}
