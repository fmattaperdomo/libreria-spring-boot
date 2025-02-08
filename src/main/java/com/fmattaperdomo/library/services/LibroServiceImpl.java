package com.fmattaperdomo.library.services;

import com.fmattaperdomo.library.dtos.LibroDto;
import com.fmattaperdomo.library.dtos.LibroResponse;
import com.fmattaperdomo.library.entities.Autor;
import com.fmattaperdomo.library.entities.Editorial;
import com.fmattaperdomo.library.entities.Libro;
import com.fmattaperdomo.library.exceptions.APIException;
import com.fmattaperdomo.library.exceptions.ResourceNotFoundException;
import com.fmattaperdomo.library.repositories.AutorRepository;
import com.fmattaperdomo.library.repositories.EditorialRepository;
import com.fmattaperdomo.library.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LibroResponse buscarTodosLibros(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Libro> pageLibros = libroRepository.findAll(pageDetails);

        List<Libro> libros = pageLibros.getContent();

        List<LibroDto> libroDtos = libros.stream()
                .map(libro -> modelMapper.map(libro, LibroDto.class))
                .toList();

        LibroResponse libroResponse = new LibroResponse();
        libroResponse.setContent(libroDtos);
        libroResponse.setPageNumber(pageLibros.getNumber());
        libroResponse.setPageSize(pageLibros.getSize());
        libroResponse.setTotalElements(pageLibros.getTotalElements());
        libroResponse.setTotalPages(pageLibros.getTotalPages());
        libroResponse.setLastPage(pageLibros.isLast());
        return libroResponse;
    }

    @Override
    public LibroDto buscarUnLibro(Integer libroId) {
        Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new ResourceNotFoundException("Libro","libroId", libroId));
        return modelMapper.map(libro,LibroDto.class);
    }

    @Override
    public LibroDto crearLibro(Integer editorialId, Integer autorId, LibroDto libroDto) {
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Autor", "autorId", autorId));

        Editorial editorial = editorialRepository.findById(editorialId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ediotrial", "editorialId", editorialId));

        boolean isProductNotPresent = true;

        List<Libro> libros = autor.getLibros();
        for (Libro value : libros) {
            if (value.getTitulo().equals(libroDto.getTitulo())) {
                isProductNotPresent = false;
                break;
            }
        }

        if (isProductNotPresent) {
            Libro libro = modelMapper.map(libroDto, Libro.class);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            Libro savedLibro = libroRepository.save(libro);
            return modelMapper.map(savedLibro, LibroDto.class);
        } else {
            throw new APIException("Producto ya existe!!");
        }
    }

    @Override
    public LibroDto borrarLibro(Integer libroId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "libroId", libroId));

        libroRepository.delete(libro);
        return modelMapper.map(libro, LibroDto.class);
    }

    @Override
    public LibroDto modificarLibro(Integer libroId, LibroDto libroDto) {
        Libro libroFromDb = libroRepository.findById(libroId)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "libroId", libroId));

        Libro libro = modelMapper.map(libroDto, Libro.class);

        libroFromDb.setDescripcion(libro.getDescripcion());
        libroFromDb.setEditorial(libro.getEditorial());
        libroFromDb.setAutor(libro.getAutor());
        libroFromDb.setUpdatedDate(libro.getUpdatedDate());
        libroFromDb.setTitulo(libro.getTitulo());
        Libro savedLibro = libroRepository.save(libroFromDb);
        return modelMapper.map(savedLibro, LibroDto.class);
    }

    @Override
    public LibroResponse buscarLibroByAutor(Integer autorId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Autor", "autorId", autorId));

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Libro> pageLibros = libroRepository.findByAutorOrderByTituloAsc(autor, pageDetails);

        List<Libro> libros = pageLibros.getContent();

        if(libros.isEmpty()){
            throw new APIException(autor.getNombre() + " autor no tiene libros asociados");
        }

        List<LibroDto> libroDtos = libros.stream()
                .map(libro -> modelMapper.map(libro, LibroDto.class))
                .toList();

        LibroResponse libroResponse = new LibroResponse();
        libroResponse.setContent(libroDtos);
        libroResponse.setPageNumber(pageLibros.getNumber());
        libroResponse.setPageSize(pageLibros.getSize());
        libroResponse.setTotalElements(pageLibros.getTotalElements());
        libroResponse.setTotalPages(pageLibros.getTotalPages());
        libroResponse.setLastPage(pageLibros.isLast());
        return libroResponse;

    }

    @Override
    public LibroResponse buscarLibroByEditorial(Integer editorialId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Editorial editorial = editorialRepository.findById(editorialId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Editorial", "EditorialId", editorialId));

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Libro> pageLibros = libroRepository.findByEditorialOrderByTituloAsc(editorial, pageDetails);

        List<Libro> libros = pageLibros.getContent();

        if(libros.isEmpty()){
            throw new APIException(editorial.getNombre() + " editorial no tiene libros asociados");
        }

        List<LibroDto> libroDtos = libros.stream()
                .map(libro -> modelMapper.map(libro, LibroDto.class))
                .toList();

        LibroResponse libroResponse = new LibroResponse();
        libroResponse.setContent(libroDtos);
        libroResponse.setPageNumber(pageLibros.getNumber());
        libroResponse.setPageSize(pageLibros.getSize());
        libroResponse.setTotalElements(pageLibros.getTotalElements());
        libroResponse.setTotalPages(pageLibros.getTotalPages());
        libroResponse.setLastPage(pageLibros.isLast());
        return libroResponse;

    }
}
