import Utility.Redirection.RedirectionType;
import Utility.ShellContext;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Utility.Redirection.RedirectionHandler.redirectOutput;

public class ProcessRunner {


    public static boolean findAndRunExecutable(String[] tokenizedParts, ShellContext shellContext) {

        String execPath = shellContext.resolveExecutablePath(tokenizedParts[0]);
        if (execPath != null) {
            var redirectionResult = redirectOutput(tokenizedParts);
            ProcessBuilder pb;

            // If redirected
            if (redirectionResult != null) {
                pb = new ProcessBuilder(redirectionResult.getPartsBeforeOutput());
                try {
                    Path redirectPath = Paths.get(redirectionResult.getOutput());
                    if (redirectionResult.getRedirectionType() == RedirectionType.STDOUT) {
                        pb.redirectOutput(redirectPath.toFile());
                    } else if (redirectionResult.getRedirectionType() == RedirectionType.STDERR) {
                        pb.redirectError(redirectPath.toFile());
                    }
                } catch (InvalidPathException e) {
                    System.out.println(e.getMessage());
                }
            }
            // Else run normally
            else {
                pb = new ProcessBuilder(tokenizedParts);
            }

            pb.directory(new File(shellContext.getCurrentDirectory().toUri()));
            try {
                Process process = pb.start();

                Thread outThread = pipeStream(process.getInputStream(), System.out);
                Thread errThread = pipeStream(process.getErrorStream(), System.err);

                process.waitFor();

                // Wait for both threads to finish printing
                outThread.join();
                errThread.join();

                return true;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }
        return false;

    }


    private static Thread pipeStream(InputStream in, PrintStream out) {
        Thread t = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    out.println(line);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                ;
            }
        });
        t.start();
        return t;
    }


}
