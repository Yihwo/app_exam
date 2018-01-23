import java.util.Scanner;

public class Exam {
    public static void main(String[] args){
        /*int num1;
        num1=10;
        int num2 = 20;
        int num3 = num1+num2;
        System.out.println(num1+"+"+num2+"="+num3);
        char text;
        text='a';
        System.out.println(text);
        double a = 0.12;
        System.out.println(a);
        */
//        int n1 =7, n2 = 3;
//        int result = n1 + n2;
//        System.out.println("덧셈 결과 : " + result);
//        result = n1 - n2;
//        System.out.println("뺄셈 결과 : " + result);
//        System.out.println("곱셈 결과 : " + n1*n2);
//        System.out.println("나눗셈 결과 : " + n1/n2);
//        System.out.println("나머지 결과 : " + n1%n2);
//
//        int num01, num02, num03;
//        num01 = 33; num02 = 70; num03 = 90;
//        result = num01+num02+num03;
//        System.out.println("합계 : " + result);
//        System.out.println("평균 : " + result/3);
//
//        boolean resultB;
//        resultB = num01<num02;
//        System.out.println("결과 : "+ resultB);
//        resultB = num01>num02;
//        System.out.println("결과 : "+ resultB);
//        resultB = num01<=num02;
//        System.out.println("결과 : "+ resultB);
//        resultB = num01>=num02;
//        System.out.println("결과 : "+ resultB);
//        resultB = num01==num02;
//        System.out.println("결과 : "+ resultB);
//        resultB = num01!=num02;
//        System.out.println("결과 : "+ resultB);
//        if(num01>num02){
//            System.out.println("큰 값은 "+num01+"입니다.");
//        }
//        else{
//            System.out.println("큰 값은 "+num02+"입니다.");
//        }
//        if(num01>0){
//            System.out.println(num01+"은 0보다 크다.");
//        }
//        if((num01%2)==0){
//            System.out.println(num01+"은 짝수");
//        }
//        else{
//            System.out.println(num01+"은 홀수");
//        }
//        if(num02>=90) {
//            System.out.println("성적은 수 입니다.");
//        }
//        else if(num02>=80){
//            System.out.println("성적은 우 입니다.");
//        }
//        else if(num02>=70){
//            System.out.println("성적은 미 입니다.");
//        }
//        else if(num02>=60){
//            System.out.println("성적은 양 입니다.");
//        }
//        else{
//            System.out.println("성적은 가 입니다.");
//        }
//        String str ="쥐";
//        i=5;
//        j=0;
//        switch(str){
//            case "강아지":
//                System.out.println("Dog");
//                break;
//            case "고양이":
//                System.out.println("Cat");
//                break;
//            case "토끼":
//                System.out.println("Rabbit");
//                break;
//            case "쥐":
//                System.out.println("Mouse");
//                break;
//            default:
//                System.out.println("Sorry, I don't know.");
//                break;
//        }
//        for (n=1;n<=i;n++){
//            for (m=0;m<i;m++){
//                if(m<i-n){
//                    System.out.print(" ");
//                }
//                else
//                    System.out.print("*");
//            }
//            System.out.println("");
//        }
//        for(n=1;n<=100;n++){
//            j=n+j;
//        }
//        System.out.println(j);
//        int n,m;
//        m=2;
//        n=1;
//        while(m <= 9) {
//            while (n <= 12) {
//                System.out.println(m+"*" + n + "=" + m * n);
//                n++;
//            }
//            n=1;
//            m=m+1;
//        }
//        Scanner kb= new Scanner(System.in); //키보드 입력 기능 활성화
//
//        String num = kb.nextLine(); //실제 입력을 받는 명령어
//        System.out.println(num); //String 문자 열

//        Scanner kb= new Scanner(System.in);
//        System.out.print("당신의 이름은?");
//        String name = kb.nextLine();
//        System.out.println("안녕하세요 "+name+"님");
//        System.out.println("당신은 스파게티를 좋아하는게 맞나요?");
//        boolean isTrue = kb.nextBoolean();
//        if(isTrue == true){
//            System.out. println("오~ 좋아하는군요!");
//        }
//        else{
//            System.out. println("이런 아니었군요.");
//        }
//        System.out. println("당신과 동생의 키는 어떻게 되나요?");
//        double num1 = kb.nextDouble();
//        double num2 = kb.nextDouble();
//        double diff = num1-num2;
//        if(diff>0){
//            System.out. println("당신이 "+diff+"만큼 크군요");
//        }
//        else if(diff==0){
//            System.out. println("당신과 동생의 키가 같군요.");
//        }
//        else{
//            System.out. println("당신이"+(-diff)+"만큼 작군요!");
//        }
//        int num1=0;
//        do{
//            System.out.println(num1);
//            num1++;
//        }while(num1<0);
//
        Scanner kb = new Scanner(System.in);
        int a= kb.nextInt();
        int b;
        int res=0;
        for(b=1;b<=10;b++){
            res = a*b;
            System.out.print(res+ " ");
        }

    }
}
