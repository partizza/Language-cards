import ua.agwebs.lc.CardsExecutor;
import ua.agwebs.lc.CardsExecutorFactory;
import ua.agwebs.lc.cli.CliHelper;

public class App {

    public static void main(String[] args) {
        CardsExecutor cardBox = CardsExecutorFactory.create(new CliHelper(args));
        cardBox.execute();
    }
}
