package hexlet.code;

import java.util.Objects;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Diff {

    private static Map<String, Object> getDiffmap(String entry, Object value, Object newValue,
                                                  DiffersStates differencesState) {
        Map<String, Object> diffmap = new LinkedHashMap<>();
        diffmap.put("key", entry);
        diffmap.put("Difference", differencesState);
        if (differencesState == DiffersStates.updated) {
            diffmap.put("file1Value", value);
            diffmap.put("file2Value", newValue);
        } else if (differencesState == DiffersStates.added) {
            diffmap.put("value", newValue);
        } else  {
            diffmap.put("value", value);
        }

        return diffmap;
    }

    public static List<Map<String, Object>> getTreeMapsDifferencesList(Map<String, Object> treeMap1,
                                                                       Map<String, Object> treeMap2) {
        Set<String> keySet = new TreeSet<>(treeMap1.keySet());
        keySet.addAll(treeMap2.keySet());

        List<Map<String, Object>> diffList = new ArrayList<>();
        for (String entry : keySet) {
            DiffersStates differencesState = DiffersStates.notChanged;
            if (!treeMap2.containsKey(entry)) {
                differencesState = DiffersStates.removed;
            } else if (!treeMap1.containsKey(entry)) {
                differencesState = DiffersStates.added;
            } else if (!Objects.equals(treeMap1.get(entry), treeMap2.get(entry))) {
                differencesState = DiffersStates.updated;
            }

            diffList.add(getDiffmap(entry, treeMap1.get(entry), treeMap2.get(entry), differencesState));
        }

        return diffList;
    }

}
