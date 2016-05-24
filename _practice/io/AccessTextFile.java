import java.io.BufferedReader;   
import java.io.FileInputStream;   
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.io.InputStream;   
import java.io.InputStreamReader;   
import java.io.OutputStream;   
import java.io.OutputStreamWriter;   
import java.io.PrintStream;   
import java.io.PrintWriter;   
  
public final class AccessTextFile {   
    public void readToBuffer(StringBuffer buffer, InputStream inputStream) throws IOException {   
        String line;   
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));   
        line = reader.readLine();        
        while (line != null) {          
            buffer.append(line);       
            buffer.append("\n");        
            line = reader.readLine();    
        }   
    }   
  
    public void writeFromBuffer(StringBuffer buffer, OutputStream outputStream) {   
        PrintStream printStream = new PrintStream(outputStream);      
        printStream.print(buffer.toString());   
    }   
  
    public void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {   
        String line;   
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); 
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));   
        line = reader.readLine();   
        while (line != null) {   
            writer.println(line);   
            line = reader.readLine();   
        }   
        writer.flush();     
    }   
  
    public void copyTextFile(String inFilename, String outFilename)   
        throws IOException {    
        InputStream inputStream = new FileInputStream(inFilename);   
        OutputStream outputStream = new FileOutputStream(outFilename);   
        copyStream(inputStream, outputStream);      
        inputStream.close();  
        outputStream.close();  
    }   
  
    public static void main(String[] args) throws IOException {   
        int sw = 1;     
        AccessTextFile test = new AccessTextFile();   
           
        switch (sw) {   
        case 1:  
        {   
            InputStream inputStream = new FileInputStream("E:\\test.txt");   
            StringBuffer buffer = new StringBuffer();   
            test.readToBuffer(buffer, inputStream);   
            System.out.println(buffer);     
            inputStream.close();   
            break;   
        }   
        case 2: 
        {   
            StringBuffer buffer = new StringBuffer("Only a test\n");   
            test.writeFromBuffer(buffer, System.out);   
            break;   
        }   
        case 3:  
        {   
            test.copyTextFile("E:\\test.txt", "E:\\r.txt");   
        }   
            break;   
        }   
    }   
  
}  

