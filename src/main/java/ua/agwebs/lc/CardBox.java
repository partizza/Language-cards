package ua.agwebs.lc;

public abstract class CardBox {

    /**
     * Template method
     */
    public void execute(){
        initData();
        drill();
    }

    /**
     * init language cards
     */
    abstract protected void initData();

    /**
     * train language cards
     */
    abstract protected void drill();
}
