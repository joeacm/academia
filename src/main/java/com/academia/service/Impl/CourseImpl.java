package com.academia.service.Impl;

import com.academia.model.Course;
import com.academia.repo.ICourseRepo;
import com.academia.repo.IGenericRepo;
import com.academia.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseImpl extends CRUDImpl<Course, Integer> implements ICourseService {

    @Autowired
    private ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}
