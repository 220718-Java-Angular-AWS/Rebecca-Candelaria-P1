package com.revature.DAOs;

import java.util.List;

public interface DataSourceCRUD<T> { //Generic parameterized to a type
    //Interface that will be used in each of the DAOs
    //Setting forth a promise for what methods will be implemented
    //CRUD Create Read Update and Delete
    void create(T t);
    T read(int id);
   List<T> readAll();
    void update(T t);
    void delete(int id);
}
