package com.maddytec.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "employee")
public class Employee {
    @Id
    private String id;
    private String name;
    private String department;
    private double salary;
}
