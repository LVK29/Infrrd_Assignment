package infrrd.lvk.fileCrud.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import infrrd.lvk.fileCrud.exception.FileCRUDException;
import infrrd.lvk.fileCrud.service.BigFileService;

@Service
public class BigFileServiceImpl implements BigFileService {

	public static String FILE_DIR = "./files/";

	public String saveFileToDrive(MultipartFile file) throws IOException, FileCRUDException {
		String fileName = file.getOriginalFilename();
		File fileObject = new File(FILE_DIR + fileName);
		if (!fileObject.exists()) {
			try {
				FileOutputStream outputStream = new FileOutputStream(FILE_DIR + fileName);
				writeFileStream(file.getInputStream().readAllBytes(), outputStream);
				return "File saved with file name : " + fileName;
			} catch (Exception e) {
				throw new FileCRUDException("Saving file failed");
			}
		} else {
			throw new FileCRUDException("Saving file failed: File with " + fileName + " already exists.");
		}
	}

	public String deleteFileFromDrive(String fileName) {
		File fileObject = new File(FILE_DIR + fileName);
		if (fileObject.delete()) {
			return "Deleted the file: " + fileObject.getName();
		} else {
			throw new FileCRUDException("File " + fileName + " does not exist");

		}
	}

	public void writeFileStream(byte[] fileByteArray, FileOutputStream outputStream) throws IOException {
		outputStream.write(fileByteArray);
		outputStream.close();
	}

	public byte[] getFileByteArray(String fileName) {
		Path path = Paths.get(FILE_DIR + fileName);

		try {
			// BufferedReader reader = Files.newBufferedReader(path);
			byte[] fileContent = Files.readAllBytes(path);
			InputStream is = new BufferedInputStream(new ByteArrayInputStream(fileContent));
			return is.readAllBytes();
		} catch (IOException e) {
			throw new FileCRUDException("File " + fileName + " does not exist");

		}
	}

	public List<String> getAllFiles() {
		List<String> results = new ArrayList<String>();
		File[] files = new File(FILE_DIR).listFiles();
		// If this pathname does not denote a directory, then listFiles() returns null.

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		return results;
	}

	public String cloneFileToDrive(String fileName) throws FileNotFoundException, IOException {
		File fileObject = new File(FILE_DIR + fileName);
		if (fileObject.exists()) {
			String fileNameWithOutExtension = fileName.substring(0, fileName.lastIndexOf("."));
			String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			Random rand = new Random();
			fileNameWithOutExtension = fileNameWithOutExtension.concat("_" + String.valueOf(rand.nextInt(1000)));
			FileOutputStream outputStream = new FileOutputStream("./files/" + fileNameWithOutExtension + fileExtension);
			writeFileStream(Files.readAllBytes(fileObject.toPath()), outputStream);
			return "File cloned with new file name : " + fileNameWithOutExtension + fileExtension;
		} else {
			throw new FileCRUDException("File " + fileName + " doestnt exist, try creating one first");

		}
	}
}
