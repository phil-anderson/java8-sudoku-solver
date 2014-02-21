package utils;

import model.Grid;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PrettyPrinterTest
{
    private static final String EXPECTED_OUTPUT = "123 456 789\n" +
                                                  "456 789 123\n" +
                                                  "789 123 456\n" +
                                                  "\n" +
                                                  "234 567 891\n" +
                                                  "567 891 234\n" +
                                                  "891 234 567\n" +
                                                  "\n" +
                                                  "345 678 912\n" +
                                                  "678 912 345\n" +
                                                  "912 345 678\n" +
                                                  "\n";
    private Grid sampleGrid;
    private PrettyPrinter prettyPrinterUnderTest;

    @Before
    public void setUp() throws Exception
    {
        sampleGrid = new GridTextParser(new Grid()).parseString("123456789\n" +
                                                                "456789123\n" +
                                                                "789123456\n" +
                                                                "234567891\n" +
                                                                "567891234\n" +
                                                                "891234567\n" +
                                                                "345678912\n" +
                                                                "678912345\n" +
                                                                "912345678");
        prettyPrinterUnderTest = new PrettyPrinter();
    }

    @Test
    public void canFormatToAString()
    {
        String formattedGrid = prettyPrinterUnderTest.formatGrid(sampleGrid);
        assertThat(formattedGrid, is(EXPECTED_OUTPUT));
    }

    @Test
    public void canPrettuyPrintToStream()
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        prettyPrinterUnderTest.print(sampleGrid, printStream);
        assertThat(outputStream.toString(), is(EXPECTED_OUTPUT));
    }
}
