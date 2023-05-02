package com.example.demo.repositories;

import java.util.ArrayList;

import com.example.demo.models.ClienteModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<ClienteModel, Long> {
    public abstract ArrayList<ClienteModel> findByPrioridad(Integer prioridad);

}