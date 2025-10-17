package com.hanhdoan.employee_management.util;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {
    public String formatName(String rawName) {
        if (rawName == null || rawName.isBlank()) return "";
        rawName = rawName.trim().toLowerCase();
        String[] words = rawName.split("\\s+");
        StringBuilder formatted = new StringBuilder();
        for (String w : words) {
            formatted.append(Character.toUpperCase(w.charAt(0)))
                     .append(w.substring(1))
                     .append(" ");
        }
        return formatted.toString().trim();
    }

    public String generateEmployeeCode(int counter) {
        return String.format("EMP%06d", counter);
    }
}
