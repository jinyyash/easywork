package com.easywork.easywork.input.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputUtil {

    public static boolean isNullOrEmpty(String input){
      return input != null && !input.isBlank();
    }

    public static LocalDate getDate(String date)throws DateTimeParseException {
       return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
