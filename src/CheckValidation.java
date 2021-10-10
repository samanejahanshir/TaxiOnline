import exception.NumberException;
import exception.StringException;
import exception.TagFormatException;

public class CheckValidation {
    public static boolean checkInt(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                throw  new NumberException("this is not number");
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
    public static boolean isValidateTagVehicle(String inputString) {
        if(inputString.length()!=9){
            throw new TagFormatException("==> length of tag is != 9 ");
        }
        for(int i=0;i<inputString.length();i++) {
            if(i==2) {
                if (!checkString(inputString.charAt(2) + "")) {
                    throw new TagFormatException("third character of tag vehicle should be alphabet ");
                }
            }
            if(i==6){
                if(inputString.charAt(i)!='-'){
                    throw  new TagFormatException("7th character of tag should be - ");
                }
            }
            else if (i!=2 && i!=6){
                if(!checkInt(inputString.charAt(i)+"")){
                    throw new TagFormatException("tag format is not valid you should enter number");
                }
            }
        }
        return true;
    }
public  static  boolean checkOriginFormat(String origin){
        String[] tempOrigin=origin.split(",");
       if( checkInt(tempOrigin[0]) && checkInt(tempOrigin[1])){
           return  true;
       }
        else {
            return  false;
       }
}
    }
