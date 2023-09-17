package com.maddytec.elasticsearch.repository;

import com.maddytec.elasticsearch.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;


public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {
    Optional<Employee> findByName(String name);
}
