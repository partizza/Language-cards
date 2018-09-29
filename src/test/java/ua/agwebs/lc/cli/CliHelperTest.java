package ua.agwebs.lc.cli;

import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class CliHelperTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullArgs() {
        new CliHelper(null);
    }

    @Test
    public void testEmptyArgs() {
        CliHelper cli = new CliHelper(new String[0]);
        assertFalse("Command line without target element should not be valid.", cli.isValid());
    }

    @Test
    public void testMoreThanOneTargetArg() {
        int numb = ThreadLocalRandom.current().nextInt(2, 100);
        String[] args = new String[numb];
        for (int i = 0; i < numb; i++) args[i] = "target " + String.valueOf(i);
        CliHelper cli = new CliHelper(args);
        assertFalse("Command line with more than one target should not be valid.", cli.isValid());
    }

    @Test
    public void testOnValidCommand() {
        String args[] = new String[]{"--parser=csv", "-czx", "--opt=beta", "some_data.txt"};
        CliHelper cli = new CliHelper(args);

        assertTrue(cli.isValid());
        assertTrue(cli.getTargets().size() == 1);

        assertTrue(cli.getSwitcherValue("parser").equals("csv"));
        assertTrue(cli.getSwitcherValue("opt", "DefVal").equals("beta"));

        assertTrue(cli.getSwitcherValue("c", "DefVal for c").equals("DefVal for c"));
        assertTrue(cli.isPresent("c"));
        assertTrue(cli.getSwitcherValue("z") == null);
        assertTrue(cli.isPresent("z"));
        assertTrue(cli.getSwitcherValue("x", "DefVal for x").equals("DefVal for x"));
        assertTrue(cli.isPresent("x"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithParamAndValueSpaceSeparator() {
        String args[] = new String[]{"--parser", "csv", "some_data.txt"};
        new CliHelper(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithParamAndMissedValue() {
        String args[] = new String[]{"--parser", "--opt=beta", "some_data.txt"};
        new CliHelper(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithIncorrectOptions(){
        String args[] = new String[]{"-", "some_data.txt"};
        new CliHelper(args);
    }

    @Test
    public void testOrderOfParams(){
        String args[] = new String[]{"--parser=csv", "-z", "--opt=beta", "some_data.txt"};
        CliHelper cli = new CliHelper(args);

        assertTrue(cli.isValid());
        Iterator<String> iterator = cli.getSwitcherIterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next().equals("parser"));
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next().equals("z"));
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next().equals("opt"));
    }

}