package skinDetector;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;






public class SkinDetection {
	
	int imageFileNumber = 1;
	int[][][] skinCount = new int[256][256][256];
	int[][][] nonSkinCount = new int[256][256][256];
	double pbOfSkin = 0;
	double pbOfNonSkin = 0,totalNonSkin = 0,totalSkin = 0,totalPixel = 0;
	String bmpP=null,jpgP=null;
	String picName = null;

	public static void main(String[] args) throws IOException {
		
		
		
		
		
		
		
		
		
		
		//test starts from here
		/*
		
        String bmpP2=null,jpgP2=null,picName2=null;
		
		File folder2 = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/test4/");
		
        File[] listOfFiles2 = folder2.listFiles();
        String sub2=null; 
        
        for(File file : listOfFiles2){
        	
        	String fName2=null;
			if (file.isFile()) {
                //System.out.println(file.getName());
				fName2=file.getName();
				//System.out.println(fName2);
				//String[] arrOfStr = fName.split(".",2);
            	bmpP2=fName2;
            	sub2 = fName2.substring(0,4);
            	jpgP2=sub2 + ".jpg";
            	                
            
			
			File f2 = new File("E:/SkinDetectionFinal/SkinDetection/trainData4.txt");
			Scanner sc = new Scanner(f2);
			
			for(int i = 0; i < 256; i++)
			{
				for(int j = 0; j < 256; j++)
				{
					for(int k = 0; k < 256; k++){
						skinCount[i][j][k] = sc.nextInt();
						
					}
				}
			}
			for(int i = 0; i < 256; i++)
			{
				for(int j = 0; j < 256; j++)
				{
					for(int k = 0; k < 256; k++)
						nonSkinCount[i][j][k] = sc.nextInt();
				}
			}
			
			totalPixel = sc.nextInt();
			totalSkin = sc.nextInt();
			totalNonSkin = sc.nextInt();
			
			sc.close();
			
			
			
			File testImage = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/test4/"+fName2);
			
			//System.out.println(fName2);
			BufferedImage testImg = null;

			try {
				testImg = ImageIO.read(testImage);
				
			} catch (IOException e) {
				System.out.println("no input file");
				
			}
			
			

			int widthTest = testImg.getWidth();
			int heightTest = testImg.getHeight();

			Color myWhite = new Color(255, 255, 255); 
			int rgb = myWhite.getRGB();

			BufferedImage theImage = new BufferedImage(widthTest, heightTest, BufferedImage.TYPE_INT_RGB);

			int[][] pixelTest = new int[widthTest][heightTest];
			Raster raster = testImg.getData();
			for (int i = 0; i < widthTest; i++) {
				for (int j = 0; j < heightTest; j++) {
					pixelTest[i][j] = raster.getSample(i, j, 0);

					Color c1 = new Color(testImg.getRGB(i, j));
					double probabilityOfSkin = (double)totalSkin/(double)totalPixel;
					double probabilityOfNonSkin = (double) totalNonSkin/(double)totalPixel;
				
					 pbOfSkin = (double) skinCount[c1.getRed()][c1.getGreen()][c1.getBlue()]
							/ (double) totalPixel;
					

					 pbOfNonSkin = (double) nonSkinCount[c1.getRed()][c1.getGreen()][c1.getBlue()]/(double) totalPixel;
							  
							  
							  
				
					if (pbOfNonSkin > 0.0) {
						
						if (pbOfSkin / pbOfNonSkin > 0.3)
							theImage.setRGB(i, j, rgb);
						else
							theImage.setRGB(i, j, 0);

					} else
						theImage.setRGB(i, j, 0);

					
				}
			}
			
			InputStream is = null;
		    OutputStream os = null;
		    try {
		        is = new FileInputStream(testImage);
		       
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		           
		        }
		    } finally {
		        is.close();
		        
		    }
			
			

			File outputfile = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/test4/Mask42/"+sub2+".bmp");
			
			
			try {
				ImageIO.write(theImage, "jpg", outputfile);
			} catch (IOException e1) {

			}
			
        }
        
		
	}
        
        */
		
		
		
		
      crossValidation();
      System.out.println("Completed");
      
	}
	
	
	
	
//train method 
	
	public void train() throws IOException{
		
		File folder = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/train5/Mask5");
        File[] listOfFiles = folder.listFiles();
        
		//int itr=0;
		for(File file : listOfFiles) {
			
			String fName=null;
			if (file.isFile()) {
                //System.out.println(file.getName());
				fName=file.getName();
				String[] arrOfStr = fName.split(".",2);
            	bmpP=fName;
            	String sub = fName.substring(0,4);
            	jpgP=sub + ".jpg";
            	
            	//System.out.println(bmpP);
            	//System.out.println(jpgP);
                
            }
			
			
			File image = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/train5/"+jpgP);
			File mask = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/train5/Mask5/"+bmpP);
			BufferedImage img = null;
			BufferedImage msk = null;

			try {
				img = ImageIO.read(image);
				msk = ImageIO.read(mask);
			} catch (IOException e) {
				
				break;
			}
			int width = img.getWidth();
			int height = img.getHeight();
			int[][] pixel = new int[width][height];
			Raster raster = img.getData();
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					totalPixel++;

					pixel[i][j] = raster.getSample(i, j, 0);

					Color c1 = new Color(img.getRGB(i, j));
					Color c2 = new Color(msk.getRGB(i, j));
					
					
					if (c2.getRed() >= 240 && c2.getGreen() >= 240 && c2.getBlue() >= 240){
						nonSkinCount[c1.getRed()][c1.getGreen()][c1.getBlue()]++;
						totalNonSkin++;
					}
						
					else{
						skinCount[c1.getRed()][c1.getGreen()][c1.getBlue()]++;
						totalSkin++;
					}
						// System.out.println(c2.getRed()+" "+c2.getGreen()+"
					// "+c2.getBlue());
				}
			}

