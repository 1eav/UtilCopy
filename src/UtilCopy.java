import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class UtilCopy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Request the paths to the source and destination folders from the user
        System.out.println("Enter the path to the source folder:");
        String sourcePath = scanner.nextLine();
        System.out.println("Enter the path to the target folder:");
        String targetPath = scanner.nextLine();

        try {
            // Call the copyFolder() method to copy the folder
            copyFolder(sourcePath, targetPath);
            System.out.println("Folder copied successfully.");
        } catch (IOException e) {
            System.out.println("Error copying folder:");
            e.printStackTrace();
        }
    }

    /**
     * Copies a folder from the source path to the destination path.
     *
     * @param sourcePath path to the source folder
     * @param targetPath path to the target folder
     * @throws IOException when there are errors reading files or folders
     */

    public static void copyFolder(String sourcePath, String targetPath) throws IOException {

        // Create File objects for the source and destination folders
        File sourceFolder = new File(sourcePath);
        File targetFolder = new File(targetPath);

        // Check if the source and destination folders exist and are directories
        if (!sourceFolder.exists() || !targetFolder.exists() ||
                !sourceFolder.isDirectory() || !targetFolder.isDirectory()) {
            throw new IllegalArgumentException("Invalid folder paths");
        }

        // Create the target folder if it doesn't exist
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        // Get a list of files and subfolders in the source folder
        File[] files = sourceFolder.listFiles();

        // Recursively copy files and subfolders
        for (File file : files) {
            String fileName = file.getName();
            File target = new File(targetFolder, fileName);
            if (file.isDirectory()) {
                // Recursively copy the subfolder
                copyFolder(file.getAbsolutePath(), target.getAbsolutePath());
            } else {
                // Copy the file
                Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}