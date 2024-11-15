package ckpy.util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOpen implements ActionListener {
    JFrame frame;
    JTextPane textPane;

    // 오픈 후 열게 될 프레임과 텍스트 영역 입력
    public FileOpen(JFrame frame, JTextPane textPane) {
        this.frame = frame;
        this.textPane = textPane;
    }

    // ActionListener 을 상속받아 저장버튼과 연결할 영역을 지정함. 불러오기 버튼 이벤트 처리에 사용
    @Override
    public void actionPerformed(ActionEvent e) {
        openFile(frame);
    }

    public void openFile(JFrame frame) {
        JFileChooser chooser = new JFileChooser();
        // 불러올 형식 지정 txt 만 지정가능
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "txt", "txt");
        chooser.setFileFilter(filter);

        // 불러올 컴포넌트를 선택 및 파일 선택기를 팝업함
        int returnVal = chooser.showOpenDialog(frame);

        // showOpenDialog 가 정상작동하면 지정한 파일의 주소를 갖고옴
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
//            System.out.println(chooser.getSelectedFile());

            // 파일 리더
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textPane.setText(""); // 기존 텍스트 지우기
                Document doc = textPane.getDocument(); // textPane 에 저장된 text 를 반환
                String line;

                // 한줄씩 파일을 읽어 줄이 없을떄 까지 반복
                while ((line = reader.readLine()) != null) {
                    /* 텍스트를 doc 에 삽입 textPane 이 doc 형태이기 때문
                    읽어온 줄 마다 줄바꿈을 실행함
                    null 로 입력한 인수에 텍스트에 특정 스타일을 적용할 수 있음. 추후 수정
                     */
                    doc.insertString(doc.getLength(), line + "\n", null);
                }
                JOptionPane.showMessageDialog(frame, "파일이 성공적으로 열렸습니다.", "파일 열기", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | BadLocationException ex) {
                JOptionPane.showMessageDialog(frame, "파일 열기 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
