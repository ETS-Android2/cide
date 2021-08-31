package Projectos.Coche;

/*

    Project     Programming21
    Package     Utilitats    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-09

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */


public class Encapsulate {
    public static void encapsulateString(String toEncapsulate, String escapeCharacter){
        encapsulate(toEncapsulate.length(),toEncapsulate,escapeCharacter);
    }
    public static void encapsulateString(String toEncapsulate, String escapeCharacter, String spaceCharacter){
        encapsulate(toEncapsulate.length(),toEncapsulate,escapeCharacter,spaceCharacter);
    }
    public static void encapsulateString(String color, String toEncapsulate, String escapeCharacter, String spaceCharacter){
        int lengthOfString = toEncapsulate.length();
        toEncapsulate = ConsoleColorsLite.stringColor(color,toEncapsulate);
        encapsulate(lengthOfString,toEncapsulate,escapeCharacter,spaceCharacter);
    }

    private static void encapsulate(int stringLength,String toEncapsulate, String escapeCharacter){

        boolean firstPhaseCheck = false, secondPhaseCheck = false;
        boolean stringCheck = false;

        for (int i = 0; i < ((stringLength + 1) * 3); i++) {

            // First phase
            if(!firstPhaseCheck){
                if(i == 0){
                    System.out.print(escapeCharacter);
                }
                System.out.print("-");
                if(i == (stringLength + 1)){
                    firstPhaseCheck = true;
                }
            } else if(!secondPhaseCheck){
                if(i == (stringLength + 2)){
                    System.out.print("\n" + escapeCharacter +"|");
                } else if(i == ((stringLength * 2) + 1)){
                    System.out.print("|");
                    secondPhaseCheck = true;
                } else if(!stringCheck){
                    System.out.print(toEncapsulate);
                    stringCheck = true;
                }
            } else {
                if(i == ((stringLength * 2) + 2)){
                    System.out.print("\n" + escapeCharacter + "-");
                }
                System.out.print("-");
            }
        }
    }
    private static void encapsulate(int stringLength, String toEncapsulate, String escapeCharacter, String spaceCharacter){

        boolean firstPhaseCheck = false, secondPhaseCheck = false;
        boolean stringCheck = false;

        for (int i = 0; i < ((stringLength + 1) * 3); i++) {

            // First phase
            if(!firstPhaseCheck){
                if(i == 0){
                    System.out.print(escapeCharacter);
                }
                System.out.print(spaceCharacter);
                if(i == (stringLength + 1)){
                    firstPhaseCheck = true;
                }
            } else if(!secondPhaseCheck){
                if(i == (stringLength + 2)){
                    System.out.print("\n" + escapeCharacter +"|");
                } else if(i == ((stringLength * 2) + 1)){
                    System.out.print("|");
                    secondPhaseCheck = true;
                } else if(!stringCheck){
                    System.out.print(toEncapsulate);
                    stringCheck = true;
                }
            } else {
                if(i == ((stringLength * 2) + 2)){
                    System.out.print("\n" + escapeCharacter + spaceCharacter);
                }
                System.out.print(spaceCharacter);
            }
        }
    }

}