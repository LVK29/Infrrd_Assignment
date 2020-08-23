package infrrd.lvk.fileCrud.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import infrrd.lvk.fileCrud.service.impl.BigFileServiceImpl;
import io.swagger.annotations.ApiOperation;

/*
 * Controller file for all the file CRUD opertions
 */
@RestController
public class BigFileController {

	private static Logger log = LoggerFactory.getLogger(BigFileController.class);
	@Autowired
	BigFileServiceImpl bigFileService;

	// save the file
	@PostMapping("/uploadFile")
	@ApiOperation(value = "Uploads/Creates the file in the server", notes = "Make sure that the fileName is not the same as that of the ones exisitng in the server")
	public Map<String, String> uploadFile(@RequestParam MultipartFile file, HttpServletResponse response)
			throws IOException {
		log.trace("Upload file API triggered");

		return Collections.singletonMap("response", bigFileService.saveFileToDrive(file));

	}

	// get big file as a byte[]
	@GetMapping("/file")
	@ApiOperation(value = "Downloads the file from the server", notes = "Make sure that the fileName exists in the server", response = HttpEntity.class)
	public HttpEntity<byte[]> getFile(@RequestParam String fileName) throws IOException {
		log.trace("Download file API triggered with file name " + fileName);
		byte[] documentBody = bigFileService.getFileByteArray(fileName);
		String mimeType = URLConnection.guessContentTypeFromName(fileName);
		HttpHeaders header = new HttpHeaders();
		if(mimeType!=null) {
		header.setContentType(MediaType.parseMediaTypes(mimeType).get(0));
		}
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName.replace(" ", "_"));
		header.setContentLength(documentBody.length);

		return new HttpEntity<byte[]>(documentBody, header);

	}

	// get all file names in the file directory
	@GetMapping("/fileNames")
	@ApiOperation(value = "Gets all file names in the server directory", response = List.class)
	public Map<String, List<String>> getFiles() throws IOException {
		log.trace("Get all file names in drive API triggered");
		return Collections.singletonMap("response", bigFileService.getAllFiles());

	}

	// delete big file
	@DeleteMapping("/file")
	@ApiOperation(value = "Removes the file from the server", notes = "Make sure that the fileName exists in the server", response = String.class)
	public Map<String, String> deleteFile(@RequestParam String fileName) throws FileNotFoundException {
		log.trace("Delete file API triggered with fileName " + fileName);
		return Collections.singletonMap("response", bigFileService.deleteFileFromDrive(fileName));
	}

	// create copy of the file
	@PostMapping("/cloneFile")
	@ApiOperation(value = "Copies an existing file from the server", notes = "Make sure that the fileName exists in the server, return will be similar filename ending with a '_' and a random thriple digit number", response = String.class)
	public Map<String, String> cloneExistingFile(@RequestParam String fileName) throws IOException {
		log.trace("Clone file API triggered with fileName " + fileName);
		return Collections.singletonMap("response", bigFileService.cloneFileToDrive(fileName));
	}

}
