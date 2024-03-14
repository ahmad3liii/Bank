package Bank;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Account {
    protected String id;
    protected String name;
    protected String password;
    protected double balance;
    public Account(){}
    public Account(String name,String  id,String password){
        this.name=name;
        this.id=id;
        this.password=password;
    }
    public Account(String name,String id,String password,double balance){
        this(name,id,password);
        this.balance=balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public boolean addBalance(double b) {
        if(b<0) return false;
        this.balance = balance+b;
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    //show your data
    public void show(){
        JOptionPane.showMessageDialog(null,"ID :"+id+"\t"+" Name :"+name+"\n"+"Balance :"+balance );
    }

    //withdraw form your account
    public boolean WithDraw(double b){
        if(b<0||b>balance*0.6)
            return false;
        balance=balance-b;
        return true;
    }
    public boolean equals (Object ob){
        if(this==null||ob==null) return false;
        if(this==ob) return true;
        if(this.getClass()!=ob.getClass()) return false;
        Account t=(Account) ob;
        return (name.equals(t.name)&&balance==t.balance&&id.equals(t.id)&&password.equals(t.password));
    }
}
class ATM extends Account {
    ArrayList<Account> atm;
    public ATM(){
        atm=new ArrayList<>();
    }
    public ATM(String name, String id, String password, double b) {
        super(name, id, password, b);
        atm=new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, password, balance);
    }

   //add account to atm
    public void addAccount (Account ... ob){
        for (var e:ob) {
            atm.add(e);
        }

    }

    //tran between accounts
   public boolean tran(String c,String z,double b) {
       if (cheek(c) && cheek(z)) {
           Account k = returnObj(c);
           Account l = returnObj(z);
           if ((k.balance != 0)) {
               if (b > 0 || b < k.balance * 0.6) {
                   k.balance -= b;
                   l.balance += b;
                   return true;
               } else return false;
           }else return false;

       }return false;
   }
    public boolean cheek (String ob){
        for (var e:atm) {
            if(ob.equals(e.id)) return true;
        }
        return false;
    }
    public Account returnObj (String ob){
        for (var e:atm) {
            if(ob.equals(e.id)) return e;
        }
        Account t=new Account("nothing","0","0",0);
        return t;
    }

    //show atm interface gui
    public void interfaceToATM() {
        String ID = JOptionPane.showInputDialog("enter your ID: ");
        int kk = 5;
        while (!cheek(ID)) {
            kk--;
            JOptionPane.showMessageDialog(null, "return enter your ID, U have " + kk + " chance before the program locked");

            if(kk==0){
                JOptionPane.showMessageDialog(null," your chance is over");
                break;}
            ID = JOptionPane.showInputDialog("enter your ID : ");
        }
        Account temp=returnObj(ID);
        while (ID.equals(temp.id)) {
            //Account temp=returnObj(ID);
            String pass = JOptionPane.showInputDialog("enter your pass: ");
            int k = 5;
            while (!pass.equals(temp.password)) {
                k--;
                JOptionPane.showMessageDialog(null, "return enter your pass, U have " + k + " chance before the program locked");

                if(k==0){
                    JOptionPane.showMessageDialog(null," your chance is over");
                    break;}
                pass = JOptionPane.showInputDialog("enter your pass: ");
            }
            if (pass.equals(temp.password)) {
                JOptionPane.showMessageDialog(null, " Welcome to your account");
                String p = JOptionPane.showInputDialog(null, "Enter the Operation's numbers which you want to use it :\n" +
                        "1- Show your Data \t 2- Add balance to your account\n" +
                        "3- Withdraw from your account \t 4- Show your hash\n" +
                        "5- Exit  \t 6- Trans between account");
                while (true) {
                    switch (p.charAt(0)) {
                        case '1':{
                            temp.show();
                            break;}
                        case '2':{
                            double bii = Double.parseDouble(JOptionPane.showInputDialog("enter amount balance to add :"));
                            temp.addBalance(bii);break;}
                        case '3':{
                            double wi = Double.parseDouble(JOptionPane.showInputDialog("enter amount balance to withdraw :"));
                            if(temp.WithDraw(wi))
                                JOptionPane.showMessageDialog(null, "This Operation is done");
                            else JOptionPane.showMessageDialog(null, "This Operation is not done");
                            break;}
                        case '4':{
                            JOptionPane.showMessageDialog(null, "Hash:"+temp.hashCode());
                            break;}
                        case '5':{
                            JOptionPane.showMessageDialog(null, "this program closed");
                            break;}
                        case '6':{
                                    String i=JOptionPane.showInputDialog("enter ID account for trans from your account: ");
                                    double bae = Double.parseDouble(JOptionPane.showInputDialog("enter amount balance :"));
                                    if (tran(ID,i,bae))
                                    JOptionPane.showMessageDialog(null, "Trans is complete .");
                                    else JOptionPane.showMessageDialog(null, "Trans is not complete.");
                                    break;
                        }
                        default: {
                            JOptionPane.showMessageDialog(null, "Sorry, don't have any Operation more");
                        }
                    }
                    if(p.equals("5")) break;
                    p = JOptionPane.showInputDialog(null, "Enter the Operation's numbers which you want to use it :\n" +
                            "1- Show your Data \t 2- Add balance to your account\n" +
                            "3- Withdraw from your account \t 4- Show your hash\n" +
                            "5- Exit   \t 6- Trans between account");
                }
            }String E = JOptionPane.showInputDialog("If U don't want complete enter '!' ,Else enter any else char  : ");
            if(E.equals("!")) break;
            ID = JOptionPane.showInputDialog("enter your ID: ");
            kk = 5;
            while (!cheek(ID)) {
                kk--;
                JOptionPane.showMessageDialog(null, "return enter your ID, U have " + kk + " chance before the program locked");

                if(kk==0){
                    JOptionPane.showMessageDialog(null," your chance is over");
                    break;}
                ID = JOptionPane.showInputDialog("enter your ID : ");
            }
            temp=returnObj(ID);
        }
    }
}


