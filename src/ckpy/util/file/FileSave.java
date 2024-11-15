package ckpy.util.file;

import ckpy.util.ImgDragAndDrop;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;
import java.util.Base64;
import java.util.List;

public class FileSave implements ActionListener {
    JFrame frame;
    JTextPane textPane;
    List<String> base64Images = new ArrayList<>();

    // 생성자로 저장할 메인 프레임과 텍스트를 입력받음
    public FileSave(JFrame frame, JTextPane textPane) {
        this.frame = frame;
        this.textPane = textPane;
    }

    // ActionListener 을 상속받아 저장버튼과 연결할 영역을 지정함. 저장버튼 이벤트 처리에 사용
    @Override
    public void actionPerformed(ActionEvent e) {
        saveFile();
    }

    public String encodeImageToBase64() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(ImgDragAndDrop.image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            base64Images.add(Base64.getEncoder().encodeToString(imageBytes));
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 저장할 프레임과 Text 지정
    public void saveFile() {

        JFileChooser fileChooser = new JFileChooser();
        // 저장할 형식 지정 지정가능
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "ckpy", "ckpy");
        fileChooser.setFileFilter(filter);

        // 저장할 컴포넌트를 선택 및 파일 선택기를 팝업함
        int option = fileChooser.showSaveDialog(frame);

        // showSaveDialog 가 정상작동하면 지정한 파일의 주소를 갖고옴
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // 파일 확장자가 .ckpy로 끝나지 않으면 자동으로 추가
            if (!file.getName().toLowerCase().endsWith(".ckpy")) {
                file = new File(file.getAbsolutePath() + ".ckpy");
            }

            if (ImgDragAndDrop.image != null) {
                encodeImageToBase64();
            }

            // 파일 쓰기 클래스 사용 Writer 클래스로 text 를 저장
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // HTML 형태의 텍스트와 base64 데이터를 작성
                // 텍스트와 base64 데이터를 작성
                writer.write("<content>\n");
                writer.write("<text>\n");
                writer.write(textPane.getText() + "\n");
                writer.write("</text>\n");
                if (ImgDragAndDrop.image != null) {
                    writer.write("<images>\n");
                    for (String base64Image : base64Images) {
                        writer.write("<image>" + base64Image + "</image>\n");
                    }
                }
                writer.write("</images>\n");
                writer.write("</content>\n");

                JOptionPane.showMessageDialog(null, "파일 저장이 되었습니다 ");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "파일 작성 중 오류가 발생했습니다: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}
