import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.TreeSet;


public class MultithreadingWriteToFile {

    public static void write_file_async(TreeSet<Shape> allFigures) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        File destination = new File("figures_list.txt");
        for(Shape s: allFigures){
            Writer w1 = new Writer(destination,s.toString());
            Thread t = new Thread(w1);
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }

    }

    public static void write_json_async(TreeSet<Shape> allFigures) {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        File destination = new File("figures_list.json");
        JSONArray json = new JSONArray();
        for(Shape s : allFigures){
            JSONObject obj = new JSONObject();
            obj.put("type", s.getClass().getSimpleName());
            obj.put("area", s.getArea());
            obj.put("perimeter", s.getPerimeter());
            json.put(obj);
        }
        Writer w1 = new Writer(destination,json.toString());
        Thread t = new Thread(w1);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();


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