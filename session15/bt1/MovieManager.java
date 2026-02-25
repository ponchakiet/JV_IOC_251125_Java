package bt1;
import java.util.ArrayList;
import java.util.List;

public class MovieManager<T> {
    private final ArrayList<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public boolean removeIf(java.util.function.Predicate<T> predicate) {
        return items.removeIf(predicate);
    }

    public T findFirst(java.util.function.Predicate<T> predicate) {
        for (T item : items) {
            if (predicate.test(item)) return item;
        }
        return null;
    }

    public List<T> filter(java.util.function.Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) result.add(item);
        }
        return result;
    }
}
