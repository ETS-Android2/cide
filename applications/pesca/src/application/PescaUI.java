package application;

import services.PescaAPI;
import services.PescaAPISimple;
import ui.Form;
import ui.FormManager;
import ui.forms.MainForm;

import java.util.HashMap;

public class PescaUI {

    /**
     * Singleton Instace
     */
    private static PescaUI pescaUI;

    /**
     * Form Manager to manage forms scenes.
     */
    private final FormManager formManager;

    /**
     * HashMap of forms to switch inside controllers.
     */
    private final HashMap<String, Form> forms;

    /**
     * API to access with the application.
     */
    private final PescaAPISimple api;

    /**
     *
     * Initializes some services, the form manager,
     * the forms hashmap, the API and registers some predefined forms.
     *
     * @throws Exception if some flow produces an error.
     */
    private PescaUI() throws Exception {
        formManager = new FormManager(this);
        forms = new HashMap<>();
        api = new PescaAPISimple();
        registerForms();
    }

    /**
     *
     * Initialize the PescaUI class and if is instantiated returns self.
     *
     * @return the instance of PescaUI.
     * @throws Exception if some flow produces an error.
     */
    public static PescaUI init() throws Exception {
        if(pescaUI != null){
            return pescaUI;
        }
        pescaUI = new PescaUI();
        return pescaUI;
    }

    public FormManager getFormManager() {
        return formManager;
    }

    public PescaAPI getApi() {
        return api;
    }

    public HashMap<String, Form> getForms() {
        return forms;
    }

    /**
     * Registers some predefined forms.
     */
    private void registerForms(){
        Form mainForm = new MainForm(this.formManager);
        this.forms.put("main",mainForm);
    }

}
