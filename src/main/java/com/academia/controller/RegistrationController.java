package com.academia.controller;

import com.academia.exception.ModelNotFoundException;
import com.academia.model.Registration;
import com.academia.model.Registration_Detail;
import com.academia.model.Student;
import com.academia.service.IRegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private IRegistrationService service;

    @Autowired
    @Qualifier("registerMapper")
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<com.academia.dto.RegistrationDTO> create(@Valid @RequestBody com.academia.dto.RegistrationDTO registrationDTO) throws Exception{
        Registration register = service.create(mapper.map(registrationDTO, Registration.class));
        return new ResponseEntity<>(mapper.map(register, com.academia.dto.RegistrationDTO.class), HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<com.academia.dto.RegistrationDTO> update(@Valid @RequestBody com.academia.dto.RegistrationDTO registrationDTO) throws Exception{
        Registration registerFilter = service.readById(registrationDTO.getId());

        if(registerFilter == null){
            throw new ModelNotFoundException("ID NOT FOUND" + registrationDTO.getId());
        }

        Registration register = service.update(mapper.map(registrationDTO, Registration.class));
        return new ResponseEntity<>(mapper.map(register, com.academia.dto.RegistrationDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<com.academia.dto.RegistrationDTO>> readAll() throws Exception{
        List<com.academia.dto.RegistrationDTO> list = service.readAll().stream().map(rgt -> mapper.map(rgt, com.academia.dto.RegistrationDTO.class)).collect(Collectors.toList());
        //System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.academia.dto.RegistrationDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Registration register = service.readById(id);

        if(register == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
        }

        return new ResponseEntity<>(mapper.map(register, com.academia.dto.RegistrationDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Registration registerFilter = service.readById(id);

        if(registerFilter == null){
            throw new ModelNotFoundException("ID NOT FOUND" + id);
        }

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/relation1")
    public ResponseEntity<Map<String,String>> courseAndStudent() throws Exception{
       return new ResponseEntity<>(service.courseRegister(), HttpStatus.OK);
    }

}
