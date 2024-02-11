import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ChildThread
 */
public class ChildThread extends Thread{
    String word;
    public ChildThread(String s){word=s;}
    @Override
    public void run(){
        for (int i=0;i<10;i++){
            System.out.println(word);

        }

    }
    public static void main(String[] args) throws IOException {
        Thread t1Thread= new ChildThread("hello");
        Thread t2Thread= new ChildThread("world");
        File file=new File("a.txt");
        file.createNewFile();
        PrintWriter a= new PrintWriter(file);
        t1Thread.start();
        t2Thread.start();



    }
    
}