package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dialog.Dialog;

public class CheckInput {
    public static boolean serialNumberCheck(String serialNumber){
        Pattern serialNumberPattern = Pattern.compile("[0-9]{4,10}");
        Matcher serialNumberMatcher = serialNumberPattern.matcher(serialNumber.toString());

        boolean result = serialNumberMatcher.matches();
        if (result)
            return result;
        Dialog.errorWindow("Ошибка!", "Серийный номер не указан или указан некорректно.\nСерийный номер может содержать только цифры, его длина - от 4 до 10 символов.");
        return result;
    }
}
