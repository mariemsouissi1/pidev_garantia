package tn.esprit.infini2.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.omg.CORBA.portable.InputStream;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName,MultipartFile multipartFile) throws IOException 
	{
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) 
		{
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = (InputStream) multipartFile.getInputStream())
		{
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe)
			{
			 throw new IOException("Could not save image file: " + fileName, ioe);
			}
	}
}
