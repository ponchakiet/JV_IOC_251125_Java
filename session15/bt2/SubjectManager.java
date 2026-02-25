package bt2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectManager<T> {
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

    public Optional<T> findFirst(java.util.function.Predicate<T> predicate) {
        return items.stream().filter(predicate).findFirst(); // Stream + Optional
    }

    public List<T> filter(java.util.function.Predicate<T> predicate) {
        return items.stream().filter(predicate).toList(); // Stream + Lambda
    }
}
