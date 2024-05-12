import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        GameProgress gameProgress1 = new GameProgress(1, 2, 3, 4.5);
        GameProgress gameProgress2 = new GameProgress(11, 23, 23, 14.5);
        GameProgress gameProgress3 = new GameProgress(221, 244, 113, 4.55);
        // Далее путь для сохранения:
        String pathSave = "D:\\Мои файлы\\Нетология\\JavaCore\\Stream API_работа с файлами и сборка проектов\\Потоки ввода вывода_Работа с файлами_Сериализация\\Домашнее задание\\Установка\\Games\\savegames\\";
        String pathFiles1 = pathSave + "savegames_1.dat";
        String pathFiles2 = pathSave + "savegames_2.dat";
        String pathFiles3 = pathSave + "savegames_3.dat";
        saveGame(pathFiles1, gameProgress1);
        saveGame(pathFiles2, gameProgress2);
        saveGame(pathFiles3, gameProgress3);
        List<String> dates = Arrays.asList(pathFiles1, pathFiles2, pathFiles3);
        zipFiles((pathSave+"out_zip.zip"), dates);


//        saveGame(pathFiles, gameProgress1);
//        saveGame(pathFiles, gameProgress2);

    }
    public static void saveGame(String pathFile, GameProgress gameProgress) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(gameProgress);
        fos.close();
        oos.close();
    }
    public static void zipFiles(String zipData, List<String> dates) {
        // zipData - полный путь к файлу архива, типа /Users/..../zip.zip
        // dates - полные пути к файлам, которые надо сохранить, типа /Users/.../save3.dat
        try {
            ZipOutputStream zipops = new ZipOutputStream(new FileOutputStream(zipData));
            int c = 0;
            for (String data:dates
                 ) {
                c++;
                File fileInput = new File (data);
                System.out.println(fileInput.exists());
                FileInputStream fileInputStream = new FileInputStream(data);
                ZipEntry zipEntry = new ZipEntry("savegames_" + c +  ".dat");
                zipops.putNextEntry(zipEntry);
                byte [] buffered = new byte[fileInputStream.available()];
                System.out.println(fileInputStream.available());
                fileInputStream.read(buffered);
                zipops.write(buffered);
                zipops.closeEntry();
                fileInputStream.close();
                System.out.println(fileInput.delete());


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
