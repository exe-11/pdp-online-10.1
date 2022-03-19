package uz.pdp.hotelcrud.service;

import java.util.List;

public interface BaseService<T, R> {

    List<R> getAll();
    R getOne(Integer id);
    R add(T t);
    String delete(Integer id);
    String edit(Integer id, T t);

}
