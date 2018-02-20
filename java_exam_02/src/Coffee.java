public class Coffee {

    boolean isInmilk;
    boolean isIncream;
    Coffee(boolean isInmilk,boolean isIncream){
        this.isInmilk = isInmilk;
        this.isIncream = isIncream;
    }
    Coffee(boolean isIncream){
        this.isIncream = isIncream;
        this.isInmilk = false;
    }
    public void showMenu(){
        if(isIncream){
            System.out.println("크림이 들어있음.");
        }
        else{
            System.out.println("크림이 안 들어있음.");
        }
        if(isInmilk){
            System.out.println("우유가 들어있음.");
        }
        else{
            System.out.println("우유가 안 들어있음.");
        }

    }

}
