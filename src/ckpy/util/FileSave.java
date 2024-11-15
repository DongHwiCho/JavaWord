package ckpy.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileSave implements ActionListener {
    JFrame frame;
    JTextPane textPane;

    // 생성자로 저장할 메인 프레임과 텍스르를 입력받음
    public FileSave(JFrame frame, JTextPane textPane) {
        this.frame = frame;
        this.textPane = textPane;
    }

    // ActionListener 을 상속받아 저장버튼과 연결할 영역을 지정함. 저장버튼 이벤트 처리에 사용
    @Override
    public void actionPerformed(ActionEvent e) {
        saveFile(frame, textPane);
    }

    // 저장할 프레임과 Text 지정
    public void saveFile(JFrame frame, JTextPane textPane) {

        JFileChooser fileChooser = new JFileChooser();
        // 저장할 컴포넌트를 선택 및 파일 선택기를 팝업함
        int option = fileChooser.showSaveDialog(frame);

        // showSaveDialog 가 정상작동하면 지정한 파일의 주소를 갖고옴
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // 파일 쓰기 클래스 사용 Writer 클래스로 text 를 저장
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textPane.getText());
                JOptionPane.showMessageDialog(frame, "파일이 저장되었습니다.", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "파일 저장 중 오류가 발생했습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
