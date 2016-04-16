/*Print out a crist tree with the size of the number you put*/
import java.util.Scanner;

public class tree {
  public static void main(String args[]) {
    tree Christ = new tree();
    System.out.println("Please print the size of your Christ tree..");
    Scanner in = new Scanner(System.in);
    int a = in.nextInt();
    Christ.printTree(a);
  }
  
  public void printTree(int temp) {
    int i, j, k;
    int n=temp;
    for(i=1; i<=n; i++) {
      for(k=n-i; k>=0; k--) {
        System.out.print(" ");
      }  

      for(j=1; j<=2*i-1; j++) {
        if(i%2==1) {
          if(j%2==1) {
            System.out.print("*");
          } else {
            System.out.print("$");
          }
        } else {
          if(j%2==1) {
            System.out.print("$");
          } else {
            System.out.print("*");
          }
        }
      } 
      System.out.println("");
    }

    for(i=1; i<=n/2; i++) {
      for(j=1; j<=n; j++) {
        System.out.print(" ");
      }
      System.out.println("||");
    }  
  }
}