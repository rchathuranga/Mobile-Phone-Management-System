package lk.ijse.projectmp.dao;

import java.util.ArrayList;

public interface CrudDAO<T,Id> extends SuperDAO{
     boolean add(T t) throws Exception;
     boolean delete(Id id) throws Exception;
     boolean update(T t) throws Exception;
     T search(Id id) throws Exception;
     ArrayList<T> getAll() throws Exception;
}
