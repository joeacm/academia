package com.academia.service.Impl;

import com.academia.model.Student;
import com.academia.repo.IGenericRepo;
import com.academia.repo.IStudentRepo;
import com.academia.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class StudentImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    @Autowired
    private IStudentRepo repo;
    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }


    @Override
    public List<Student> ageDescendent() {
        Comparator<Student> descendent = (x,y) -> y.getAge() - x.getAge();

        List<Student> list = repo.findAll().stream().sorted(descendent).collect(Collectors.toList());
        return list;
    }
}
