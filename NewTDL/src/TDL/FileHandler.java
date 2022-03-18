package TDL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class FileHandler {

    public List<Task> loadFromFile() throws IOException, ClassNotFoundException {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("ToDo.txt"));
            List<Task> loadedTasks = (List<Task>) in.readObject();
            return loadedTasks;
        }
        catch (EOFException e){
            System.out.println("File masih kosong. Tambahkan minimal satu Task!");
        }
        catch (FileNotFoundException e){
            List<Task> list = new ArrayList<Task>();
            saveToFile(list);

        }

        return null;

    }

    public void saveToFile(List<Task> tasksToSave) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ToDo.txt"));
        out.writeObject(tasksToSave);

        System.out.println("Task tersimpan:");
        System.out.println();
        tasksToSave.forEach(task -> System.out.println("* " + task.getTitle())
        );
    }

}
