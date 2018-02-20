
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Exam {
    public static void main(String[] args) {
        Random random = new Random();//랜덤 함수
        random.nextInt(6);//int형식의 무작위 숫자 를 만들어냄

        int i;
        for (i=0;i<100;i++) {
            System.out.print(random.nextInt(6) + " ");
        }
//        Integer integer = new Integer(num);
//        double num2 = integer.doubleValue();
//        System.out.println(num+" "+num2);


//        JFrame frm = new JFrame("First Swing");
//        frm.setBounds(120,120,250,270);//크기 설정 (x,y, width, height)
//        frm.setLayout(new FlowLayout());
//        JTextArea txtArea = new JTextArea(10,20);
//        JButton btn = new JButton("clear");
//
//
//        //체크박스
//        JCheckBox cb1 = new JCheckBox("수박");//체크박스 종류
//        JCheckBox cb2 = new JCheckBox("딸기");
//        JCheckBox cb3 = new JCheckBox("사과");
//
//        CheckBoxHandler cbHandler1 = new CheckBoxHandler(cb1);//선택된 종류를 콘솔창에 출력할 클래스
//        CheckBoxHandler cbHandler2 = new CheckBoxHandler(cb2);
//        CheckBoxHandler cbHandler3 = new CheckBoxHandler(cb3);
//        cb1.addItemListener(cbHandler1);
//        cb2.addItemListener(cbHandler2);
//        cb3.addItemListener(cbHandler3);
//
//        frm.add(cb1);
//        frm.add(cb2);
//        frm.add(cb3);//프레임에 버튼을 추가
//
//        JRadioButton rb1 = new JRadioButton("한국");
//        JRadioButton rb2 = new JRadioButton("미국");
//        JRadioButton rb3 = new JRadioButton("중국");
//
//        ButtonGroup btnGroup1 = new ButtonGroup();//라디오 버튼들을 하나의 그룹으로 묶음
//        btnGroup1.add(rb1);
//        btnGroup1.add(rb2);
//        btnGroup1.add(rb3);//그룹에 라디오 버튼들을 포함시킴
//
//        RadioButtonHandler rbHandler1 = new RadioButtonHandler(rb1);
//        RadioButtonHandler rbHandler2 = new RadioButtonHandler(rb2);
//        RadioButtonHandler rbHandler3 = new RadioButtonHandler(rb3);
//
//        rb1.addItemListener(rbHandler1);
//        rb2.addItemListener(rbHandler2);
//        rb3.addItemListener(rbHandler3);
//
//        frm.add(rb1);
//        frm.add(rb2);
//        frm.add(rb3);
//
//        frm.setVisible(true);

//        BtnTxtHandler btnTxtHandler = new BtnTxtHandler(txtArea);
//        btn.addActionListener(btnTxtHandler);//버튼에 리스너 등록
//
//        txtArea.setCaretPosition(txtArea.getText().length());//커서 위치 설정
//        txtArea.setLineWrap(true);//자동 개행(줄바꿈) 활성화
//        txtArea.setWrapStyleWord(true);//단어 자동개행 활성화
//
//        JScrollPane simpleScroll = new JScrollPane(txtArea);//텍스트 박스에 스크롤기능 활성
//
//        frm.add(simpleScroll);//스크롤을 프레임에 추가
//        frm.add(txtArea);
//        frm.add(btn);
//
//        frm.setVisible(true);
//        frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

//        JLabel label1 = new JLabel("ID");
//        JLabel label2 = new JLabel("PW");
//        JLabel label3 = new JLabel("e-mail");
//
//        JTextField txtField1 = new JTextField(10);
//        JPasswordField pwField2 = new JPasswordField(10);
//        JTextField txtField2 = new JTextField(20);
//
//        PWHandler pwHandler = new PWHandler(txtField1,pwField2);
//
//        txtField1.addActionListener(pwHandler);
//        pwField2.addActionListener(pwHandler);
//
//        frm.add(label1);
//        frm.add(txtField1);
//        frm.add(label2);
//        frm.add(pwField2);
//        frm.add(label3);
//        frm.add(txtField2);
//
//        frm.setVisible(true);
//        WindowListener listen = new WindowAdapter(){//리스너 생성
//            public void windowClosing(WindowEvent ev) {    //윈도우 차이 종료되면
//                System.exit(0);                     //해당 시스템이 종료됨
//            }
//            public void WindowOpened (WindowEvent we){
//                System.out.println("프로그램 수행");
//            }
//        };
//        frm.addWindowListener(listen);// 컴포넌트 등록

//
//        //frm.setLayout(new FlowLayout());//버튼들의 배치 방식 설정
//        //frm.setLayout(new BorderLayout());//버튼의 위치를 변경하기 위해 사용
//        //frm.setLayout(new GridLayout(3,2));//버튼을 격자로 배열하기 위해 사용
//
//        //j판넬을 이용하기
//        frm.setLayout(new BorderLayout());
//
//        JButton btn1 = new JButton("my Button");
//        JButton btn2 = new JButton("your Button");
//        JButton btn3 = new JButton("our Button");
//        JButton btn4 = new JButton("my Button");
//        JButton btn5 = new JButton("your Button");
//        JButton btn6 = new JButton("our Button");
//        JButton btn7 = new JButton("our Button");
//        JButton btn8 = new JButton("my Button");
//        JButton btn9 = new JButton("your Button");
//        JButton btn10 = new JButton("our Button");
//
//        MouseListener listener = new MouseEventHandler();
//
//        JPanel panel1 = new JPanel();
//        panel1.setLayout(new FlowLayout());
//        panel1.add(btn1);
//        panel1.add(btn2);
//        panel1.add(btn3);
//        panel1.add(btn4);
//
//        JPanel panel2 = new JPanel();
//        panel2.setLayout(new GridLayout(3,2,2,2));
//        panel2.add(btn5);
//        panel2.add(btn6);
//        panel2.add(btn7);
//        panel2.add(btn8);
//        panel2.add(btn9);
//        panel2.add(btn10);
//
//        frm.add(panel1,BorderLayout.NORTH);
//        frm.add(panel2,BorderLayout.SOUTH);
//
//
//
//
////보더 레이아웃
////        frm.add(btn1, BorderLayout.CENTER);//버튼을 프레임 안에 추가
////        frm.add(btn2, BorderLayout.SOUTH);//버튼 위치 변경
////        frm.add(btn3, BorderLayout.NORTH);
//
//
//        btn1.addMouseListener(listener);
//        btn2.addMouseListener(listener);
//        btn3.addMouseListener(listener);
//
//
//
//        frm.setVisible(true);//프레임을 화면에 나타냄




//        ArrayList<Integer> arrayList = new ArrayList<Integer>();
//        arrayList.add(32);
//        System.out.println(arrayList.get(0));

//        int arr[] = new int[10];
//        int i,j,sum = 0;
//        Scanner key = new Scanner(System.in);
//
//        for(i=0;i<5;i++){
//            j= key.nextInt();
//            arr[i]=j;
//        }
//        for(i=0;i<arr.length;i++){
//            if(i%2==0)
//                sum = sum + arr[i];
//        }
//        System.out.println(sum);
//        String[] arr1 = new String[3];
//        arr1[0]=new String("asdf");
//        arr1[1]="sdfw";
//        arr1[2]=new String("erwq");
//        for(int i=0;i<=2;i++){
//            System.out.println(arr1[i]);
//        }
//        Join myJoin = new Join();
//        String id;
//        String pw1,pw2;
//        Scanner key = new Scanner(System.in);
//        System.out.println("id input");
//        id = key.nextLine();
//        key.nextLine();
//        System.out.println("pw input");
//        pw1 = key.nextLine();
//        myJoin.passwordLength(pw1);
//        System.out.println("pw confirm");
//        pw2 = key.nextLine();
//        myJoin.passwordOverlap(pw1,pw2);


//        String str = "hi";
//        System.out.println(str.length());
//        String str2 = "everyone";
//        System.out.println(str.concat(str2));
//        String str3 = "aaad";
//        String str4 = "aaac";
//        System.out.println(str3.compareTo(str4));


//    Coffee coffee1 = new Coffee(true, false);
//    Coffee coffee2 = new Coffee(true);
//
//    coffee1.showMenu();
//    coffee2.showMenu();

//        System.out.println("1부터 입력한 값까지의 총 합을 구하는 프로그램");
//        System.out.println("1000보다 작은 수를 입력 바랍니다.");
//        System.out.println("종료를 원하면 10000을 입력해 주세요.");
//        System.out.println("총 합을 구할 수를 입력하세요.");
//        int a;
//        double a;
//        Scanner key = new Scanner(System.in);
//        a = key.nextInt();
//        while(1000<a){
//            if(a==10000){
//                System.out.println("프로그램을 종료합니다.");
//                return;
//            }
//            System.out.println("1000보다 작은 수를 입력 바랍니다.");
//            a = key.nextInt();
//        }
//        System.out.println(a+"까지의 합은 " + addAll(a)+"입니다.");
//        a = key.nextDouble();
//        System.out.println("원의 넓이는 : "+circle(a)+"입니다.");
//        int num=1;
//        while(num<100){
//            if(num % 5 != 0 || num % 7 != 0){
//                num++;
//                continue;
//            }
//
//            System.out.println(num);
//            num++;
//        int change;
//        Vender myVender = new Vender();
//        myVender.runVender();


//        Number myNum = new Number();
//        InstCnt cnt1 = new InstCnt();
//        InstCnt cnt2 = new InstCnt();
//        InstCnt cnt3 = new InstCnt();

//        WindowListener listen = new WindowAdapter(){//리스너 생성
//            public void windowClosing(WindowEvent ev) {    //윈도우 차이 종료되면
//                System.exit(0);                     //해당 시스템이 종료됨
//            }
//            public void WindowOpened (WindowEvent we){
//                System.out.println("프로그램 수행");
//            }
//        };
//        frm.addWindowListener(listen);// 컴포넌트 등록
    }


////////////////////////////////////함수///////////////////////////////////////////////
    //20180131
//    public static int addAll(int a){
//        int num,result = 0;
//        for(num=1; num<=a;num++){
//            result = result + num;
//        }
//        return result;
//    }

//    public static double circle(double a){
//        double result;
//        double temp;
//        temp = multi(a,a);
//        result = multi(temp,3.14);
//        return result;
//    }
//    public static double multi(double a,double b){
//        double result;
//        result = a * b;
//        return result;
//    }
}
//class FruitSeller{
//    int numOfApple = 20;
//    int myMoney = 0;
//    public int SaleApple(int money){
//        int num = money/1000;
//        numOfApple-=num;
//        myMoney+=money;
//        return num;
//    }
//}