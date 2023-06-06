package com.experiment.translate.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

public class FileUtil {
    public static final String TEMP_FILE = "file/temp";
    public static final String VOICE_FILE = "file/voice";
    public static final String IMAGE_FILE = "file/image";
    public static final String FILE_DESCRIPTOR = "file://";

    public static String saveFile(byte[] data,String save_mode){
        // 生成一个唯一的文件名
        String fileName = generateFileName();

        // 获取保存文件的目录路径
        String saveDirectory = save_mode;

        // 将文件数据写入本地文件
        Path filePath = Path.of(saveDirectory, fileName);
        try {
            Files.write(filePath, data, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 返回文件的路径
        return filePath.toAbsolutePath().toString();
    }

    private static String generateFileName() {
        // 使用UUID生成唯一的文件名
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID + ".mp3";
    }

    public static void deleteFilesExcept(String directoryPath, List<String> fileNamesToKeep) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!fileNamesToKeep.contains(file.getName())) {
                        file.delete();
                    }
                }
            }
        }
    }
}
