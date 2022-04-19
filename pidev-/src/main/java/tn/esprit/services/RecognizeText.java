package tn.esprit.services;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;

//import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc.cvtColor;
//import org.opencv.highgui.HighGui.imwrite;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class RecognizeText {

		public String getImgText (String imageLocation) {
//			//1) Source Image
//			Mat img = new Mat();
//			img = Imgcodecs.imread(imageLocation); 
//			Imgcodecs.imwrite("preprocess/trueImage.png", img);
//			//2) Gray Scale
//			Mat imgGray = new Mat();
//			Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);
//			Imgcodecs.imwrite("preprocess/Gray.png", imgGray);
//			//3) Gaussian Blur
//			Mat imgGaussianBlur = new Mat(); 
//			Imgproc.GaussianBlur(imgGray,imgGaussianBlur,new Size(3, 3),0);
//			Imgcodecs.imwrite("preprocess/gaussian_blur.png", imgGaussianBlur);  
//			//4) Adaptive Threshold
//			Mat imgAdaptiveThreshold = new Mat();
//			Imgproc.adaptiveThreshold(imgGaussianBlur, imgAdaptiveThreshold, 255, CV_ADAPTIVE_THRESH_MEAN_C ,CV_THRESH_BINARY, 99, 4);
//			Imgcodecs.imwrite("preprocess/adaptive_threshold.png", imgAdaptiveThreshold);

			ITesseract instance = new Tesseract();
			try {
			File file = new File(imageLocation);
			String data=instance.doOCR(file);
				return data;
		}
		catch (TesseractException e ) {
			e.printStackTrace();
			return "Error While Reading From Image";
		}
		}
//	File image = new File("src/main/resources/images/multiLanguageText.png");
//	Tesseract tesseract = new Tesseract();
//	tesseract.setDatapath("src/main/resources/tessdata");
//	tesseract.setLanguage("eng");
//	tesseract.setPageSegMode(1);
//	tesseract.setOcrEngineMode(1);
//	String result = tesseract.doOCR(image);
	
//	private static final String MORPH_RET = null;
//	static String SRC_PATH = "";
//	static Tesseract tesseract = new Tesseract();
//	static {
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		tesseract.setDatapath("C:\\Users\\souis\\tesseract-main\\tessdata");
//		
//	}
//	
//	String extractString (Mat inputMat) {
//		String input = "C:/opencv/GeeksforGeeks.jpg";
//		  
//        // To Read the image
//        Mat source = Imgcodecs.imread(input);
//  
//        // Creating the empty destination matrix
//        Mat destination = new Mat();
//  
//        // Converting the image to gray scale and 
//        // saving it in the dst matrix
//        Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY);
//  
//        // Writing the image
//        Imgcodecs.imwrite("C:/opencv/GeeksforGeeks.jpg", destination);
//        System.out.println("The image is successfully to Grayscale");
//        
//        
//        
//        
//		Mat gray=new Mat();
//		
//		String result="";
//		//cvtColor(inputMat, gray, COLOR_BayerBG2GRAY);
//		//imwrite(SRC_PATH + "gray.png", gray);
//		//Mat element= getStructuringElement(MORPH_RET, new Size(2,2), new Point(1,1));
//		//dilate(gray, gray, element);
//		//erode(gray, gray, element);
//		
//		try {
//			result= tesseract.doOCR(SRC_PATH+ ".png");
//		}
//		catch (TesseractException e ) {
//			e.printStackTrace();
//			
//		}
//		return result;}

}
