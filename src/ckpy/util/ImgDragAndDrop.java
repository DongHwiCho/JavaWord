import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class ImgDragAndDrop {
    JTextPane textPane;
    // FileSave 메소드에서 인코딩하기위해 스태틱메소드를 사용함 (추후 삽입 메소드에서도 사용하기 위함)
    static BufferedImage image;

    // 생성자 - JTextPane을 받아와 초기화
    public ImgDragAndDrop(JTextPane textPane) {
        this.textPane = textPane;
        initializeDragAndDrop();
    }

    // 드래그 앤 드롭 기능 초기화 메서드
    private void initializeDragAndDrop() {
        new DropTarget(textPane, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                // 드래그가 텍스트 영역에 들어올 때 호출
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                // 드래그가 텍스트 영역 위를 지나갈 때 호출
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
                // 드롭 동작이 변경될 때 호출
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                // 드래그가 텍스트 영역을 벗어날 때 호출
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    // 드롭 동작 수락
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = dtde.getTransferable();
                    DataFlavor[] flavors = transferable.getTransferDataFlavors();

                    for (DataFlavor flavor : flavors) {
                        if (flavor.isFlavorJavaFileListType()) {
                            // 드롭된 파일 리스트 가져오기
                            List<File> files = (List<File>) transferable.getTransferData(flavor);
                            for (File file : files) {
                                if (isImageFile(file)) {
                                    // 이미지 파일인 경우 BufferedImage로 읽기
                                    image = ImageIO.read(file);
                                    if (image != null) {
                                        // JTextPane에 이미지 삽입
                                        ImageIcon imageIcon = new ImageIcon(image);
                                        textPane.insertIcon(imageIcon);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 이미지 파일 여부 확인 메서드
    private static boolean isImageFile(File file) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        String fileName = file.getName().toLowerCase();
        for (String extension : imageExtensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
