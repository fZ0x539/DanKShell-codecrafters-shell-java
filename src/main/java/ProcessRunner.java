import Utility.Redirection.RedirectionType;
import Utility.ShellContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import Exception.InvalidRedirectionPathException;

public class ProcessRunner {


    public static boolean findAndRunExecutable(String[] tokenizedParts, ShellContext shellContext) {

        String execPath = shellContext.resolveExecutablePath(tokenizedParts[0]);
        if (execPath != null) {

            String[] partsBeforeOutput = null;
            String outputPath = null;
            RedirectionType redirectionType = null;
            //* ex: ls   /tmp/baz   >   /tmp/foo/baz.md
            //*     [0]     [1]    [2]      [3]
            //*  tokenizedParts.length = 4. tP.length - 1 = 3
            //*  i = 3
            //*  First  run: tokenizedParts[3] = /tmp/foo/baz.md
            //*  Second run: tokenizedParts[2] = >
            //*  RedirectionType FOUND: STDOUT
            //*
            for (int i = tokenizedParts.length - 1; i >= 1; i--) {
                if (RedirectionType.fromOperator(tokenizedParts[i]) == RedirectionType.STDOUT) {
                    //if Redirect Operator is the last element, throw exception.
                    if (i == tokenizedParts.length - 1)
                        throw new InvalidRedirectionPathException();
                    redirectionType = RedirectionType.fromOperator(tokenizedParts[i]);
                    outputPath = tokenizedParts[i + 1];
                    partsBeforeOutput = Arrays.copyOfRange(tokenizedParts, 0, i);
                    break;
                }
            }
            //! DEBUG
//            System.out.println("partsBeforeOutput: " + Arrays.toString(partsBeforeOutput));
//            System.out.println("outputPath: " + outputPath);
//            System.out.println("RedirectionType: " + redirectionType.toString());


            ProcessBuilder pb;
            if (redirectionType != null && outputPath != null) {
                pb = new ProcessBuilder(partsBeforeOutput);
                try {
                    Path redirectPath = Paths.get(outputPath);
                    pb.redirectOutput(redirectPath.toFile());
                } catch (InvalidPathException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                pb = new ProcessBuilder(tokenizedParts);
            }

            pb.directory(new File(shellContext.getCurrentDirectory().toUri()));
            try {
                Process process = pb.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

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
}