			imageFileNumber++;

		}
		
		
		
		File in  = new File("trainData53.txt");
		FileWriter fileWriter = new FileWriter(in);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		
		for(int i = 0; i < 256; i++)
		{
			for(int j = 0; j < 256; j++)
			{
				for(int k = 0; k < 256; k++)
					printWriter.println(skinCount[i][j][k]);
			}
		}
		
		for(int i = 0; i < 256; i++)
		{
			for(int j = 0; j < 256; j++)
			{
				for(int k = 0; k < 256; k++)
					printWriter.println(nonSkinCount[i][j][k]);
			}
		}
		
		printWriter.println(totalPixel + " " + totalSkin + " " + totalNonSkin);
		
		System.out.println("trained with " +imageFileNumber+" image");
		printWriter.close();
		fileWriter.close();
		
	}
	
	
	
	
public static void crossValidation(){
		int imageFileNumber2=0;	
		long totalAccuracy=0;
		long precision=0,recall=0,fMeasure=0;
		File folder = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/Mask");
        File[] listOfFiles = folder.listFiles();
        String bmpP=null,jpgP=null;
        int tp=0,fp=0,fn=0,tn=0;
        
		//int itr=0;
		for(File file : listOfFiles) {
			
			String fName=null;
			if (file.isFile()) {
                //System.out.println(file.getName());
				fName=file.getName();
				String[] arrOfStr = fName.split(".",2);
            	bmpP=fName;
            	String sub = fName.substring(0,4);
            	jpgP=sub + ".bmp";
            	
            	//System.out.println(bmpP);
            	//System.out.println(jpgP);
                
            }
			
			
			File image = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/myMask/"+jpgP);
			File mask = new File("E:/SkinDetectionFinal/SkinDetection/ibtd/Mask/"+bmpP);
			BufferedImage img = null;
			BufferedImage msk = null;

			try {
				img = ImageIO.read(image);
				msk = ImageIO.read(mask);
			} catch (IOException e) {
				
				break;
			}
			int width = img.getWidth();
			int height = img.getHeight();
			int[][] pixel = new int[width][height];
			Raster raster = img.getData();
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					//totalPixel++;

					pixel[i][j] = raster.getSample(i, j, 0);

					Color c1 = new Color(img.getRGB(i, j));
					Color c2 = new Color(msk.getRGB(i, j));
					
					
					if (c2.getRed() >= 250 && c2.getGreen() >= 250 && c2.getBlue() >= 250 && c1.getRed() ==0 && c1.getGreen() ==0 && c1.getBlue() ==0){
						tn++;
					}
					else if (c2.getRed() <= 250 && c2.getGreen() <= 250 && c2.getBlue() <= 250 && c1.getRed() == 255 && c1.getGreen() == 255 && c1.getBlue() == 255){
						tp++;
					}
					else if (c2.getRed() >= 250 && c2.getGreen() >= 250 && c2.getBlue() >= 250 && c1.getRed() == 255 && c1.getGreen() == 255 && c1.getBlue() == 255){
						fp++;
					}
					else if (c2.getRed() <= 250 && c2.getGreen() <= 250 && c2.getBlue() <= 250 && c1.getRed() ==0 && c1.getGreen() ==0 && c1.getBlue() ==0){
						fn++;
					}
						
					
						// System.out.println(c2.getRed()+" "+c2.getGreen()+"
					// "+c2.getBlue());
				}
			}

			imageFileNumber2++;
			//long accuracy = (tp+tn)/(tp+fp+tn+fn);
			long a1=tp+tn;
			long a2=tp+fp+tn+fn;
			long acc=(a1*100)/a2;
			totalAccuracy += acc;
			
		}
			
		totalAccuracy = totalAccuracy/imageFileNumber2;
		
		System.out.println("tp : "+tp);
		System.out.println("tn : "+tn);
		System.out.println("fp : "+fp);
		System.out.println("fn : "+fn);
		
		System.out.println("Accuracy rate : "+totalAccuracy);
		
		precision=(tp*100)/(tp+fp);
		recall=(tp*100)/(tp+fn);
		fMeasure=(2*precision*recall)/(precision+recall);
		
		System.out.println("Precision : "+precision);
		System.out.println("Recall : "+recall);
		System.out.println("fMeasure : "+fMeasure);
		
	}
	

}
