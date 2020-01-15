package drive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

public class Drive {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //C:\\Users\\isgaier\\Desktop\\code-challenge\\partition_sort\\input.txt
        //C:\\Users\\isgaier\\Desktop\\code-challenge\\partition_sort\\output.txt
        Scanner fi= new Scanner(System.in);
        System.out.println("Please enter the input file path");
        String pathFileReader= fi.next();
        // check if inputfile can read, exists and is a file
        File fileReader = new File(pathFileReader);// create new file
        if(fileReader.isFile()&& fileReader.canRead()&& fileReader.exists()){
            
            FileInputStream fis=new FileInputStream(fileReader);
            Scanner sc=new Scanner(fis);
            
            System.out.println("Please enter the path of output file ");
            String pathFileWriter= fi.next();
            
            File fileWriter = new File(pathFileWriter);
            // check if outputfile can write, exists and is a file
            if(fileWriter.canWrite() && fileWriter.exists() && fileWriter.isFile()){                
                PrintWriter writer = new PrintWriter(fileWriter, "UTF-8");                 
                while(sc.hasNextLine()){
                    // create new object initialize with input text and file weiter  
                    partionSort obj = new partionSort(sc.nextLine(), writer);
                    obj.sortInput();// call sortInput function
                }
                writer.close();//close output file
            } else {
                System.out.println("sorry, this not file");
            }                                                
        } else {
            System.out.println("sorry, this not file");
        }                            
    }
}

class partionSort {
    private String input;
    private PrintWriter printWriter;
    // constructor 
    partionSort( String text , PrintWriter printWriter ){
        this.input =text;
        this.printWriter = printWriter;
    }
    public void sortInput() {
        String partion = null;        
        int start=0,finish=0;// to control fo partion string to sort input from to .
        for(int i=0;i< input.length();i++){
            char ch =input.charAt(i);// Give char with the index i         
            char nextCharacter = 0;// This variable to give nextCharacter of ch
            finish++;//increment finish counter to ctach partion input to sort it
            if(i+1==input.length()){// check if the char last one 
                 partion= input.substring(start,finish);//Give the string between start and finish index           
                char tempArray[] = partion.toCharArray();// convert the string to array in order to sort it
                Arrays.sort(tempArray);// sort array
                printWriter.println(tempArray);// print on the output file 
            }else {
                
                 nextCharacter=input.charAt(i+1);//Give the next char with the index i+1
                 // check if current char and next char is a digit OR a not digit  
                 if(((ch >= '0' && ch <= '9')&&(nextCharacter >= '0' && nextCharacter <= '9')) // all digits
                         ||(ch < '0' || ch > '9' ) &&(nextCharacter < '0' || nextCharacter > '9' ) ){// all ascii
                     continue;// keep going to the next char
                 }else{
                     // If the current char is digit and the next char is a not digit 
                     // Or if the current char is not digit and the next char is a digit
                     // Then we have cross point between digit and not digit                      
                   partion= input.substring(start,finish);// So we will Give the string between start at this cross point
                   char tempArray[] = partion.toCharArray();// convert the string to array in order to sort it
                   Arrays.sort(tempArray);// Sort array
                   printWriter.print(tempArray); // // print on the output file 
                   start=finish;// the next string will start from finsh of previous string
                 }           
            }
        }
    }
}