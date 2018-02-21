import java.util.Scanner;

public class Exam_03 {
    public static void main(String[] args) {

        BusinessMan bMan = new BusinessMan("yihwo","green","student");
        bMan.tellYourInfo();
        Man man = new Man("yihwo");
        man.setName("leho");//Man 함수에서 private로 선언된 name의 값을 바꿈
        System.out.println(man.getName());//바뀐 name값을 불러옴
//        Scanner key = new Scanner(System.in);
//
//        try {
//            System.out.println("당신의 나이는 : "+readAge()+"입니다.");
//        }catch(AgeInputException e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        DevideNum();
//        int i=0;
//        try {
//
//            System.out.println("피제수 입력 : ");
//            int num1 = key.nextInt();
//            System.out.println("제수 입력 : ");
//            int num2 = key.nextInt();
//            System.out.println("연산결과를 저장할 배열의 인덱스 입력 : ");
//            int idx = key.nextInt();
//            double[] arr = new double[10];
//            arr[idx] = num1/num2;
//            System.out.println("나눗셈의 결과는 "+ arr[idx]);
//            System.out.println("저장된 위치의 인덱스는 "+idx);
//        }catch(ArithmeticException e){
//            System.out.println("제수는 0이 될 수 없습니다.");
//            i-=1;
////            continue;
//        }catch(ArrayIndexOutOfBoundsException e){
//            System.out.println("유효하지 않은 인덱스 값입니다.");
//            i-=1;
////            continue;
//        }
    }
    public static double DevideNum(){
        double result = -1;
        try{
            int i=3,j=2;
            result = i/j;
            System.out.println(result);
            return result;
        }catch(Exception e){    //에러 발생시 실행되는 코드 Exception 은 에러의 종류가 들어있음

            System.out.println(e.getMessage());//어떤 에러가 발생했는지 알기위함
            System.out.println("에러발생");
            e.printStackTrace();//에러가 발생하고 있는 원인과 위치를 알려줌
            return result;
        }finally {//위에서 return이 되어도 finally부분은 실행된다.
            System.out.println("finally 부분 실행");
        }
    }
    public static int readAge() throws AgeInputException{ //catch문에서 어느 예외처리문으로 보낼지 결정
        int age;
        Scanner key = new Scanner(System.in);
        age = key.nextInt();
        if (age<0){
            AgeInputException ageInputException = new AgeInputException();
            throw ageInputException;//에러발생시 예외처리를 실행
        }
        return age;
    }

}
