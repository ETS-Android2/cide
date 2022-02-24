package com.github.pomaretta.cide.console;

import java.util.ArrayList;

public interface Menu<T> {
    T view(ArrayList<T> list, boolean selection);
    void create();
    void search();
    void remove();
    void modify();
}
