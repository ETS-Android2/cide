package ui;

import application.PescaUI;

public class FormManager {

    /**
     * PescaUI to manage.
     */
    private final PescaUI pescaUI;

    /**
     * The current form.
     */
    private Form currentForm;

    /**
     * @param pescaUI to manage.
     */
    public FormManager(PescaUI pescaUI) {
        this.pescaUI = pescaUI;
    }

    /**
     * Load the form, run all components and show it.
     */
    private void loadForm(){
        getCurrentForm().run();
        getCurrentForm().show();
    }

    /**
     * Unload the form, unload all components and hide it.
     */
    private void unloadForm(){
        if(getCurrentForm() != null){
            getCurrentForm().unload();
            getCurrentForm().hide();
        }
    }

    /**
     * @param form to assign.
     */
    private void setCurrentForm(Form form){
        this.currentForm = form;
    }

    public Form getCurrentForm() {
        return currentForm;
    }

    public PescaUI getPescaUI() {
        return pescaUI;
    }

    /**
     *
     * Unloads the current form, then sets the new form and loads it.
     *
     * @param chatForm to assign.
     */
    public void changeForm(Form chatForm){
        unloadForm();
        setCurrentForm(chatForm);
        loadForm();
    }

}
