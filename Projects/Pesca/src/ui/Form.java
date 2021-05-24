package ui;

public interface Form {
    /**
     * Create all the form elements.
     */
    public void run();

    /**
     * Remove all elements inside JFrame.
     */
    public void unload();

    /**
     * Set visible the JFrame.
     */
    public void show();

    /**
     * Set false the visibility the JFrame.
     */
    public void hide();
}
