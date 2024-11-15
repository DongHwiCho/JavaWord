package ckpy.gui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RibbonMenu {
    // 필드 선언
    private JFrame frame;
    private JTextPane textPane;
    private JToolBar toolBar;

    private JButton boldButton;
    private JButton italicButton;
    private JButton redButton;
    private JButton blueButton;
    private JButton blackButton;

    public RibbonMenu(JFrame frame, JTextPane textPane){
        // Field에 레퍼런스 할당
        this.frame = frame;
        this.textPane = textPane;

        // JToolBar 생성
        this.toolBar = new JToolBar("Ribbon");

        // JToolBar에 item 추가
        this.addItem();
        this.addAction();
    }

    private void addItem(){
        // JToolBar 리본메뉴에 아이템 추가
        // 굶게 버튼
        boldButton = new JButton("굶게");
        toolBar.add(boldButton);
        // 기울게 버튼
        italicButton = new JButton("기울게");
        toolBar.add(italicButton);
        // 빨강 버튼
        redButton = new JButton("빨강");
        toolBar.add(redButton);
        // 파랑 버튼
        blueButton = new JButton("파랑");
        toolBar.add(blueButton);
        // 검정 버튼
        blackButton = new JButton("검정");
        toolBar.add(blackButton);
    }

    private void addAction(){
        boldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction("Bold");
            }
        });
        italicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonAction("Italic");
            }
        });
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorAction(Color.RED);
            }
        });
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorAction(Color.BLUE);
            }
        });
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorAction(Color.BLACK);
            }
        });
    }

    public void buttonAction(String mode) {
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        // 선택된 텍스트가 없으면 커서 위치의 한 글자를 대상으로 설정
        if (start == end && start > 0) {
            start--;
            end = start + 1;
        }

        // 현재 스타일을 가져오기
        Element element = doc.getCharacterElement(end-1);
        AttributeSet as = element.getAttributes();

        // 현재 텍스트가 굵게 설정되었는지 확인
        boolean isCheck = false;
        if (mode.equals("Bold")){
            isCheck = StyleConstants.isBold(as);
        } else if (mode.equals("Italic")){
            isCheck = StyleConstants.isItalic(as);
        }

        // 새로운 스타일 속성 생성
        SimpleAttributeSet newAttr = new SimpleAttributeSet();

        if (mode.equals("Bold")){
            StyleConstants.setBold(newAttr, !isCheck);  // 현재 상태의 반대로 설정
        } else if (mode.equals("Italic")){
            StyleConstants.setItalic(newAttr, !isCheck);  // 현재 기울임 상태의 반대로 설정
        }

        // 선택된 텍스트에 새로운 스타일 적용
        doc.setCharacterAttributes(start, end - start, newAttr, false);
    }

    public void colorAction(Color color){
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        // 선택된 텍스트가 없으면 커서 위치의 한 글자를 대상으로 설정
        if (start == end && start > 0) {
            start--;
            end = start + 1;
        }

        // 선택한 텍스트가 있는지 확인
        if (start != end) {
            Style style = textPane.addStyle("ColorStyle", null);
            StyleConstants.setForeground(style, color);

            // 선택한 텍스트에 스타일 적용
            doc.setCharacterAttributes(start, end - start, style, true);
        }
    }

    public JToolBar getToolbar(){
        return this.toolBar;
    }
}
