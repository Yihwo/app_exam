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
        int n1 =7, n2 = 3;
        int result = n1 + n2;
        System.out.println("덧셈 결과 : " + result);
        result = n1 - n2;
        System.out.println("뺄셈 결과 : " + result);
        System.out.println("곱셈 결과 : " + n1*n2);
        System.out.println("나눗셈 결과 : " + n1/n2);
        System.out.println("나머지 결과 : " + n1%n2);

        int num01, num02, num03;
        num01 = 33; num02 = 70; num03 = 90;
        result = num01+num02+num03;
        System.out.println("합계 : " + result);
        System.out.println("평균 : " + result/3);

        boolean resultB;
        resultB = num01<num02;
        System.out.println("결과 : "+ resultB);
        resultB = num01>num02;
        System.out.println("결과 : "+ resultB);
        resultB = num01<=num02;
        System.out.println("결과 : "+ resultB);
        resultB = num01>=num02;
        System.out.println("결과 : "+ resultB);
        resultB = num01==num02;
        System.out.println("결과 : "+ resultB);
        resultB = num01!=num02;
        System.out.println("결과 : "+ resultB);
        if(num01>num02){
            System.out.println("큰 값은 "+num01+"입니다.");
        }
        else{
            System.out.println("큰 값은 "+num02+"입니다.");
        }
        if(num01>0){
            System.out.println(num01+"은 0보다 크다.");
        }
        if((num01%2)==0){
            System.out.println(num01+"은 짝수");
        }
        else{
            System.out.println(num01+"은 홀수");
        }
        if(num02>=90) {
            System.out.println("성적은 수 입니다.");
        }
        else if(num02>=80){
            System.out.println("성적은 우 입니다.");
        }
        else if(num02>=70){
            System.out.println("성적은 미 입니다.");
        }
        else if(num02>=60){
            System.out.println("성적은 양 입니다.");
        }
        else{
            System.out.println("성적은 가 입니다.");
        }

    }
}
