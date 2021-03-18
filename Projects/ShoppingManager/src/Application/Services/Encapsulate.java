package Application.Services;

/*

    Project     Programming21
    Package     Utilitats    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-09

    DESCRIPTION
    
*/

import Application.Services.Console.Components.Menu.StringGenerator;

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
                    System.out.print("\n" + escapeCharacter);
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
                    System.out.print("\n" + escapeCharacter);
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
    public static String inlineEncapsulate(String s, int lenght, int spacing){
        double firstStage = Math.ceil(((double)(lenght - s.length()) / 2)) - Math.ceil((double)spacing / 2);
        double secondStage = Math.floor(((double)(lenght - s.length()) / 2)) - Math.floor((double)spacing / 2);
        double firstSpacing = Math.ceil((double)spacing / 2);
        double secondSpacing = Math.floor((double)spacing / 2);
        return StringGenerator.generateStringByChar("-",firstStage)
                + StringGenerator.generateStringByChar(" ",firstSpacing)
                + s
                + StringGenerator.generateStringByChar(" ",secondSpacing)
                + StringGenerator.generateStringByChar("-",secondStage);
    }
}