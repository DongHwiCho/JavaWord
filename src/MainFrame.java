import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public MainFrame() {
        // 프레임 객체 생성
        JFrame frame = new JFrame();
        // 기본 닫기 버튼 기능 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 프레임 사이즈 설정
        frame.setSize(1920, 1080);

        // 텍스트 페인 생성
        JTextPane textPane = new JTextPane();
        // 프레임에 텍스트 페인 추가
        frame.add(textPane);

        // 메뉴바 생성
        TopMenuBar menuBar = new TopMenuBar(frame, textPane);
        // 프레임에 메뉴바 설정
        frame.setJMenuBar(menuBar.getMenuBar());

        // 프레임 표시 설정 ( 항상 마지막에 실행 )
        frame.setVisible(true);
    }
}
