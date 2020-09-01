package com.example.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class FileDownloadService {

	@Schedule(second = "*/5", hour = "*", minute = "*")
	public void download() {
		var filesToBeDownloaded = List.of("photo1.jpg", "photo2.jpg");
		Client client = ClientBuilder.newClient();
		try {
			for (String file : filesToBeDownloaded) {
				WebTarget target = client.target("http://localhost:8080/fileservice/api/v1/files/download/{file}")
						.resolveTemplate("file", file);
				Response response = target.request(MediaType.APPLICATION_OCTET_STREAM).get();
				try (InputStream in = response.readEntity(InputStream.class)) {
					var bytes = in.readAllBytes();
					Path path = Paths.get("c:/tmp", file);
					FileOutputStream fos = new FileOutputStream(path.toFile());
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					baos.write(bytes);
					baos.writeTo(fos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
