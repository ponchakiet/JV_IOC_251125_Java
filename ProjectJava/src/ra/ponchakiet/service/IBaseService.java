package ra.ponchakiet.service;

import java.util.List;

public interface IBaseService<T, ID> {
    void add(T t);
    void update(T t, ID id);
    void delete(ID id);
    T findById(ID id);
    List<T> findAll();
    void sort(ID id, ID id1);
}
