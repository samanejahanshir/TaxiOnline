import exception.StringException;

public class CheckValidation {
    public static boolean checkInt(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                throw  new NumberFormatException();
            }
        }
        return true;
    }

    public static boolean checkString(String inputString) {
        for (int i = 0; i < inputString.length(); i++) {
            if (!Character.isLetter(inputString.charAt(i)) && inputString.charAt(i)!=' ') {
                throw new StringException("this name is not valid");
            }
        }
        return true;
    }
}
