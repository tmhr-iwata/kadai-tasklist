package models.validators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import models.Task;

public class TaskValidator {
    public static List<String> validate(Task t){
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(t.getTitle());
        if (!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = _validateContent(t.getContent());
        if (!content_error.equals("")){
            errors.add(content_error);
        }

        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        String fmtedDay = null;
        String fmtedTime = null;
        if(t != null){
            fmtedDay = sdfDay.format(t.getLimitday());
            fmtedTime = sdfTime.format(t.getLimitday());
        }
        String fLimitday = null;
        if (fmtedDay == null || fmtedTime == null){
            fLimitday = "1970/01/01 00:00:00";
        }
        fLimitday = fmtedDay + fmtedTime + ":00";
        String limitday_error = _validateLimitday(fLimitday);
        if (!limitday_error.equals("")){
            errors.add(limitday_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "タスクの概要を入力してください。";
        }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "タスクの詳細を入力してください。";
        }

        return "";
    }

    private static String _validateLimitday(String limitday) {
        if(limitday == null || limitday.equals("")) {
            return "タスクの期日を入力してください。";
        }

        return "";
    }
}
