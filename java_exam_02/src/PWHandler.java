import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PWHandler implements ActionListener{
    JTextField id;
    JPasswordField pw;
    public PWHandler (JTextField id, JPasswordField pw){
        this.id = id;
        this.pw = pw;
    }
    public void actionPerformed(ActionEvent e) {  //이벤트가 발생하면 함수 실행
        System.out.println("ID : "+id.getText());
        System.out.println("PassWord : "+new String(pw.getPassword()));
        id.setText("");
        pw.setText("");
    }
}
