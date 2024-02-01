package com.academia.controller;

import com.academia.dto.StudentDTO;
import com.academia.exception.ModelNotFoundException;
import com.academia.model.Student;
import com.academia.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService service;

    @Autowired
    @Qualifier("studentMapper")
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        Student std = service.create(mapper.map(studentDTO, Student.class));
        return new ResponseEntity<>(mapper.map(std, StudentDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO>  update(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        Student studentFilter = service.readById(studentDTO.getId());

        if(studentFilter == null){
            throw new ModelNotFoundException("ID NOT FOUND" + studentDTO.getId());
        }

        Student student = service.update(mapper.map(studentDTO, Student.class));
        return new ResponseEntity<>(mapper.map(student, StudentDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        List<StudentDTO> list = service.readAll().stream().map(std -> mapper.map(std,StudentDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Student student = service.readById(id);

        if(student == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
        }

        return new ResponseEntity<>(mapper.map(student, StudentDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Student studentFilter = service.readById(id);

        if(studentFilter == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Listar Estudiantes por Edad de forma descendente
    @GetMapping("/ageDescendent")
    public ResponseEntity<List<StudentDTO>> ageDescendent() throws Exception{
        List<StudentDTO> list = service.ageDescendent().stream().map(std -> mapper.map(std, StudentDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
