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

        // 메뉴바 생성
        JMenuBar menuBar = new JMenuBar();
        // 프레임에 메뉴바 설정
        frame.setJMenuBar(menuBar);

        // 메뉴 생성
        JMenu fileMenu = new JMenu("파일");
        JMenu aboutMenu = new JMenu("정보");
        // 메뉴바에서 메뉴 추가
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        // 메뉴 아이텀 생성
        JMenuItem saveMenuItem = new JMenuItem("저장");
        JMenuItem loadMenuItem = new JMenuItem("불러오기");
        JMenuItem exitMenuItem = new JMenuItem("닫기");
        // 메뉴에 메뉴 아이템 추가
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);

        // 텍스트 페인 생성
        JTextPane textPane = new JTextPane();
        // 프레임에 텍스트 페인 추가
        frame.add(textPane);

        /*
        저장 버튼에 FileSave 클래스의 saveFile 메서드 연결
        addActionListener를 통해 버튼을 연결해 이벤트를 처리함
         */
        FileSave fileSave = new FileSave(frame,textPane);
        saveMenuItem.addActionListener(fileSave);
        FileOpen fileOpen = new FileOpen(frame,textPane);
        loadMenuItem.addActionListener(fileOpen);

        // 프레임 표시 설정 ( 항상 마지막에 실행 )
        frame.setVisible(true);
    }
}
