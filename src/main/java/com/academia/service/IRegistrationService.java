package com.academia.service;

import com.academia.model.Registration;
import com.academia.model.Registration_Detail;
import com.academia.model.Student;

import java.util.List;
import java.util.Map;

public interface IRegistrationService extends ICRUD<Registration, Integer> {

    Map<String, String> courseRegister();
}
