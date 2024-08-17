package tech.starvingdevelopers.smnbackend.utils;

import java.util.HashMap;
import java.util.Map;

public class ConvertNameUtils {
    private static Map<String, String> keys = new HashMap<>();

    static {
        keys.put("á", "a");
        keys.put("à", "a");
        keys.put("â", "a");
        keys.put("ã", "a");
        keys.put("é", "e");
        keys.put("è", "e");
        keys.put("ê", "e");
        keys.put("í", "i");
        keys.put("ì", "i");
        keys.put("î", "i");
        keys.put("ó", "o");
        keys.put("ò", "o");
        keys.put("ô", "o");
        keys.put("õ", "o");
        keys.put("ú", "u");
        keys.put("ù", "u");
        keys.put("û", "u");
        keys.put("ç", "c");
    }

    public static String formatName(String customName) {
        String searchableName = customName.toLowerCase();
        for (Map.Entry<String, String> entry : keys.entrySet()) {
            if (searchableName.contains(entry.getKey())) {
                searchableName = searchableName.replace(entry.getKey(), entry.getValue());
            }
        }

        return searchableName;
    }
}
