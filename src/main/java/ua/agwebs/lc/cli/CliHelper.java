package ua.agwebs.lc.cli;

import java.util.*;

public class CliHelper implements CliService {

    private static final String OPTION_FLAG = "-";
    private static final String PARAM_VALUE_FLAG = "--";
    private static final String PARAM_AND_VALUE_SEPARATOR = "=";

    private LinkedHashMap<String, String> options;
    private List<String> targets;

    public CliHelper(String[] args) {
        if (args == null) throw new IllegalArgumentException();

        options = new LinkedHashMap<>();
        targets = new ArrayList<>();

        parse(args);
    }

    protected void parse(String[] args) {
        int i = 0;
        while (i < args.length) {
            if (args[i].startsWith(PARAM_VALUE_FLAG)) {
                String[] pair =
                        args[i].substring(PARAM_VALUE_FLAG.length())
                                .split(PARAM_AND_VALUE_SEPARATOR);

                if (pair == null || pair.length != 2)
                    throw new IllegalArgumentException("Can't parse parameter argument: " + args[i]);

                options.put(pair[0], pair[1]);
            } else if (args[i].startsWith(OPTION_FLAG)) {
                String values = args[i].substring(OPTION_FLAG.length());
                if (values == null || values.isEmpty())
                    throw new IllegalArgumentException("Can't parse options: " + args[i]);

                for (char ch : values.toCharArray())
                    options.put(String.valueOf(ch), null);
            } else {
                targets.add(args[i]);
            }

            i++;
        }
    }

    @Override
    public boolean isValid() {
        return targets != null && targets.size() == 1;
    }

    @Override
    public List<String> getTargets() {
        return targets;
    }

    @Override
    public String getTarget(int indx) {
        return targets.get(indx);
    }

    @Override
    public String getSwitcherValue(String switcher) {
        return options.get(switcher);
    }

    @Override
    public String getSwitcherValue(String switcher, String defValue) {
        String val = options.get(switcher);
        return val == null ? defValue : val;
    }

    @Override
    public Iterator<String> getSwitcherIterator() {
        return options.keySet().iterator();
    }

    @Override
    public boolean isPresent(String switcher) {
        return options.containsKey(switcher);
    }

    @Override
    public String toString() {
        return "CliHelper{" +
                "options=" + options +
                ", targets=" + targets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CliHelper cliHelper = (CliHelper) o;

        if (options != null ? !options.equals(cliHelper.options) : cliHelper.options != null) return false;
        return targets != null ? targets.equals(cliHelper.targets) : cliHelper.targets == null;
    }

    @Override
    public int hashCode() {
        int result = options != null ? options.hashCode() : 0;
        result = 31 * result + (targets != null ? targets.hashCode() : 0);
        return result;
    }
}
