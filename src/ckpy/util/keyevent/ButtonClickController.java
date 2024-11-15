package ckpy.keyevent;

import ckpy.gui.MainFrame;

public class ButtonClickController {
    public ButtonClickController(MainFrame view) {
        new ShortcutManager(view);
    }
}