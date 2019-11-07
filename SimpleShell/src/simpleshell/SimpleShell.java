/*
 * School assignment
 * Cat.-ID: 22 (the last one, M. W.)
 */
package simpleshell;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Simple shell demo.
 * 
 * @author administrator (Last Cat.-ID)
 */
public class SimpleShell {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("SimpleShell: 'exit' to quit session.\n");
        
        while(true) {
            System.out.print("> ");
            String commands = input.nextLine();
            if(!commands.isEmpty()) {
                for(String cmd : getCommandArray(commands)) {
                    if(cmd.equals("exit")) {
                        System.exit(0);
                    } else {
                        runCommand(cmd);
                    }
                }
            }
        }
    }
    
    /**
     * Runs the command specified in the parameter.
     * 
     * @param command
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void runCommand(String command) throws IOException, InterruptedException {
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            InputStream s = p.getInputStream();
            int b = s.read();
            while (b != -1) {
                System.out.print((char)b);
                b = s.read();
            }
        } catch (Exception ex) {
            System.err.println("An error occurred while executing the command.");
        }
    }
    
    /**
     * Splits up the passed parameter using ' & ' as a delimiter.
     * 
     * @param command
     * @return 
     */
    public static String[] getCommandArray(String command) {
        String[] commands = command.split(" & ");
        return commands;
    }
}
