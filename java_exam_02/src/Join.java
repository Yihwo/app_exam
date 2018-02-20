import java.util.Scanner;

public class Join {
    Scanner key = new Scanner(System.in);

    public void passwordLength(String pw1){

        while(pw1.length()<8) {
            System.out.println("8글자 이상 입력하세요.");
            pw1 = key.nextLine();
        }
    }
    public void passwordOverlap(String pw1,String pw2){

        while(pw1.compareTo(pw2)!=0) {
            System.out.println("비밀번호를 다르게 입력하였습니다.");
            System.out.println("다시 입력해 주세요.");
            pw2 = key.nextLine();
        }
        System.out.println("입력 내용이 같습니다.");
    }
}
