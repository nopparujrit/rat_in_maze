
package Project1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;


public class RatInMaze {
    private static final String FILE_NAME = null;
    private static final String PATH = "src/main/Java/Project1/";
    
    public static void main(String[] args) {
        Scanner scanner1 = null;
        Scanner scanner2 = null;
        Scanner scanner3 = null;
        String fileName = FILE_NAME;
        boolean fileFound = false;
        int row=0;
        int col=0;
        int rowrat=0;
        int colrat=0;
        int[][] B ;
        int[][] temp_array ;
        Arraytmatrix data =null;
        int foodcount=0;
        Food[] listfood =null;
        System.out.printf("==========================HOW TO PLAY==========================\n" );
        System.out.printf("--Key Inputs--\n" );
        System.out.printf("--Can be lowercase letter or uppercase letter\n" );
        System.out.printf("U = move rat up 1 row(from current position)\n" );
        System.out.printf("D = move rat down 1 row(from current position)\n" );
        System.out.printf("L = move rat left 1 column(from current position)\n" );
        System.out.printf("L = move rat right 1 column(from current position)\n" );
        System.out.printf("A = use auto mode to find all food in maze\n" );
        System.out.printf("E = exit\n" );
        System.out.printf("O = restart\n" );
        System.out.printf("===============================================================\n" );
        while (!fileFound) {
            try {
                if(fileName == null){
                System.out.printf("Please enter a new file name: ", fileName);
                Scanner inputScanner = new Scanner(System.in);
                fileName = inputScanner.nextLine();
                }
                
                scanner1 = new Scanner(new File(PATH + fileName));//create 3 scan to
                scanner2 = new Scanner(new File(PATH + fileName));
                scanner3 = new Scanner(new File(PATH + fileName));
                fileFound = true;
                
                while(scanner1.hasNextLine()){//1 scan row for init.
                String line = scanner1.nextLine();
                //System.out.printf("%s\n",line);
                row++;
                }
                //System.out.printf("row %d \n",row);
                
                while(scanner2.hasNext()){//2 scan all character for columnfor init.
                char name = scanner2.next().charAt(0);
                //System.out.printf("%s",name); 
                col++;
                if(name=='F'){foodcount++;}//for init.
                }
                col = col/row;//cal. column
                //System.out.printf("col %d \n",col);
                B = new int[row][col];
                temp_array = new int[row][col];
                listfood = new Food[foodcount];
                int for_addrow=0;
                int for_addcol=0;
                int for_addfood=0;
                
                while(scanner3.hasNext()){
                char name3 = scanner3.next().charAt(0);
                
                if(name3=='F'){
                    listfood[for_addfood] = new Food(for_addrow,for_addcol);for_addfood++;
                    //listfood[for_addfood].add(for_addcol);
                    //food[for_addrow][for_addcol]=1;
                    name3='3';}//location of f and f is 3
                if(name3=='R'){
                    rowrat = for_addrow;
                    colrat = for_addcol;
                    //rat[for_addrow][for_addcol]=1;
                    name3='5';}//location of r and f is 5
              
                int a=Character.getNumericValue(name3);  // char to int
                //System.out.printf("add col %d, row %d \n",for_addcol,for_addrow);
                
                B[for_addrow][for_addcol] =a;
                
                temp_array[for_addrow][for_addcol] =a;
                for_addcol++;
                if(for_addcol==col){for_addrow++; for_addcol=0;}
                }
                
                data = new Arraytmatrix(B,listfood,temp_array,rowrat,colrat);//create Arraytmatrix
                data.printMat(); 
                
                
            } catch (FileNotFoundException e) {
                System.out.printf("File %s is not found. Please enter a new file name: ", fileName);
                Scanner inputScanner = new Scanner(System.in);
                fileName = inputScanner.nextLine();
            }
        }
        if (scanner1 != null) {
            scanner1.close();
        }
        if (scanner2 != null) {
            scanner2.close();
        }
        if (scanner3 != null) {
            scanner3.close();
        }
        //System.out.println("R is in " + rows.indexOf("F"));
        //System.out.println("Implicit toString = " + rows);
        //System.out.printf("col %d, row %d \n",col,row);
        boolean exit=false; //for check id alfa is not  (u d l r a)
        
        while(!exit && !data.getend()){
            
        System.out.printf("\nUser input >>( U D L R ) key to move\n\t   >>(A) key to auto mode\n\t   >>(E) key to exit\n\t   >>(O) key to restart\n");
        Scanner myObj = new Scanner(System.in);
        String Usip = myObj.nextLine();
        boolean check_move;
        switch(Usip.toLowerCase()){
            case "u":
                data.up(rowrat,colrat,listfood);check_move = data.getmove();
                if(check_move==true)data.printMat(); 
                else{System.out.printf("Can't move\n");}
                rowrat = data.getrow();
                    break;
            case "d":
                data.down(rowrat,colrat,listfood);check_move = data.getmove();
                if(check_move==true)data.printMat(); 
                else{System.out.printf("Can't move\n");}
                rowrat = data.getrow();
                    break;
            case "l":
                data.left(rowrat,colrat,listfood);check_move = data.getmove();
                if(check_move==true)data.printMat(); 
                else{System.out.printf("Can't move\n");}
                colrat = data.getcol();
                    break;
            case "r":
               data.right(rowrat,colrat,listfood);check_move = data.getmove(); 
               if(check_move==true)data.printMat(); 
               else{System.out.printf("Can't move\n");}
               colrat = data.getcol();
                    break;
            case "a":
               data.auto(rowrat,colrat,listfood);
                    break;        
            case "e":
               exit=true;
               //System.out.printf("Exit Game\n");
               System.exit(0);
                    break; 
            case "o":
               
                RatInMaze.main(args);
                //exit_();
                    break;         
            default: System.out.printf("Incorrect input!!\n");
            //System.out.printf("User input >> Enter move (( U D L R ))\n\t      A to auto mode\n\t      E key to exit\n");       
        }
       }
        
        String ui=null;
        do{
        if(data.getend()){System.out.printf("Game End. Do You want to play new game?(Y/N)\n");}
        Scanner myObj = new Scanner(System.in);
        String Usip = myObj.nextLine();
        ui=Usip.toLowerCase();
        //System.out.printf("user %s\n",ui);
        }
        while(!ui.equals("y") && !ui.equals("n"));{
        if(ui.equals("y"))RatInMaze.main(args);
        else System.out.printf("Exit Game\n");
        }
    }
    
}

