import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnTxtHandler implements ActionListener {

    JTextArea txtArea;
    public BtnTxtHandler(JTextArea txtArea){
        this.txtArea = txtArea;
    }
    public void actionPerformed(ActionEvent e) {
        txtArea.setText("모든 내용을 지웠습니다.\n");
        txtArea.append("원하는 내용을 입력하세요.\n");
    }
}
