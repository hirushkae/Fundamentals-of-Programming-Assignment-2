import java.io.File;
import java.util.Scanner;
import java.util.*;

public class Students_marks
{
    private ArrayList<Students_marks> list;
    private String fullname;
    private int id;
    private double a1, a2, a3;

    
    public Students_marks()
    {
        
    }

    public Students_marks(String firstname, String lastname, int id, double a1, double a2, double a3)
    {
        this.fullname = firstname+" "+lastname;
        this.id = id;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }
    
    public static void main(String[] args) throws Exception {
        Students_marks st = new Students_marks();
        st.Mainmenu();
    }
    
    public void Mainmenu() throws Exception {
        list = fileRead();
        Scanner op = new Scanner(System.in);
        System.out.println("Select from the menu"+
            "\n 1. Enter 1 to display student information and assignment marks"+
            "\n 2. Enter 2 to display total marks for all assignments"+
            "\n 3. Enter 3 to display the list of students with the total marks less than a certern threshold"+
            "\n 4. Enter 4 to display the 10 highest and the 10 lowest student marks"+
            "\n 5. Enter 5 to exit the main menu");
            
        int optionNum;
        optionNum = op.nextInt();
        
        switch(optionNum){
            case 1:
                System.out.println("Student details and marks");
                for(Students_marks l:list){
                    System.out.println(l.toString());
                }
                System.out.println("\n");
                Mainmenu();
                break;
                
            case 2:
                System.out.println("Student details, marks and total marks");
                for(Students_marks l:list){
                    System.out.println(l.toStringWithTotal());
                }
                System.out.println("\n");
                Mainmenu();
                break;
                
            case 3:
                System.out.println("Student details, marks and total marks for who less than a certern threshold");
                float th;
                th = op.nextInt();
                
                for(Students_marks l:list){
                    String s = l.toString_threshold(th);
                    if(s != null){
                    System.out.println(s);
                    }
                }
                System.out.println("\n");
                Mainmenu();
                break;
                
            case 4:
                System.out.println("Student details, marks and total marks");
                
                
                
                System.out.println("\n");
                Mainmenu();
                break;
                
            case 0:
                break;
        }
    }
    
    public static ArrayList<Students_marks> fileRead() throws Exception{
        ArrayList<Students_marks> list = new ArrayList<Students_marks>();
        Scanner file = new Scanner(new File("Students_marks.txt"));
        file.useDelimiter(",");
        String unit_name;
        
        while(file.hasNextLine()){
            String line = file.nextLine();
            String[] data;
            String initialLine;
            
            if(line.contains("Unit")){
                initialLine = line;
            } else if(!line.contains("Last Name")){
                data = line.split(",");
                
                if(data.length == 4){
                    list.add(new Students_marks(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),0.0,0.0));
                }else if(data.length == 5){
                    list.add(new Students_marks(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]),0.0));
                }else if(data.length == 3){
                    list.add(new Students_marks(data[0],data[1],Integer.parseInt(data[2]),0.0,0.0,0.0));
                }else{
                    String a1 = data[3];
                    String a2 = data[4];
                    String a3 = data[5];
                    
                    if(data[3].isEmpty()){
                        a1 = "0.0";
                    }
                    if(data[4].isEmpty()){
                        a2 = "0.0";
                    }
                    if(data[5].isEmpty()){
                        a3  = "0.0";
                    }
                    list.add(new Students_marks(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(a1),Double.parseDouble(a2),Double.parseDouble(a3)));
                }
            }
        }
        file.close();
        return list;
    }
    
    public String toString(){
        return String.format("id:%d, FullName: %s, A1:%.2f,A3:%.2f,A3:%.2f,",
        id,fullname,a1,a2,a3);
    }
    
    public String toStringWithTotal(){
        double totalMarks = CalculateTotal();
        return String.format("id:%d, FullName: %s, A1:%.2f,A3:%.2f,A3:%.2f,Total:%.2f",
        id,fullname,a1,a2,a3,totalMarks);
    }
    
    public double CalculateTotal(){
        double total = a1 + a2 + a3;
        return total;
    }
    
    public String toString_threshold(float th){
        double totalMarks = CalculateTotal();
        if (totalMarks<th){
        return String.format("id:%d, FullName: %s, A1:%.2f,A3:%.2f,A3:%.2f,Total:%.2f",
        id,fullname,a1,a2,a3,totalMarks);
        } else {
        return null;
        } 
    }
    
}


