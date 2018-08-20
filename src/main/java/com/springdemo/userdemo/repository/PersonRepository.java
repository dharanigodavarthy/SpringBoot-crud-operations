package com.springdemo.userdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdemo.userdemo.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
