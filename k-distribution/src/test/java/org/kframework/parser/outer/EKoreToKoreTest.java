package org.kframework.parser.outer;


import org.junit.Test;
import org.kframework.kore.K;

import java.io.File;
import java.net.URISyntaxException;

public class EKoreToKoreTest {

    @Test
    public void test() throws URISyntaxException {
        CharSequence theTextToParse = "module FOO syntax Exp ::= Exp [stag(as(d)f)] rule ab cd [rtag(.::KList)] endmodule";
        String mainModule = "KORE";
        String startSymbol = "KDefinition";
        File definitionFile = new File(this.getClass().getResource("/e-kore.k").toURI()).getAbsoluteFile();

        K kBody = NewOuterParserTest.parseWithFile(theTextToParse, mainModule, startSymbol, definitionFile);

        doTransformation(kBody);
    }

    private K doTransformation(K ekore) {
        return null;
    }
}
