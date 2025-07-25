package validation;

public class Validator {
    public static boolean isEmpty(String inputData){
        if(inputData == null || inputData.isEmpty()){
            return true; //rong
        }
        return false;
    }
    public static boolean isInt(String inputData){
       if(inputData == null || inputData.isEmpty()){
           return false; // rong
       }
       try{
           Integer.parseInt(inputData);
           return true;
       }catch(Exception e){
           return false;
       }
    }
    public static boolean isFloat(String inputData){
        if(inputData == null || inputData.isEmpty()){
            return false; // rong
        }
        try{
            Float.parseFloat(inputData);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    public static boolean isValidCustomerPhone(String phone){
        phone = phone.trim();
        if(phone.length() > 20){
            return false;
        }
        String regex = "^(0|\\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-9]|9[0-9])\\d{7}$";
        return phone.matches(regex);
    }
    public static boolean isValidEmail(String email) {
        email = email.trim();
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(regex);
    }
}
