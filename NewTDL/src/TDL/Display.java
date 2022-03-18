package TDL;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Display {

    private Scanner reader;
    private ToDoList toDoList;
    private FileHandler fileSave;
    private Print printer;
    private SimpleDateFormat sdf;

    public Display() throws IOException, ClassNotFoundException {
        reader = new Scanner(System.in);
        toDoList = new ToDoList();
        fileSave = new FileHandler();
        sdf = new SimpleDateFormat("dd-MM-yyyy");

        FileHandler fileHandler = new FileHandler();
        toDoList.setToDoList(fileHandler.loadFromFile());
        printer = new Print(toDoList.getToDoList());
    }


    public String userInput() {
        return reader.nextLine();
    }


    public void start() throws IOException, ClassNotFoundException {
        printer.printWelcome();
        response();
    }


    public void response() {
        while (true) {
            printer.printOptions();

            switch (userInput()) {
                case "1":
                    orderListOptions();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    editTask();
                    break;
                case "4":
                    quitAndSave();
                    return;
                default:
                    printer.printNotValiableOption();
            }
            printer.printPressEnterForMenu();
            userInput();
        }

    }

    public void addTask() {
        System.out.println("Tambah Task Baru \n");
        System.out.println("Nama Task: ");

        Task task = new Task();
        task.setTitle(userInput());
        System.out.println("Masukkan Nama Project: ");
        task.setProjectName(userInput());
        System.out.println("Tanggal deadline? (dd-MM-yyyy)");

        while (true) {
            try {
                task.setDueDate(sdf.parse(userInput()));
                break;
            } catch (ParseException e) {
                printer.printWrongDateFormat();
            }
        }

        Date date = new Date();
        String strDate = sdf.format(date);
        try {
            task.setCreatedDate(sdf.parse(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        toDoList.addToDo(task);
        System.out.println("Task: " + task.getTitle() + " telah sukses ditambahkan");
    }


    public void quitAndSave() {
        printer.printWhenQuitApplication();
        try {
            fileSave.saveToFile(toDoList.getToDoList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTaskNumberAndChangeItToDone() {

        System.out.println("Task mana yang akan ditandai selesai? \n ");
        printer.printOnlyIndexAndNameOfTask();
        System.out.println("\n masukkan index Task yang akan ditandai DONE (0 -> kembali ke menu)");
        Task searched;

        while (true) {
            try {
                int getTaskByNumber = Integer.parseInt(userInput());

                if (getTaskByNumber != 0) {
                    searched = toDoList.getTaskInToDo(getTaskByNumber - 1);
                    break;
                } else {
                    response();
                }

            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        searched.setStatus(Status.DONE);
        System.out.println("Task telah ditandai sebagai DONE");
    }

    public void removeTask() {
        System.out.println("silahkan pilih Task apa yang akan dihapus? \n");
        printer.printOnlyIndexAndNameOfTask();
        System.out.println("\n masukkan index Task yang akan dihapus  (0 -> kembali ke menu)");

        while (true) {
            try {
                int removeProjectByNumber = Integer.parseInt(userInput());
                if (removeProjectByNumber != 0) {
                    toDoList.remove(removeProjectByNumber - 1);
                    break;
                } else {
                    response();
                }
            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        System.out.println("Task berhasil dihapus!");

    }


    public void update() {

        printer.printUpdateOptions();
        switch (userInput()) {
            case "0":
                response();
                break;
            case "1":
                editName();
                break;
            case "2":
                editProject();
                break;
            case "3":
                editDate();
                break;
            default:
                printer.printNotValiableOption();
                update();
        }
    }

    public void editName() {
        System.out.println("Disini anda bisa mengedit nama dari salah satu Tasks:\n ");
        printer.printOnlyIndexAndNameOfTask();
        System.out.println("\n masukkan index task yang akan anda edit (0 -> kembali ke menu)");
        Task searched;

        while (true) {
            try {
                int getTitleByNumber = Integer.parseInt(userInput());

                if (getTitleByNumber != 0) {
                    searched = toDoList.getTaskInToDo(getTitleByNumber - 1);
                    break;
                } else {
                    response();
                }
            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        System.out.println("Nama Task: " + searched.getTitle() + "akan diedit menjadi?");
        String newName = userInput();
        searched.setTitle(newName);
        System.out.println("Nama telah diedit menjadi " + newName);
    }

    public void editProject() {
        System.out.println("Disini anda bisa mengedit nama dari salah satu Project dari tasks:\n ");
        printer.printIndexAndNameAndProjectOfTask();
        System.out.println("\n masukkan index Project yang akan anda edit (0 -> kembali ke menu) ");
        Task searched;

        while (true) {
            try {
                int getProjectByNumber = Integer.parseInt(userInput());

                if(getProjectByNumber != 0){
                    searched = toDoList.getTaskInToDo(getProjectByNumber - 1);
                    break;
                }
                else{
                    response();
                }

            } catch (Exception e) {
                printer.printIndexOutOfReach();
            }
        }

        System.out.println("Project " + searched.getTitle() + " akan diubah menjadi?");
        String newProject = userInput();
        searched.setProjectName(newProject);
        System.out.println("Project "+searched.getTitle() + " telah diubah menjadi: " + newProject);
    }

    public void editDate() {
        System.out.println("Disini anda bisa mengedit tenggal deadline dari task dibawah: \n ");
        printer.printIndexAndNameAndDueDateOfTask();
        System.out.println("\n  masukkan index Task yang akan anda edit tanggal deadlinenya (0 -> kembali ke menu)");

        try {
            int getProjectByNumber = Integer.parseInt(userInput());

            if(getProjectByNumber != 0){
            Task searched = toDoList.getTaskInToDo(getProjectByNumber - 1);
            System.out.println("Masukkan tanggal dealine bari dari task " + searched.getTitle() + " dibawah (dd-MM-yyyy)");

            while (true) {
                try {
                    searched.setDueDate(sdf.parse(userInput()));
                    break;
                } catch (ParseException e) {
                    printer.printWrongDateFormat();
                }
            }
            System.out.println("tanggal deadline dari "+searched.getTitle() + " telah diubah menjadi " + sdf.format(searched.getDueDate()));}
            else{
                response();
            }
        } catch (NumberFormatException e) {
            printer.printIndexOutOfReach();
            editDate();
        }
    }

    public void editTask() {
        //method with all edit subclasses
        printer.printEditTaskOptions();

        switch (userInput()) {
            case "0":
                response();
                break;
            case "1":
                removeTask();
                break;
            case "2":
                getTaskNumberAndChangeItToDone();
                break;
            case "3":
                update();
                break;
            default:
                printer.printNotValiableOption();
                editTask();
        }
    }

    public void orderListOptions() {
        printer.printSortingOptions();

        switch (userInput()) {
            case "0":
                response();
                break;
            case "1":
                printer.printEntireList();
                break;
            case "2":
                printer.printTasksByStatus(Status.PENDING);
                break;
            case "3":
                printer.printTasksByStatus(Status.DONE);
                break;
            case "4":
                printer.printTasksByProject();
                break;
            case "5":
                printer.printTaskByDueDate();
                break;
            default:
                printer.printNotValiableOption();
                orderListOptions();
        }
    }

}

