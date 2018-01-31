import java.util.Scanner;

public class exam_01 {
    public static void main(String[] args){
        System.out.println("넓이를 구할 원의 반지름을 입력하세요.");
        double a;
        Scanner key = new Scanner(System.in);
        a = key.nextDouble();
        System.out.println("원의 넓이는 : "+cercle(a)+"입니다.");
    }
    public static double cercle(double a){
        double result;
        result = a * a * 3.14;
        return result;
    }
}