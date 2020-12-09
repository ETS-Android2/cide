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

        int lengthOfString = toEncapsulate.length();

        String firstPhase,secondPhase,thirdPhase;
        boolean firstPhaseCheck = false, secondPhaseCheck = false, thirdPhaseCheck = false;
        boolean stringCheck = false;
        boolean firstChar = false;
        boolean secondChar = false;
        boolean thirdChar = false;

        for (int i = 0; i < ((lengthOfString + 1) * 3); i++) {

            // First phase
            if(!firstPhaseCheck){
                if(i == 0){
                    System.out.print(escapeCharacter);
                }
                System.out.print("-");
                if(i == (lengthOfString + 1)){
                    firstPhaseCheck = true;
                }
            } else if(!secondPhaseCheck){
                if(i == (lengthOfString + 2)){
                    System.out.print("\n" + escapeCharacter +"|");
                } else if(i == ((lengthOfString * 2) + 1)){
                    System.out.print("|");
                    secondPhaseCheck = true;
                } else if(!stringCheck){
                    System.out.print(toEncapsulate);
                    stringCheck = true;
                }
            } else {
                if(i == ((lengthOfString * 2) + 2)){
                    System.out.print("\n" + escapeCharacter + "-");
                }
                System.out.print("-");
            }
        }

    }
    public static void encapsulateString(String toEncapsulate, String escapeCharacter, String spaceCharacter){

        int lengthOfString = toEncapsulate.length();

        String firstPhase,secondPhase,thirdPhase;
        boolean firstPhaseCheck = false, secondPhaseCheck = false, thirdPhaseCheck = false;
        boolean stringCheck = false;
        boolean firstChar = false;
        boolean secondChar = false;
        boolean thirdChar = false;

        for (int i = 0; i < ((lengthOfString + 1) * 3); i++) {

            // First phase
            if(!firstPhaseCheck){
                if(i == 0){
                    System.out.print(escapeCharacter);
                }
                System.out.print(spaceCharacter);
                if(i == (lengthOfString + 1)){
                    firstPhaseCheck = true;
                }
            } else if(!secondPhaseCheck){
                if(i == (lengthOfString + 2)){
                    System.out.print("\n" + escapeCharacter +"|");
                } else if(i == ((lengthOfString * 2) + 1)){
                    System.out.print("|");
                    secondPhaseCheck = true;
                } else if(!stringCheck){
                    System.out.print(toEncapsulate);
                    stringCheck = true;
                }
            } else {
                if(i == ((lengthOfString * 2) + 2)){
                    System.out.print("\n" + escapeCharacter + spaceCharacter);
                }
                System.out.print(spaceCharacter);
            }
        }

    }
    public static void encapsulateString(String color, String toEncapsulate, String escapeCharacter, String spaceCharacter){

        int lengthOfString = toEncapsulate.length();
        toEncapsulate = ConsoleColorsLite.stringColor(color,toEncapsulate);

        String firstPhase,secondPhase,thirdPhase;
        boolean firstPhaseCheck = false, secondPhaseCheck = false, thirdPhaseCheck = false;
        boolean stringCheck = false;
        boolean firstChar = false;
        boolean secondChar = false;
        boolean thirdChar = false;

        for (int i = 0; i < ((lengthOfString + 1) * 3); i++) {

            // First phase
            if(!firstPhaseCheck){
                if(i == 0){
                    System.out.print(escapeCharacter);
                }
                System.out.print(spaceCharacter);
                if(i == (lengthOfString + 1)){
                    firstPhaseCheck = true;
                }
            } else if(!secondPhaseCheck){
                if(i == (lengthOfString + 2)){
                    System.out.print("\n" + escapeCharacter +"|");
                } else if(i == ((lengthOfString * 2) + 1)){
                    System.out.print("|");
                    secondPhaseCheck = true;
                } else if(!stringCheck){
                    System.out.print(toEncapsulate);
                    stringCheck = true;
                }
            } else {
                if(i == ((lengthOfString * 2) + 2)){
                    System.out.print("\n" + escapeCharacter + spaceCharacter);
                }
                System.out.print(spaceCharacter);
            }
        }

    }
}