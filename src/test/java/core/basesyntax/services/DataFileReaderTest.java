package core.basesyntax.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class DataFileReaderTest{
    private static final String FILE_PATH
            = "src/test/resources/testFile.csv";
    private static final String EMPTY_FILE_PATH
            = "src/test/resources/emptyTestFile.csv";
    private static final String NON_EXISTED_FILE_PATH
            = "src/test/resources/111.txt";
    private static DataFileReader fileReader;
    private static List<String[]> expected;

    @BeforeClass
    public static void beforeClass(){
        fileReader = new DataFileReader();
    }

    @Before
    public void beforeEach(){
        expected = new ArrayList<>();
    }

    @Test
    public void readFromFile_ok(){
        expected.add(new String[]{"s","banana","100","2020-10-17"});
        expected.add(new String[]{"b","banana","13","2020-10-15"});
        expected.add(new String[]{"b","orange","30","2020-10-15"});

        List<String[]> actual = fileReader.readDataFromFile(FILE_PATH);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void readFromEmptyFile(){
        List<String[]> actual = fileReader.readDataFromFile(EMPTY_FILE_PATH);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test(expected = RuntimeException.class)
    public void readFromNonExistedFile(){
        DataFileReader fileReader = new DataFileReader();
        fileReader.readDataFromFile(NON_EXISTED_FILE_PATH);
    }
}
