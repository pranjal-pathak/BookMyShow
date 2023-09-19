package com.scaler.bookmyshow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass       //tells spring jpa that this class is not just a super class but a mapped superclass i.e we expect rows and columns with these attributes in the db
public class BaseModel {

    //basemodel will contain attributes that are common to al the entities.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //primary key for all the classes that extend base model
    private Date createdAt;
    private Date lastModifiedAt;

}
