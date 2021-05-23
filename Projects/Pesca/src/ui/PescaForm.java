package ui;

import javax.swing.*;

public abstract class PescaForm implements Form {

    protected FormManager manager;
    protected JFrame root;

    public PescaForm(FormManager manager){
        this.manager = manager;
        this.root = new JFrame();
        this.root.setVisible(false);
        this.root.setResizable(false);
    }

    protected JFrame getRoot() {
        return this.root;
    }

    @Override
    public void unload() {
        getRoot().getContentPane().removeAll();
    }

    @Override
    public void show() {
        getRoot().setVisible(true);
    }

    @Override
    public void hide() {
        getRoot().setVisible(false);
    }

}
