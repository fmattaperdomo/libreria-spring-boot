package com.fmattaperdomo.library.controllers;

import com.fmattaperdomo.library.configurations.AppConstants;
import com.fmattaperdomo.library.dtos.LibroDto;
import com.fmattaperdomo.library.dtos.LibroResponse;
import com.fmattaperdomo.library.services.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponse> buscarTodosLibros(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_TITLE_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        LibroResponse libroResponse = libroService.buscarTodosLibros(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(libroResponse, HttpStatus.OK);
    }

    @GetMapping("/libros/{libroId}")
    public ResponseEntity<LibroDto> buscarUnLibro(@PathVariable Integer libroId){
        LibroDto libroDto = libroService.buscarUnLibro(libroId);
        return new ResponseEntity<>(libroDto, HttpStatus.OK);
    }

    @PostMapping("/libros")
    public ResponseEntity<LibroDto> crearLibro(@Valid @RequestBody LibroDto libroDto,
                                               @RequestParam Integer autorId,
                                               @RequestParam Integer editorialId
                                                 ){
        LibroDto savedLibroDto = libroService.crearLibro(autorId, editorialId, libroDto);
        return new ResponseEntity<>(savedLibroDto, HttpStatus.CREATED);
    }

    @PutMapping("/libros/{libroId}")
    public ResponseEntity<LibroDto> modificarLibro(@Valid @RequestBody LibroDto libroDto,
                                                    @PathVariable Integer libroId){
        LibroDto updatedLibroDto = libroService.modificarLibro(libroId, libroDto);
        return new ResponseEntity<>(updatedLibroDto, HttpStatus.OK);
    }

    @DeleteMapping("/libros/{libroId}")
    public ResponseEntity<LibroDto> borrarLibro(@PathVariable Integer libroId){
        LibroDto deletedLibro = libroService.borrarLibro(libroId);
        return new ResponseEntity<>(deletedLibro, HttpStatus.OK);
    }

    @GetMapping("/autores/{autorId}/libros")
    public ResponseEntity<LibroResponse> buscarLibroByAutor(@PathVariable Integer autorId,
                                                                 @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                 @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                 @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_TITLE_BY, required = false) String sortBy,
                                                                 @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        LibroResponse libroResponse = libroService.buscarLibroByAutor(autorId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(libroResponse, HttpStatus.OK);
    }

    @GetMapping("/editoriales/{editorialId}/libros")
    public ResponseEntity<LibroResponse> buscarLibroByEditorial(@PathVariable Integer editorialId,
                                                            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_TITLE_BY, required = false) String sortBy,
                                                            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        LibroResponse libroResponse = libroService.buscarLibroByAutor(editorialId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(libroResponse, HttpStatus.OK);
    }
}
