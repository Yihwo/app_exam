import java.util.Scanner;

class Vender {
    int money = 50000;
    int sugar = 200;
    int cream = 100;
    int cocoa = 200;

    String tea="";
    int getMoney=0;
    int change = 0;


    public  void runVender(){
        inputMoney();
        selectTea();

        makeTea(tea);
        change = returnWon(tea,getMoney);
        System.out.println("거스름 돈은 "+change+"원 입니다.");
    }
    public void inputMoney(){
        Scanner key = new Scanner(System.in);
        System.out.println("금액을 넣어주세요.");
        getMoney = key.nextInt();
        System.out.println(getMoney+"원을 입력받았습니다.");
    }

    public void selectTea(){
        Scanner key = new Scanner(System.in);
        System.out.println("커피를 선택하세요.");
        System.out.println("크림, 설탕, 코코아");
        tea = key.nextLine();
        while(true){
            if(tea.equals("크림")) {
                break;
            }
            else if (tea.equals("설탕")){
                break;
            }
            else if (tea.equals("코코아")){
                break;
            }
            else{
                System.out.println("잘못된 선택 입니다.");
                tea = key.nextLine();
            }
        }
    }

    public void makeTea(String tea){
        switch (tea){
            case "크림" :
                System.out.println("크림 커피를 만들었습니다.");
                break;
            case "설탕" :
                System.out.println("설탕 커피를 만들었습니다.");
                break;
            case "코코아":
                System.out.println("코코아를 만들었습니다.");
                break;
        }
    }
    public int returnWon(String tea, int getMoney){
        int change = 0;
        switch (tea){
            case "크림" :
                change = getMoney-100;
                break;
            case "설탕" : case "코코아":
                change = getMoney-200;
                break;
        }
        return change;
    }
}
