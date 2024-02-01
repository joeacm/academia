package com.academia.service.Impl;

import com.academia.model.Registration;
import com.academia.model.Registration_Detail;
import com.academia.repo.ICourseRepo;
import com.academia.repo.IGenericRepo;
import com.academia.repo.IRegistrationRepo;
import com.academia.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationImpl extends CRUDImpl<Registration, Integer> implements IRegistrationService {

    @Autowired
    private IRegistrationRepo repo;

    @Autowired
    private ICourseRepo repo2;

    @Override
    protected IGenericRepo<Registration, Integer> getRepo() {
        return repo;
    }


    @Override
    public Map<String,String> courseRegister() {
        //List<String> courses = repo2.findAll().stream().map(Course::getName).collect(Collectors.toList());
        List<Registration_Detail> details = repo.findAll().stream()
                        .flatMap(e->e.getDetails().stream())
                        .collect(Collectors.toList());
        //System.out.println(details);
       /* repo.findAll().stream()
                .collect(Collectors.groupingBy(e -> e.getDetails().stream()
                        .map(r -> r.getCourse().getName())), )*/
    Map<String,String> mapNum1 = details.stream()
            .collect(Collectors.toMap
                    (r -> r.getCourse().getName(), r -> r.getRegistration().getStudent().getName(), (s, s2) -> {
                        return s + " || " + s2;
                        } ));

    /*Map<String, List<String>> mapNum2 = details.stream().collect(Collectors.toMap(e->e.getCourse().getName(), e -> e, (o, o2) -> {

        String student = r1.
            })*/

       /* Map<String, List<String>> mapFinal = new HashMap<>();
        Map<String, List<String>> mapFilter = repo.findAll().stream().
                collect(Collectors.toMap(e -> e.getStudent().getName(), e -> e.getDetails().stream().map(r -> {
                    if (mapFinal.containsKey(r.getCourse().getName())){

                    }
                } r.getCourse().getName()).collect(Collectors.toList())));

        repo.findAll().stream().map(e-> e.getDetails().stream().map(r->{
            if(mapFinal.containsKey(r.getCourse().getName())){

            }
        }))

        List<String> one = mapFilter.values().stream().flatMap(List::stream).collect(Collectors.toList());
        Set<String> set = new HashSet<>(one);
       // List<String> courses = repo2.findAll().stream().map(Course::getName).collect(Collectors.toList());

        System.out.println(mapFilter);*/
        return mapNum1;
    }



}
