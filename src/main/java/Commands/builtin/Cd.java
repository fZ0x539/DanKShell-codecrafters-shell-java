package Commands.builtin;

import Commands.Type;
import Utility.ShellContext;
import core.ContextAwareCommandSuper;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Cd extends ContextAwareCommandSuper {

    public Cd(ShellContext shellContext) {
        super("cd", Type.BUILTIN, shellContext);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        if (inputArgs.length == 1) {
//            //Remove initial / if necessary, split each directory into it's own string
            String inputPath = inputArgs[0].trim();
            if(inputPath.startsWith("/"))
                inputPath = inputPath.substring(1);
            String[] relativePaths = inputPath.split("/");

            try{
                Path absolutePath = Paths.get(inputArgs[0]);
                getShellContext().setCurrentDirectory(absolutePath);

                //if it has either one: cd documents
                //elseif two or more directories eg: cd /user/documents
//                if(relativePaths.length == 1){
//
//                } else if(relativePaths.length >= 2){
//
//                }
            } catch (InvalidPathException e){
                System.err.println(e.getMessage());
            }

//            System.out.println(Arrays.toString(relativePaths));
        } else {
            System.out.println("cd: string not in pwd: " + inputArgs[0]);
        }
    }
}
