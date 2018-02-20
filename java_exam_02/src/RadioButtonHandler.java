import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonHandler implements ItemListener{
    JRadioButton rb1;
    public RadioButtonHandler(JRadioButton rb1) {
        this.rb1 = rb1;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange()==ItemEvent.SELECTED){
            System.out.println("선택되었습니다.");
            System.out.println(rb1.getText());
        }else{
            System.out.println("다른 버튼을 선택하였습니다.");
        }
    }
}
