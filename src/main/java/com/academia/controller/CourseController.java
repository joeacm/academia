package com.academia.controller;

import com.academia.dto.CourseDTO;
import com.academia.exception.ModelNotFoundException;
import com.academia.model.Course;
import com.academia.service.ICourseService;
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
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private ICourseService service;

    @Autowired
    @Qualifier("courseMapper")
    private ModelMapper mapper;
    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO courseDTO) throws Exception{
        Course cor = service.create(mapper.map(courseDTO, Course.class));
        return new ResponseEntity<>(mapper.map(cor, CourseDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO courseDTO) throws Exception{
        Course courseFilter = service.readById(courseDTO.getId());

        if(courseFilter == null){
            throw new ModelNotFoundException("ID NOT FOUND" + courseDTO.getId());
        }

        Course course = service.update(mapper.map(courseDTO, Course.class));
        return new ResponseEntity<>(mapper.map(course, CourseDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception{
        List<CourseDTO> list = service.readAll().stream().map(course -> mapper.map(course, CourseDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Course course = service.readById(id);

        if(course == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
        }

        return new ResponseEntity<>(mapper.map(course, CourseDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Course course = service.readById(id);

        if(course == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
