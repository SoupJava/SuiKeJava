package Common;


import java.util.ArrayList;

public class Cookie {
    private static String UserID;
    private static String ChoiceClass;

    private static ArrayList<Double> Mark;

    public static String getUserID() {
        return UserID;
    }

    public static String getChoiceClass() {
        return ChoiceClass;
    }

    public static void setUserID(String userID) {
        UserID = userID;
    }

    public static void setChoiceClass(String choiceclass) {
        ChoiceClass = choiceclass;
    }

    public static void setMark(ArrayList<Double> mark) {
        Mark = mark;
    }

    public static ArrayList<Double> getMark() {
        return Mark;
    }

    public static void clearMark(int count) {
        for (int i = 0; i < count; i++)
            Mark.remove(i);
    }
}
