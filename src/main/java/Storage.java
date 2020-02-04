import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveToFile(ArrayList<Task> list) throws FileNotFoundException{
        StringBuilder sb = new StringBuilder();
        for(Task t : list) {
            sb.append(t.saveToText() + "\n");
        }
        try (PrintStream out = new PrintStream(new FileOutputStream("data/saved.txt"))) {
            out.print(sb.toString());
            System.out.println("Your tasks have been saved to the hard disk");
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public List<List<String>> loadSavedData() throws IOException {
        List<List<String>> taskList = new ArrayList<>();
        File tmpDir = new File(filePath); //"data/saved.txt");
        Scanner sc = new Scanner(tmpDir);
        while(sc.hasNext()) {
            List<String> xs = new ArrayList<>();
            String input = sc.nextLine();
            String[] inputs = input.split(" - ");
            xs = Arrays.asList(inputs);
            taskList.add(xs);
        }
        return taskList;
    }
}
