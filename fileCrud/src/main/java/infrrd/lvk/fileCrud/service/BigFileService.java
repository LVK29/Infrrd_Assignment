package infrrd.lvk.fileCrud.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import infrrd.lvk.fileCrud.exception.FileCRUDException;

public interface BigFileService {

	public String saveFileToDrive(MultipartFile file) throws IOException, FileCRUDException;

	public String deleteFileFromDrive(String fileName);

	public void writeFileStream(byte[] fileByteArray, FileOutputStream outputStream) throws IOException;

	public byte[] getFileByteArray(String fileName);

	public List<String> getAllFiles();

	public String cloneFileToDrive(String fileName) throws FileNotFoundException, IOException;
}
