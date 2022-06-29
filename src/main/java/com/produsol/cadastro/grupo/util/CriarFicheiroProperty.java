package com.produsol.cadastro.grupo.util;

import com.google.gson.JsonParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class CriarFicheiroProperty 
{
	public static void main(String []args)
	{
		//String jsonString = "{'officeId':1,'clientId':10328,'resourceId':10328}";
		
		//WritePropertyFile(jsonString);
		//getPath();
	}
	
	
	public static void getPath()
	{
		 Path path2 = Paths.get("/src/main/jpdl/simple/database.properties");
	        System.out.println("\n[Path] : " + path2);
	        printPath(path2);
	}
	
	 static void printPath(Path path) {

	        System.out.printf("%-25s : %s%n", "path", path);
	        System.out.printf("%-25s : %s%n", "path.toAbsolutePath()",
	                                                path.toAbsolutePath());
	        System.out.printf("%-25s : %s%n", "path.getParent()", path.getParent());
	        System.out.printf("%-25s : %s%n", "path.getRoot()", path.getRoot());

	        try {

	            if (Files.notExists(path)) {
	                return;
	            }

	            // default, follow symbolic link
	            System.out.printf("%-25s : %s%n", "path.toRealPath()",
	                                                  path.toRealPath());
	            // no follow symbolic link
	            System.out.printf("%-25s : %s%n", "path.toRealPath(nofollow)",
	                path.toRealPath(LinkOption.NOFOLLOW_LINKS));

	            // alternative to check isSymbolicLink
	            /*if (Files.isSymbolicLink(path)) {
	                Path link = Files.readSymbolicLink(path);
	                System.out.println(link);
	            }*/

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }


	
	

	public void WritePropertyFile(String jsonString)
	{
	
		System.out.println("1");
		
		try(OutputStream output = new FileOutputStream("database.properties")){

			System.out.println("2");
			
			Properties prop = new Properties();
			
			System.out.println("2.2: "+ jsonString);
			
			com.google.gson.JsonObject data = new JsonParser()
					.parse(jsonString).getAsJsonObject();
			
			System.out.println("clientId =" + data.get("clientId"));
			
			//System.out.println(data);
			
			
				prop.setProperty("clientId",data.get("clientId").toString());
				
				prop.store(output, null);	
				
				System.out.println("4");

			}
		catch (IOException io){
			io.printStackTrace();

		}
		
	}

}
