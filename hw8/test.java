// geeksforgeeks solution for ref

import java.io.File;

public class test 
{
    static void RecursivePrint(File[] arr,int index,int level) {
        // check base case
        if (index == arr.length)
            return;
        for (int i = 0; i < level; i++)
            System.out.print("|    ");
        System.out.print("|");
        if (arr[index].isFile()) {
            System.out.println("____" + arr[index].getName());
        }
        else if (arr[index].isDirectory()) {
            System.out.println("____" + arr[index].getName());
            // go into subdirs
            RecursivePrint(arr[index].listFiles(), 0, level + 1);
        }
        RecursivePrint(arr,++index, level);
    }

    public static void main(String[] args)
    {
        String maindirpath = "/Users/Ruslan/Documents/BC_Spring_18/java/cisc3150_Spring_2018-master/";
        File maindir = new File(maindirpath);
        if (maindir.exists() && maindir.isDirectory()) {
            File arr[] = maindir.listFiles();
            System.out.println(maindir);
            RecursivePrint(arr,0,0); 
        }
    }
}
