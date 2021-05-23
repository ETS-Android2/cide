package application;

import services.PescaAPI;
import ui.Form;
import ui.FormManager;

import java.util.HashMap;

public class PescaUI {

    private static PescaUI pescaUI;

    private FormManager formManager;
    private HashMap<String, Form> forms;
    private PescaAPI api;

    private PescaUI() throws Exception {
        formManager = new FormManager(this);
        forms = new HashMap<>();
        api = new PescaAPI();
    }

    public static PescaUI init() throws Exception {
        if(pescaUI != null){
            throw new Exception("Application already started.");
        }
        return new PescaUI();
    }

    public FormManager getFormManager() {
        return formManager;
    }

    public PescaAPI getApi() {
        return api;
    }

    private void registerForms(){

    }

}
