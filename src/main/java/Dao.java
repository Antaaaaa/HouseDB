import java.util.List;
public interface Dao<T> {
    List<T> getList();
    void insertData(T t);
    T findById(int id);
    void updateData(T t, String[] args);
    void deleteData(int id);
}
