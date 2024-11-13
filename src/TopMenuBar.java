import javax.swing.*;

public class TopMenuBar {
    // 필드 선언
    JFrame frame;
    JTextPane textPane;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu aboutMenu;
    JMenuItem saveMenuItem;
    JMenuItem loadMenuItem;
    JMenuItem exitMenuItem;
    FileSave fileSave;
    FileOpen fileOpen;

    public TopMenuBar(JFrame frame, JTextPane textPane){
        this.frame = frame;
        this.textPane = textPane;
        // 메뉴바 생성
        this.menuBar = new JMenuBar();

        // addMenu 메소드 실행
        this.addMenu();

        // 파일 저장, 불러오기 클래스 생성
        fileSave = new FileSave(frame,textPane);
        fileOpen = new FileOpen(frame,textPane);
        // addAction 메소드 실행
        this.addAction();
    }

    private void addMenu(){
        // 메뉴 생성
        fileMenu = new JMenu("파일");
        aboutMenu = new JMenu("정보");
        // 메뉴바에서 메뉴 추가
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        // 메뉴 아이텀 생성
        saveMenuItem = new JMenuItem("저장");
        loadMenuItem = new JMenuItem("불러오기");
        exitMenuItem = new JMenuItem("닫기");
        // 메뉴에 메뉴 아이템 추가
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
    }

    private void addAction(){
        /*
            저장 버튼에 FileSave 클래스의 saveFile 메서드 연결
            addActionListener를 통해 버튼을 연결해 이벤트를 처리함
         */
        saveMenuItem.addActionListener(fileSave);
        loadMenuItem.addActionListener(fileOpen);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
