public class Man {
    private String name;
    public Man(String name){
        this.name = name;
    }
    public void tellYourName(){
        System.out.println("my name is "+name);
    }
    public void setName(String name){//private으로 선언된 name의 값을 다른 클래스에서 변경 할 수 있도록
        this.name = name;
    }
    public String getName(){//private으로 선언된 name의 값을 다른 클래스에서 불러올 수 있도록
        return name;
    }
}
