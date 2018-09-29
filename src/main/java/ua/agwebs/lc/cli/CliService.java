package ua.agwebs.lc.cli;

import java.util.Iterator;
import java.util.List;

/**
 * Service to operate command line arguments
 *
 */
public interface CliService {

    /**
     * Validates line of arguments
     *
     * @return true if set of command line arguments is valid, otherwise false
     */
    boolean isValid();

    /**
     * A target argument is what a program is to work on (e.g. file name)
     *
     * @return  values of target arguments in order with command line
     */
    List<String> getTargets();

    /**
     * A target argument is what a program is to work on (e.g. file name)
     *
     * @param indx order number of target argument
     * @return value of target argument
     */
    String getTarget(int indx);

    /**
     * A switcher is configuration parameter for a program.
     * Could be optional.
     *
     * @param switcher a type of option from command line
     * @return parameter value
     */
    String getSwitcherValue(String switcher);

    /**
     * A switcher is configuration parameter for a program.
     * Could be optional.
     *
     * @param switcher a type of option from command line
     * @param defValue default value
     * @return parameter value if it exists, otherwise default value
     */
    String getSwitcherValue(String switcher, String defValue);

    /**
     * A switcher is configuration parameter for a program.
     *
     * @param switcher a type of option from command line
     * @return true if switcher is present, otherwise false
     */
    boolean isPresent(String switcher);

    Iterator<String> getSwitcherIterator();
}
