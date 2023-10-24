import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String path = GetCurrentDir();

        do {
            File dir = new File(path);

            // если объект представляет каталог
            if (dir.isDirectory()) {
                System.out.println("\n" + dir.getPath());
                path = dirMenu(path);
            }
            // если нет, переходим к работе с файлом
            else {
                System.out.println(dir.getPath());
                path = fileMenu(path);
            }
        } while (true);
    }

    public static String GetCurrentDir() {

        File dir = new File("");
        String path = dir.getAbsolutePath();

        return path;
    }

    public static String dirMenu(String path) {

        String menuInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n[cd..] - Перейти на уровень вверх");
        System.out.println("[dirInfo] - Вывести содержимое каталога");
        System.out.println("[dirElements] - Вывести содержимое каталога и перейти к элементу");
        System.out.println("[createDir] - Создать каталог");
        System.out.println("[renameDir] - Переименовать каталог");
        System.out.println("[deleteDir] - Удалить каталог");
        System.out.println("[createFile] - Создать файл");
        System.out.print("\nВведите команду: ");

        menuInput = scanner.nextLine();

        switch (menuInput) {
            case "dirInfo" -> Commands.dirInfo(path);
            case "dirElements" -> path = Commands.dirElements(path);
            case "cd.." -> path = Commands.cd(path);
            case "renameDir" -> path = Commands.rename(path);
            case "deleteDir" -> path = Commands.delete(path);
            case "createDir" -> path = Commands.createDir(path);
            case "createFile" -> path = Commands.createFile(path);
        }

        return path;
    }
    public static String fileMenu(String path) {

        String menuInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n[cd..] - Перейти на уровень вверх");
        System.out.println("[fileInfo] - Вывести информацию о файле");
        System.out.println("[openFile] - Открыть файл в блокноте для редактирования");
        System.out.println("[renameFile] - Переименовать файл");
        System.out.println("[deleteFile] - Удалить файл");
        System.out.print("\nВведите команду: ");

        menuInput = scanner.nextLine();

        switch (menuInput) {
            case "fileInfo" -> Commands.fileInfo(path);
            case "openFile" -> Commands.openFile(path);
            case "cd.." -> path = Commands.cd(path);
            case "renameFile" -> path = Commands.rename(path);
            case "deleteFile" -> path = Commands.delete(path);

        }
        return path;
    }

}
