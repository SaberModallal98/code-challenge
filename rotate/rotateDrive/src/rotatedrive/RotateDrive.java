package rotatedrive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RotateDrive {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //C:\\Users\\isgaier\\Desktop\\code-challenge\\rotate\\input.txt
        //C:\\Users\\isgaier\\Desktop\\code-challenge\\rotate\\output.txt
        
        Scanner fi= new Scanner(System.in);
        System.out.println("Please enter the input file path");
        String pathFileReader= fi.next();
        
        System.out.println("Please enter the path of output file ");
        String pathFileWriter= fi.next();                
        
        File fileReader = new File(pathFileReader);// create new file   
        // check if inputfile can read, exists and is a file
        if(fileReader.isFile()&& fileReader.canRead()&& fileReader.exists()){            
            FileInputStream fis=new FileInputStream(fileReader);
            Scanner sc=new Scanner(fis);
            
            ArrayList<String> arrayInput = new ArrayList<String>();
            int max=0;//initial value 
            int count=0;
            while(sc.hasNextLine()){
                arrayInput.add(sc.nextLine()); //add each line in array list 
                if(arrayInput.get(count).length()>max)// find the max length of all lines
                    max=arrayInput.get(count).length();
                count++;
            }
            
            File fileWriter = new File(pathFileWriter);
            if(fileWriter.canWrite() && fileWriter.exists() && fileWriter.isFile()){
                PrintWriter writer = new PrintWriter(fileWriter, "UTF-8");
                // Create outputArray with max row that founded in the maximum line lenght
                //arrayInput.size() is the number of rows in the input array            
                char arrayOutput[][]= new char[max][arrayInput.size()];        
                Rotate rotate = new Rotate(arrayInput,arrayOutput,writer);// create new object.
                rotate.Mirror();// mirror the input.
                rotate.printOutputFile();// print the output of mirror in output file.                
            }else{
                System.out.println("sorry, this not file");                
            }                                                
        }else {
            System.out.println("sorry, this not file");
        }
    }
}

class Rotate {
    private char[][] arrayOutput;
    private ArrayList<String> arrayInput; 
    private PrintWriter printWriter;
    // constructor 
    Rotate( ArrayList<String> inputArray,char[][] output,PrintWriter writer ){
            this.arrayInput = inputArray;
            this.arrayOutput =output;
            this.printWriter=writer;
        }
    public void Mirror() {            
        for(int i=0; i<arrayInput.size();i++){
            for(int j=0; j< arrayInput.get(i).length();j++){
                // add each coulumn of arrayInput in the rows of arrayOutput
                arrayOutput[j][i]=arrayInput.get(i).charAt(j);
            }
        }
    }
    public void printOutputFile(){
        for(int i=0; i<arrayOutput.length;i++){
            printWriter.println(arrayOutput[i]);// print each row to the outputFile
        }
        printWriter.close();
    }
}