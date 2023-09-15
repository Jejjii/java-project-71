package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Diff {
    private final Map<String, Object> changes;

    public Diff() {
        this.changes = new HashMap<>();
    }

    public void addChange(String key, Object value) {
        changes.put(key, value);
    }

    public List<Map.Entry<String, Object>> getChanges() {
        return new ArrayList<>(changes.entrySet());
    }
}
