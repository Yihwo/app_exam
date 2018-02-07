import java.util.Scanner;

public class Exam {
    public static void main(String[] args) {
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
////        a = key.nextDouble();
////        System.out.println("원의 넓이는 : "+circle(a)+"입니다.");
//        int num=1;
//        while(num<100){
//            if(num % 5 != 0 || num % 7 != 0){
//                num++;
//                continue;
//            }
//
//            System.out.println(num);
//            num++;
        int change;
        Vender myVender = new Vender();
        myVender.runVender();
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