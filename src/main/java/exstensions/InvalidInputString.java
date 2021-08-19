package exstensions;

public class InvalidInputString extends Exception{
 private String message;

 public InvalidInputString(String message){
  this.message = message;
 }
    @Override
    public String toString(){
        return message;
    }
}
