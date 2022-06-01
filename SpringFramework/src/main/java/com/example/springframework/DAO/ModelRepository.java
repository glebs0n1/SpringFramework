package com.example.springframework.DAO;

import com.example.springframework.model.MyModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository< MyModel,Integer > {
}
