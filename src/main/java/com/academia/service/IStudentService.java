package com.academia.service;

import com.academia.model.Student;

import java.util.List;

public interface IStudentService extends ICRUD<Student, Integer>{

    List<Student> ageDescendent();
}
