import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class MultithreadingWriteToFile {

    public static void write_file_async(LinkedList<Shape> allFigures) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        File destination = new File("figures_list.txt");
        for(int i = 0; i < allFigures.size(); i++){
            Writer w1 = new Writer(destination,allFigures.get(i).toString());
            Thread t = new Thread(w1);
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }

    }

}

class Writer implements Runnable{
    File destination;
    String content;
    public Writer(File destination, String content) {
        this.content = content;
        this.destination = destination;
    }
    @Override
    public void run() {
        writeToFile(destination,content);
    }

    private static void writeToFile(File file,String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(content + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}