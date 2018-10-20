package com.tuc.icesaver;

import java.io.*;

import android.os.*;
import android.widget.*;
import android.content.*;

/*
 Данный класс позволяет легко и быстро сохранять данные как в отдельных файлах, так и в одном, используя структуру "ключ:значение"
 Методы:
 public static String readKey(String key) — возвращает значение по ключу key, если таковое существует. В противном случае вернет FileUtil.NO_DATA
 public static void writeKey(String key, String value, Context ctx) — если ключу key уже задано какое-либо значение, то изменит его на value. Иначе создаст новый ключ и задаст ему значение. Использует контекст ctx для передачи методу writrFile, который, в свою очередь, открывает с помощью него поток для записи сюданных в файл
 public static String readFile(String fileName) — возвращает содержимое файла с именем fileName (который находится в приватной папке приложения) в строке, если такой существует. Иначе вернет FileUtil.NO_DATA
 public static void writeFile(String fileName, String data) — если файл с именем fileName существует, то перезапишет его сожержимое на строку data. Если нет, то создаст и также запишет в него строку data.
 Поля:
 public static File path — не трогать. Нужно для хранения пути до директории приложения
 public static final String NO_DATA — используется как знак того, что файла, который прочитать, не существует
 */
public class FileUtil {
    public static File path;
    public static final String NO_DATA = "N0 D4T4";
    static String keysFileName = "data";
    static boolean keysFileExists = false;

    public static boolean isFirstRun() {
        File file = new File(path, "/" + keysFileName);
        boolean firstRun = !file.exists();
        try {
            file.createNewFile();
        } catch (Exception e) {
            Debug.e(e);
        }
        return firstRun;
    }

    public static void writeKey(String key, String value, Context ctx) {
        if (!keysFileExists) {
            File file = new File(path, "/" + keysFileName);
            if (!file.exists())
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    Debug.e(e);
                }
            keysFileExists = true;
        }
        String keysFileData = readFile(keysFileName);
        if (keysFileData != NO_DATA) {
            String[] lines = keysFileData.split(";");
            String[] keys = new String[lines.length];
            String[] values = new String[lines.length];
            boolean keyExists = false;
            //Debug.d(null, "lines length " + lines.length);
            for (int k = 0; k < lines.length; k++) {
                String[] currentLine = lines[k].split(":");
                if (currentLine.length == 2) {
                    values[k] = currentLine[1];
                }
                keys[k] = currentLine[0];

                if (keys[k].equals(key)) {
                    values[k] = value;
                    keyExists = true;
                }
            }
            String newData = "";

            if (!keyExists) {
                newData = key + ":" + value + ";\n";
            }
            for (int k = 0; k < lines.length; k++) {
                if (keys[k] != null && !keys[k].equals("")) {
                    newData += (keys[k] + ":" + values[k] + ";\n");
                }
            }

            writeFile(keysFileName, newData, ctx);
        } else {
            writeFile(keysFileName, key + ":" + value + ";\n", ctx);
        }
    }

    public static String readKey(String key) {
        String keysFileData = readFile(keysFileName);
        if (keysFileData != NO_DATA) {
            String[] lines = keysFileData.split(";");
            String[] keys = new String[lines.length];
            String[] values = new String[lines.length];
            for (int k = 0; k < lines.length; k++) {
                String[] currentLine = lines[k].split(":");
                keys[k] = currentLine[0];
                values[k] = currentLine[1];
                if (keys[k].equals(key)) {
                    return values[k];
                }
            }
        }
        return NO_DATA;
    }

    public static void writeFile(String fileName, String data, Context ctx) {
        try {
            File file = new File(path, "/" + fileName);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = ctx.openFileOutput(fileName, ctx.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Debug.e(e);
        }
    }

    public static String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();

        try {
            File file = new File(path, "/" + fileName);
            FileInputStream inputStream = new FileInputStream(file);
            String line;


            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            inputStream.close();
            return builder.toString();
        } catch (Exception e) {
            Debug.e(e);
        }
        return NO_DATA;
    }
}