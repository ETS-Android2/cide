package Application.Services.Console.Menu;

/*

    Project     Programming21
    Package     Application.Services.Console.Menu    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-15

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

public class StyledMenu extends DefaultMenu {

    private String[] headers;
    private int[] sizes;

    public StyledMenu(String[] o, String[] h, int[] s) {
        super(o);
        this.headers = h;
        this.sizes = s;
    }

    @Override
    public void paint() {

        int count = 0;
        System.out.print("\n\n");

        // HEADERS
        for(String header : this.headers){
            System.out.printf("%-" + sizes[count] + "s",header);
            count++;
        }

        // RESET
        count = 0;

        // OPTIONS
        for(String option : this.options){
            String text = "\n";
            for(Integer size : this.sizes){
                text += "%-" + String.format("%d",size) + "s ";
            }
            System.out.printf(text,option);
        }

    }
}
