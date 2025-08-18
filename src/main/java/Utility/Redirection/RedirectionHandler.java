package Utility.Redirection;

import java.util.Arrays;

import Exception.InvalidRedirectionPathException;

public class RedirectionHandler {

    public static RedirectionContext redirectOutput(String[] tokenizedParts) {
        RedirectionContext redirectionResult = null;
        //* ex: ls   /tmp/baz   >   /tmp/foo/baz.md
        //*     [0]     [1]    [2]      [3]
        //*  tokenizedParts.length = 4. tP.length - 1 = 3
        //*  i = 3
        //*  First  run: tokenizedParts[3] = /tmp/foo/baz.md
        //*  Second run: tokenizedParts[2] = >
        //*  RedirectionType FOUND: STDOUT
        //*
        for (int i = tokenizedParts.length - 1; i >= 1; i--) {
            RedirectionType redirType = RedirectionType.fromOperator(tokenizedParts[i]);
            if (redirType != null && redirType.isRedirect()) {
                //if Redirect Operator is the last element, throw exception.
                if (i == tokenizedParts.length - 1)
                    throw new InvalidRedirectionPathException();
                redirectionResult = new RedirectionContext(
                        Arrays.copyOfRange(tokenizedParts, 0, i),   //? partsBeforeOutput
                        tokenizedParts[i + 1],                           //? output
                        RedirectionType.fromOperator(tokenizedParts[i])  //? redirectionType
                );
                break;
            }
        }
              //!DEBUG
//            System.out.println("partsBeforeOutput: " + Arrays.toString(partsBeforeOutput));
//            System.out.println("outputPath: " + outputPath);
//            System.out.println("RedirectionType: " + redirectionResult.getRedirectionType().toString());

        return redirectionResult;
    }
}