class Arraytmatrix {
    
    private int[][] myarray;
    private int[][] temp_array;
    private int ratrow;
    private int ratcol;
    private int tratrow;
    private int tratcol;
    private boolean move=false;
    private boolean END=false;
    private boolean sol=false;
    private int eatenfood=0;
    private boolean found=false;
    int[][] tarray =null;
    private Food[] myfood;
    
    public Arraytmatrix(int[][] arg,Food[] f,int[][] t,int rowr,int colr){
        myarray = arg;
        myfood = f;
        temp_array = t;
        ratrow =rowr;
        ratcol=colr;
    }
   
    public void auto(int rowr,int colr,Food[] f){
        ArrayDeque<One> dq = new ArrayDeque<One>();
        
        ArrayDeque<One> dq_mark = new ArrayDeque<One>();
        
        One temp_dq =null;//for deque
        One temp_mark =null;//for mark >1 path
        ratrow =rowr;
        ratcol=colr;
        tratrow = ratrow;
        tratcol = ratcol;
       
        
                tarray = new int[myarray.length][myarray[1].length];
                for (int x=0; x < myarray.length; x++) 
                {
                for (int j=0; j < myarray[1].length; j++ )  
		tarray[x][j] = myarray[x][j];
                }
                temp_array = tarray;
        
        
        for(int i =0;i<f.length;i++){
            if(eatenfood==f.length){END=true;break;}
                        
            System.out.printf("=====================Finding Food %d=====================\n",eatenfood+1);
            found=false;
            int mark=0;
            dq.addFirst(new One(tratrow,tratcol,5,"start")); //add fist (R)position
            
        while(found==false){
        int path=0;
        if(ratrow-1<0){move=false;}
        else{
        if( myarray[ratrow-1][ratcol] == 1){move=true;}
        else if( myarray[ratrow-1][ratcol] == 3){ move=true;}
        else{move=false;}
        }
        if(move==true)path++;//System.out.printf("path1 %d\n",path);
        
        if(ratrow+1>=myarray.length){move=false;}
        else{
        if( myarray[ratrow+1][ratcol] == 1){move=true;}
        else if( myarray[ratrow+1][ratcol] == 3){move=true;}
        else{move=false;}
        }
        if(move==true)path++;//System.out.printf("path2 %d\n",path);
        
        if(ratcol-1<0){move=false;}
        else{
        if( myarray[ratrow][ratcol-1] == 1){move=true;}
        else if( myarray[ratrow][ratcol-1] == 3){move=true;}
        else{move=false;}
        }
        if(move==true)path++;//System.out.printf("path3 %d\n",path);
        
        
        if(ratcol+1>=myarray[1].length){move=false;}
        else{
        if( myarray[ratrow][ratcol+1] == 1){move=true;}
        else if( myarray[ratrow][ratcol+1] == 3){move=true;}
        else{move=false;}
        }
        if(move==true)path++;
        
        
        if(path>1){
        dq_mark.add(new One(ratrow,ratcol,1,"x"));
       
        mark++;
        
        }
      
        left(ratrow,ratcol,f);if(move==false){
           up(ratrow,ratcol,f);if(move==false){
               right(ratrow,ratcol,f);if(move==false){
                    down(ratrow,ratcol,f);if(move==false){
                        myarray[ratrow][ratcol] =0;// back set R to zero
                        //back here
                        
                       temp_mark = dq_mark.peekLast();
                        if(temp_mark != null )//deque mark condition
                        {   
                            ratrow = temp_mark.getfrow();
                            ratcol = temp_mark.getfcol();
                            myarray[ratrow][ratcol] =5;
                        }
                        temp_mark = dq_mark.pollLast();
                        mark--;
                        if(mark<0){System.out.printf("Food not found(No solution)\n");
                        eatenfood=f.length;
                        END=true;break;
                        }
                        
                            do {
                                temp_dq = dq.peekLast();
                                if(!temp_dq.equals(temp_mark)){temp_dq = dq.pollLast();}
                            
                            
                               } while (temp_dq != null && !temp_dq.equals(temp_mark));
                            
                            int d = 0;
                            for (One elem : dq_mark) {
                                System.out.println("index "+ d++ + " "+ elem.getfrow()+","+elem.getfcol());
                            }
                        
                    }
                    else{myarray[ratrow-1][ratcol] =0;dq.add(new One(ratrow,ratcol,1,"down"));/*System.out.printf("add row %d col %d\n",ratrow,ratcol);*/}
               }
               else{myarray[ratrow][ratcol-1] =0;dq.add(new One(ratrow,ratcol,1,"right"));/*System.out.printf("add row %d col %d\n",ratrow,ratcol);*/}
           }
           else{myarray[ratrow+1][ratcol] =0;dq.add(new One(ratrow,ratcol,1,"up"));/*System.out.printf("add row %d col %d\n",ratrow,ratcol);*/}
        }
        else{myarray[ratrow][ratcol+1] =0;dq.add(new One(ratrow,ratcol,1,"left"));/*System.out.printf("add row %d col %d\n",ratrow,ratcol);*/}
        
        printMat();
        }
      
        
        while ( ( temp_mark = dq_mark.pollLast()) != null ) 
             
        temp_dq = dq.peekLast();//TRace from deque
        
        
            if(found==true){
                temp_dq=dq.pollLast();
                dq.add(new One(temp_dq.getfrow(),temp_dq.getfcol(),3,temp_dq.getdirect()));
                
                temp_array[temp_dq.getfrow()][temp_dq.getfcol()] = myarray[ratrow][ratcol];//to print put 1 back
                temp_array[tratrow][tratcol] = 1;
                tratrow =ratrow;
                tratcol =ratcol;
                //
                myarray = temp_array;
                
                tarray = new int[myarray.length][myarray[1].length];
                for (int x=0; x < myarray.length; x++) 
                {
                for (int j=0; j < myarray[i].length; j++ )  
		tarray[x][j] = myarray[x][j];
                }
                
                temp_array = tarray;
                
                printMattemp();
                
                System.out.printf("\n---------------------PATH FROM START--------------------\n");
            while ( (temp_dq = dq.pollFirst()) != null ) {
                String type=null;
                if(temp_dq.gettype()==3)type="F";
                else if(temp_dq.gettype()==5)type="R";
                else type="1";
        System.out.printf("%6s -> (row %2d ,col %2d,%2s)\n",temp_dq.getdirect(),temp_dq.getfrow(),temp_dq.getfcol(),type);
            }
            System.out.printf("--------------------------------------------------------\n");
            System.out.printf("\n\n");
        
            }
           
        }
    }
    public int[][] up(int rowr,int colr,Food[] f){
        if(rowr-1<0){
            move=false;
        }
        else{
        
        if( myarray[rowr-1][colr] == 1){
            myarray[rowr][colr] = 1;
            myarray[rowr-1][colr] = 5;
            ratrow = rowr-1;
            
            move=true;
        }
        else if( myarray[rowr-1][colr] == 3){
            myarray[rowr][colr] = 1;
            myarray[rowr-1][colr] = 5;
            ratrow = rowr-1;
            
            move=true;
            
            for(int i =0;i<f.length;i++){
                if(f[i].getfrow()==ratrow && f[i].getfcol()==ratcol){
                    f[i].eat();eatenfood++;
                    found=true;
                    System.out.printf("++++++++Food is eaten++++++++\n");
                    if(eatenfood==f.length){END=true;}
                }
            }
        }
        else if( myarray[rowr-1][colr] == 0){
             move=false;
        }
        else{
             move=false;
        }
    }
        return myarray;
    }
    public int[][] down(int rowr,int colr,Food[] f){
        if(rowr+1>=myarray.length){
            move=false;
        }
        else{
        if( myarray[rowr+1][colr] == 1){
            myarray[rowr][colr] = 1;
            myarray[rowr+1][colr] = 5;
            ratrow = rowr+1;
            
            move=true;
        }
        else if( myarray[rowr+1][colr] == 3){
            myarray[rowr][colr] = 1;
            myarray[rowr+1][colr] = 5;
            ratrow = rowr+1;
           
            move=true;
            for(int i =0;i<f.length;i++){
                if(f[i].getfrow()==ratrow && f[i].getfcol()==ratcol){
                    f[i].eat();eatenfood++;
                    found=true;
                    System.out.printf("++++++++Food is eaten++++++++\n");
                    if(eatenfood==f.length){END=true;}
                }
            }
        }
        else if( myarray[rowr+1][colr] == 0){
             move=false;
        }
        else{
             move=false;
        }
        }
        return myarray;
    }
    public int[][] left(int rowr,int colr,Food[] f){
        if(colr-1<0){
            move=false;
        }
        else {
        if( myarray[rowr][colr-1] == 1){
            myarray[rowr][colr] = 1;
            myarray[rowr][colr-1] = 5;
            ratcol = colr-1;
            
            move=true;
        }
        else if( myarray[rowr][colr-1] == 3){
            myarray[rowr][colr] = 1;
            myarray[rowr][colr-1] = 5;
            ratcol = colr-1;
            
            move=true;
            for(int i =0;i<f.length;i++){
                if(f[i].getfrow()==ratrow && f[i].getfcol()==ratcol){
                    f[i].eat();eatenfood++;
                    found=true;
                    System.out.printf("++++++++Food is eaten++++++++\n");
                    if(eatenfood==f.length){END=true;}
                }
            }
        }
        else if( myarray[rowr][colr-1] == 0){
             move=false;
        }
        else {
            move=false;
        }
        
        }
        
        return myarray;
    }
    public int[][] right(int rowr,int colr,Food[] f){
        if(colr+1>=myarray[0].length){
            move=false;
        }
        else {
        if( myarray[rowr][colr+1] == 1){
            myarray[rowr][colr] = 1;
            myarray[rowr][colr+1] = 5;
            ratcol = colr+1;
           
            move=true;
        }
        else if( myarray[rowr][colr+1] == 3){
            myarray[rowr][colr] = 1;
            myarray[rowr][colr+1] = 5;
            ratcol = colr+1;
           
            move=true;
            for(int i =0;i<f.length;i++){
                if(f[i].getfrow()==ratrow && f[i].getfcol()==ratcol){
                    f[i].eat();eatenfood++;
                    found=true;
                    System.out.printf("++++++++Food is eaten++++++++\n");
                    if(eatenfood==f.length){END=true;}
                }
            }
        }
        else if( myarray[rowr][colr+1] == 0){
             move=false;
        }
        else {
            move=false;
        }
        
        
        }
        
        return myarray;
    }
    public void printMat()  
    {
       
        System.out.printf("        ");
	for (int x=0; x < myarray[0].length; x++) {
            //if(i==0)
             System.out.printf("col_%-2d ",x);
        }
        System.out.printf("\n");
        
	for (int i=0; i < myarray.length; i++) 
	{
            System.out.printf("row_%-2d",i);
            for (int j=0; j < myarray[i].length; j++ )  
		{
                if(myarray[i][j]==5)System.out.printf("%6s ", 'R');
                else if (myarray[i][j]==3)System.out.printf("%6s ", 'F');
                else System.out.printf("%6d ", myarray[i][j]);
                }
            System.out.printf("\n");
        }
    }
    public void printMattemp()  
    {
	System.out.printf("        ");
	for (int x=0; x < temp_array[0].length; x++) {
            //if(i==0)
             System.out.printf("col_%-2d ",x);
        }
        System.out.printf("\n");
        
	for (int i=0; i < temp_array.length; i++) 
	{
            System.out.printf("row_%-2d",i);
            for (int j=0; j < temp_array[i].length; j++ )
            {
                if(temp_array[i][j]==5)System.out.printf("%6s ", 'R');
                else if (temp_array[i][j]==3)System.out.printf("%6s ", 'F');
                else System.out.printf("%6d ", temp_array[i][j]);
            }
            System.out.printf("\n");
        }
    }
    public int getrow(){
        return ratrow;
    }
    public boolean getend(){
        return END;
    }
    public int getcol(){
        return ratcol;
    }
    public boolean getmove(){
        return move;
    }
} 

class Food {
    private int col,row;
    private boolean is_alive=true;
    public Food(int r, int c){
        row = r; col=c;
    }
    public int getfrow(){
        return row;
    }
    public int getfcol(){
        return col;
    }
    public void setcol(int x){
        col =x;
    }
    public void setrow(int x){
        row =x;
    }
    public void eat(){
        is_alive = false;
    }
    
}
 
class One {
    private int col,row;
    private String direc;
    private int type;
    public One(int r, int c,int t,String d){
        row = r; col=c;
        type = t; direc=d;
    }
    public int gettype(){
        return type;
    }
    public String getdirect(){
        return direc;
    }
    public int getfrow(){
        return row;
    }
    public int getfcol(){
        return col;
    }
    public void setcol(int x){
        col =x;
    }
    public void setrow(int x){
        row =x;
    }
    public boolean equals(Object o)
    {
	One other = (One)o;
	// equality by row and column
        if(this.getfrow()==other.getfrow()&& this.getfcol()==other.getfcol()) return true;
        else return false;
	
    }
    
} 
