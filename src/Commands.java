import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Commands {
    public static String cd(String path) {

        File file = new File(path);
        File fileParent = new File(file.getParent());

        if (fileParent.exists()) {
            path = fileParent.getPath();
        }
        else {
            System.out.println("ERROR: У данного каталога нет родителя");
        }
        System.out.println("---------------------------------------------------------------------");
        return path;
    }

    public static void dirInfo(String path) {
        File dir = new File(path);
        System.out.println("\nИнформация о каталоге " + dir.getName() + ":\n");
        if (dir.canRead())
            System.out.println("\t-Доступен для чтения");
        if (dir.canWrite())
            System.out.println("\t-Доступен для записи");
        System.out.println();

        File[] item = new File(path).listFiles();

        for (int i = 0; i < item.length; ++i) {

            System.out.println("\t" + i + ". " + item[i].getName());
        }
        System.out.println("---------------------------------------------------------------------");
    }

    // выбор элемента, содержащегося в каталоге, для перехода
    public static String dirElements(String path) {
        int numInput;
        Scanner scanner = new Scanner(System.in);

        File dir = new File(path);
        System.out.println("\nКаталог " + dir.getName() + ":\n");

        // получаем все вложенные объекты в каталоге
        File[] item = new File(path).listFiles();

        if (item.length != 0) {

            for (int i = 0; i < item.length; ++i) {
                System.out.println("\t" + i + ". " + item[i].getName());
            }

            System.out.print("\nВыберите каталог/файл: ");
            numInput = scanner.nextInt();
            path = item[numInput].getPath();
        } else {
            System.out.println("\nЭтот каталог пустой!");
        }
        System.out.println("---------------------------------------------------------------------");

        return path;
    }


    public static String createDir(String path) {
        boolean isCreated = false;
        String fileName;

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nВведите имя для нового каталога: ");
        fileName = scanner.nextLine();
        File file = new File(path + "\\" + fileName);

        isCreated = file.mkdir();

        if (isCreated == true) {
            System.out.println("Каталог " + file.getName() + " создан!");
        }
        else {
            System.out.println("ERROR: Ошибка при создании каталога " + file.getName() + ", возможно каталог с таким названием уже существует");
        }
        System.out.println("---------------------------------------------------------------------");
        return path;
    }

    public static String createFile(String path) {
        boolean isCreated = false;
        String fileName;

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nВведите имя нового файла: ");
        fileName = scanner.nextLine();
        File file = new File(path + "\\" + fileName);

        try {
            isCreated = file.createNewFile();
            if (isCreated == true) {
                System.out.println("\nФайл успешно создан");
                path = file.getPath();
                WriteText(path);
            }
            else
                System.out.println("ERROR: Ошибка при создании файла " + file.getName() + ", возможно файл с таким названием уже существует");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------");
        return path;
    }

    public static void WriteText(String path) {

        Scanner scanner = new Scanner(System.in);

        try(FileWriter writer = new FileWriter(path, false)) {
            System.out.println("\nВведите данные в созданный файл: ");
            String text = scanner.nextLine();
            writer.write(text);
            writer.flush();
            System.out.println("\n Данные внесены");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    public static String delete(String path) {
        Scanner scanner = new Scanner(System.in);

        File file = new File(path);
        String input;


        System.out.print("\nВы уверены, что вы хотите удалить каталог " + file.getName() + " (Yes/No) ");
        input = scanner.nextLine();

        if(input.equals("Yes")) {
            path = file.getParent();
            if (file.delete())
            {
                System.out.println("\nКаталог/файл " + file.getName() + " успешно удалён!");
            }
            else
                System.out.println("\nКаталог/файл " + file.getName() + " не получилось удалить!");
        }
        System.out.println("---------------------------------------------------------------------");
        return path;
    }

    public static String rename(String path) {
        String fileName;
        Scanner scanner = new Scanner(System.in);

        File file = new File(path);
        System.out.print("\nВведите новое имя для " + file.getName() + ": ");
        fileName = scanner.nextLine();

        File newFile = new File(file.getParent() + "\\" + fileName);

        if (file.renameTo(newFile))
        {
            System.out.println("Файл/каталог успешно переименован!");
            path = newFile.getPath();
        }
        else
            System.out.println("ERROR: не получилось переименовать файл/каталог!");
        System.out.println("---------------------------------------------------------------------");
        return path;
    }
    public static void fileInfo(String path) {
        File file = new File(path);
        System.out.println("\nИнформация о каталоге " + file.getName() + ":\n");

        if (file.canRead())
            System.out.println("\tДоступен для чтения");
        if (file.canWrite())
            System.out.println("\tДоступен для записи");

        System.out.println("\tРазмер: " + file.length() + " байт");
        System.out.println("---------------------------------------------------------------------");
    }
    public static void openFile(String path) {

        ProcessBuilder open = new ProcessBuilder();
        System.out.println("notepad.exe");
        open.command("notepad.exe", path);

        try {
            open.start();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------------------------------------------------------");
    }
}
