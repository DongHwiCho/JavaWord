package ckpy.keyevent;

import ckpy.gui.MainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

// Shortcut 설정 클래스
public class ShortcutManager {
    private final MainFrame view;
    private final Map<String, String> shortcuts;

//    public ShortcutManager(JTextPane textPane, MainFrame view) {
//        this.textPane = textPane;
//        this.view = view;
//        this.shortcuts = new HashMap<>();
//        configureShortcuts();
//        applyShortcuts();
//    }
public ShortcutManager(MainFrame view) {
        this.view = view;
        this.shortcuts = new HashMap<>();
        configureShortcuts();
        applyShortcuts();
    }
    // 단축키 매핑 설정
    private void configureShortcuts() {
        shortcuts.put("control T", "shortcutActionT");
        shortcuts.put("control D", "shortcutActionD");
        shortcuts.put("control S", "shortcutActionS");
    }

    // 단축키들을 JTextPane에 설정
    private void applyShortcuts() {
        for (Map.Entry<String, String> entry : shortcuts.entrySet()) {
            String keyStroke = entry.getKey();
            String actionName = entry.getValue();
            view.textPane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(keyStroke), actionName);
            view.textPane.getActionMap().put(actionName, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.onShortcutKeyPressed(keyStroke);
                }
            });
        }
    }
}
