/**
 * hw8
 *
 * Write a program that goes through your file system and outputs a tree 
 * diagram of all of your directories in a file called dir_tree.txt. The 
 * methods in the File class will probably come in very handy. For a sample 
 * output, check out this webpage: http://www.computerhope.com/treehlp.htm 
 * Make sure you get the indentation right/tree branches right.
 * 
 * NOTE: GIVEN THAT YOUR ENTIRE DIRECTORY TREE WILL PROBABLY BE HUGE, FEEL 
 * FREE TO START FROM ANY FOLDER JUST AS LONG AS THAT FOLDER CONTAINS A 
 * BUNCH OF SUB FOLDERS AND A BUNCH OF SUB-SUB FOLDERS, AND FILES AT EACH 
 * LEVEL. THE LOGIC IS THE SAME.
 * 
 * @date	2018-5-2
 * @version	1.0
 * @author	Ruslan Pantaev
 */

import java.io.*;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirTree {

	public static String fixPathName(Path path) {
		String fileName = path.normalize().toString();
		// fileName = fileName.substring(fileName.lastIndexOf('/')+1);
		fileName = fileName.replaceAll(".*/", "");
		return fileName;
	}

	// ref https://docs.oracle.com/javase/tutorial/essential/io/walk.html
	
	public static class PrintFiles extends SimpleFileVisitor<Path> {
		public static int level;
		public PrintFiles() { this.level = 0; }

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attr) {
			this.level++;
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
			// System.out.print("|");
			if (attr.isRegularFile()) {
				for (int i = 0; i < this.level-1; i++) {
					System.out.format("|   ");
				}
				System.out.format("|___%s\n", fixPathName(file));
			}
			return CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
			this.level--;
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			System.out.println(exc);
			return CONTINUE;
		}
	}
	
	public static void main(String[] args) {
		Path startingDir = Paths.get("/Users/Ruslan/Documents/BC_Spring_18/java/cisc3150_Spring_2018-master/");
		System.out.println(startingDir);
		PrintFiles pf = new PrintFiles();
		try {
			Files.walkFileTree(startingDir, pf);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
