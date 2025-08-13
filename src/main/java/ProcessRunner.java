import Utility.ShellContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessRunner {


    public static boolean findAndRunExecutable(String[] parts, ShellContext shellContext) {

        String execPath = shellContext.resolveExecutablePath(parts[0]);
        if (execPath != null) {
            ProcessBuilder pb = new ProcessBuilder(parts);
            try {
                Process process = pb.start();

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                process.waitFor();
                return true;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }


//    public static String getPath(String execName) {
//        String pathEnv = System.getenv("PATH");
//        String[] dirArray = pathEnv.split(":");
//        for (String directoryPath : dirArray) {
//            Path candidatePath = Paths.get(directoryPath, execName);
//
//            if (Files.exists(candidatePath) && Files.isExecutable(candidatePath)) {
//                return candidatePath.toString();
//            }
//        }
//        return null;
//    }

}
