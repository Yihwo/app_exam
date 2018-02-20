import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckBoxHandler implements ItemListener{
    JCheckBox cb1;
    JCheckBox cb2;
    JCheckBox cb3;
    public CheckBoxHandler(JCheckBox cb1) {
        this.cb1 = cb1;
    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){//체크 박스가 선택된 경우
            System.out.println("선택 되었습니다.");
            System.out.println(cb1.getText());
        }else{
            System.out.println("선택이 해제되었습니다.");
        }
    }
}
