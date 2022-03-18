package TDL;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Print {

    private List<Task> listOfToDos;
    private SimpleDateFormat sdf;

    Print(List<Task> listOfToDos) {
        this.listOfToDos = listOfToDos;
        this.sdf = new SimpleDateFormat("dd-MM-yyyy");
    }

    public void printHeading() {
        System.out.print(String.format("%1$-27s", "TASK"));
        System.out.print(String.format("%1$-27s", "PROJECT"));
        System.out.print(String.format("%1$-27s", "STATUS"));
        System.out.print(String.format("%1$-27s", "DIBUAT"));
        System.out.println("DEADLINE");
        System.out.println("--------------------------------" +
                "--------------------------------------------" +
                "----------------------------------------");
    }

    public void printEntireList() {
        printHeading();

        listOfToDos.forEach(this::printBody);
    }

    public void printTasksByStatus(Status status) {
        printHeading();

        listOfToDos.stream()
                .filter(task -> task.getStatus() == status)
                .forEach(this::printBody);

    }

    public void printTasksByProject() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Pilih Project dibawah, dengan mengetik namanya: ");

        printIndexAndNameAndProjectOfTask();
        String project = reader.nextLine().toLowerCase().trim();

        printHeading();

        listOfToDos.stream()
                .filter(task -> task.getProjectName().toLowerCase().trim().equals(project))
                .forEach(this::printBody);

    }

    public void printBody(Task task) {

        System.out.print(listOfToDos.indexOf(task) + 1 + ". ");
        System.out.print(String.format("%1$-25s", task.getTitle()));
        System.out.print(String.format("%1$-25s", task.getProjectName()));
        System.out.print(String.format("%1$-28s", task.getStatus()));
        System.out.print(String.format("%1$-25s", sdf.format(task.getCreatedDate())));
        System.out.print(String.format("%1$-25s", sdf.format(task.getDueDate())));
        System.out.println("");
    }

    public void printTaskByDueDate() {
        printHeading();
        listOfToDos.sort(Comparator.comparing(Task::getDueDate));
        listOfToDos.stream().forEach(this::printBody);
    }


    public void printOnlyIndexAndNameOfTask() {
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
        }
    }

    public void printIndexAndNameAndProjectOfTask() {
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.print(String.format("%1$-25s", list.getTitle()));
            System.out.print(String.format("%1$-25s", list.getProjectName()));
            System.out.println();
        }
    }



    public void printIndexAndNameAndDueDateOfTask() {
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.print(String.format("%1$-25s", list.getTitle()));
            System.out.print(String.format("%1$-25s", sdf.format(list.getDueDate())));
            System.out.println();
        }
    }

    public void printWelcome() {

        System.out.println("\n ==Selamat Datang di APLIKASI TO DO LIST===");
        System.out.println(" Task yang masih PENDING: " + getBackAmount(Status.PENDING) + " | Task yang sudah DONE: " + getBackAmount(Status.DONE));
        System.out.println("\n Silahkan pilih Menu:");
    }


    public int getBackAmount(Status status){
        int counter = 0;

        for(Task list: listOfToDos){
            if (list.getStatus()== status){
                counter++;
            }
        }
        return counter;
    }


    public void printOptions() {
        System.out.println("---------------------------------------------");
        System.out.println(" (1) Menampilkan  List dari Task");
        System.out.println(" (2) Tambahkan Task Baru");
        System.out.println(" (3) Edit Task (Hapus, Tandai selsai, Update/Edit)");
        System.out.println(" (4) Simpan dan Keluar");
    }


    public void printNotValiableOption() {
        System.out.println("Anda memasukkan menu yang tidak tersedia. Silahkan dicoba lagi. \n");
    }


    public void printWrongDateFormat() {
        System.out.println("Format tanggal yang dimasukkan salah. Format yang benar: (dd-MM-yyyy)");
    }



    public void printWhenQuitApplication() {
        System.out.println("Keluar dari aplikasi, TO DO LIST anda telah disimpan.");
    }


    public void printIndexOutOfReach() {
        System.out.println("Task index yang anda pilih tidak tersedia. Silahkan pilih Index task yang ada:");
    }

    public void printUpdateOptions() {
        System.out.println("Tekan (1) Untuk mengedit nama Task");
        System.out.println("Tekan (2) for editing project name of a specific task");
        System.out.println("Tekan (3) for editing due date of a task");
    }

    public void printEditTaskOptions() {
        System.out.println("Tekan (1) Untuk menghapus salah satu TO DO LIST");
        System.out.println("Tekan (2) Untuk menandai telah selesai");
        System.out.println("Tekan (3) Untuk memperbaharui/mengedit TO DO LIST");
    }

    public void printSortingOptions() {
        System.out.println("Disini Anda bisa menampilkan TO DO LIST yang diurutkan sesuai dengan:");
        System.out.println("(1): Tampilkan semua TO DO LIST");
        System.out.println("(2): Tampilkan semua TO DO LIST yang masih status PENDING");
        System.out.println("(3): Tampilkan semua TO DO LIST yang sudah status DONE");
        System.out.println("(4): Tampilkan semua TO DO LIST sesuai dengan PROJECT:");
        System.out.println("(5): Tampilkan semua TO DO LIST sesuai DEADLIINE");
    }


    public void printPressEnterForMenu() {
        System.out.println("\n TEKAN ENTER untuk kembali ke menu");
    }
}
