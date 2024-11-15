import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Base64;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

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
        // 불러올 형식 지정 ckpy 만 지정 가능
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ckpy", "ckpy");
        chooser.setFileFilter(filter);

        // 불러올 컴포넌트를 선택 및 파일 선택기를 팝업함
        int returnVal = chooser.showOpenDialog(frame);

        // showOpenDialog 가 정상작동하면 지정한 파일의 주소를 갖고옴
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            // 파일 리더
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textPane.setText(""); // 기존 텍스트 지우기
                Document doc = textPane.getDocument(); // textPane 에 저장된 text 를 반환
                String line;
                boolean isTextSection = false;
                boolean isImageSection = false;

                // 한줄씩 파일을 읽어 줄이 없을 때까지 반복
                while ((line = reader.readLine()) != null) {
                    // 텍스트 섹션 시작 확인
                    if (line.contains("<text>")) {
                        isTextSection = true;
                        continue;
                    } else if (line.contains("</text>")) {
                        isTextSection = false;
                        continue;
                    }

                    // 이미지 섹션 시작 확인
                    if (line.contains("<images>")) {
                        isImageSection = true;
                        continue;
                    } else if (line.contains("</images>")) {
                        isImageSection = false;
                        continue;
                    }

                    // 텍스트 섹션 처리
                    if (isTextSection) {
                        doc.insertString(doc.getLength(), line + "\n", null);
                    }

                    // 이미지 섹션 처리
                    if (isImageSection) {
                        if (line.contains("<image>")) {
                            String base64Image = line.substring(line.indexOf("<image>") + 7, line.indexOf("</image>"));
                            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                            BufferedImage image = ImageIO.read(bais);

                            if (image != null) {
                                // 이미지 BufferedImage를 ImageIcon으로 변환하여 textPane에 추가
                                ImageIcon imageIcon = new ImageIcon(image);
                                textPane.insertIcon(imageIcon);
                                doc.insertString(doc.getLength(), "\n", null); // 이미지 이후 줄바꿈 추가
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(frame, "파일이 성공적으로 열렸습니다.", "파일 열기", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | BadLocationException ex) {
                JOptionPane.showMessageDialog(frame, "파일 열기 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
