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

}
