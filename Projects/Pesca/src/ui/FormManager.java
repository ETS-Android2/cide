package ui;

import application.PescaUI;

public class FormManager {

    private final PescaUI pescaUI;
    private Form currentForm;

    public FormManager(PescaUI pescaUI) {
        this.pescaUI = pescaUI;
    }

    private void loadForm(){
        getCurrentForm().show();
        getCurrentForm().run();
    }

    private void unloadForm(){
        if(getCurrentForm() != null){
            getCurrentForm().unload();
            getCurrentForm().hide();
        }
    }

    private void setCurrentForm(Form form){
        this.currentForm = form;
    }

    public Form getCurrentForm() {
        return currentForm;
    }

    public PescaUI getPescaUI() {
        return pescaUI;
    }

    public void changeForm(Form chatForm){
        unloadForm();
        setCurrentForm(chatForm);
        loadForm();
    }

}
