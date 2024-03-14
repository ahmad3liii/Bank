package Bank;
public class Main {
    public static void main(String [] args){
        ATM atm=new ATM();
        Account a1=new Account("Ahmad Ali","254","4689",0);
        Account a2=new Account("Hassan","354","465",0);
        Account a3=new Account("Haider","54","035",0);
        Account a4=new Account("Edoardo","154","235",0);
        Account a5=new Account("Ammar","180","244",0);
        Account a6=new Account("Melena","174","267",0);
        Account [] a={a1,a2,a3,a4,a5,a6};
        atm.addAccount(a);
        atm.interfaceToATM();
    }
}
