package br.com.g4flex.client;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

public class FlexExportClient {

	private final static String BASE_URL = "http://localhost:3333";

	private static HttpURLConnection init(String url) throws IOException {
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setDoInput(true);
		urlConnection.setDoOutput(true);
		urlConnection.connect();
		return urlConnection;
	}

	public static void pdf(HttpServletResponse response, String json, String filename) throws IOException {
		HttpURLConnection myUrlConnection = init(BASE_URL + "/exports/pdf");

		sendData(myUrlConnection, json);
		receiveData(myUrlConnection, response, "application/pdf; charset=utf-8", filename);
	}

	public static void excel(HttpServletResponse response, String json, String filename) throws IOException {
		HttpURLConnection myUrlConnection = init(BASE_URL + "/exports/excel");

		sendData(myUrlConnection, json);
		receiveData(myUrlConnection, response, "application/xlsx; charset=utf-8", filename);
	}

	private static void receiveData(HttpURLConnection con, HttpServletResponse response, String contentType,
			String filename) throws IOException {
		InputStream in = new BufferedInputStream(con.getInputStream());
		byte dataBuffer[] = new byte[40000000];
		int bytesRead;
		while ((bytesRead = in.read(dataBuffer, 0, 40000000)) != -1) {
			response.getOutputStream().write(dataBuffer, 0, bytesRead);
		}
		response.setContentLength(bytesRead);
		response.setContentType(contentType);
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"" + filename + "\"");
		response.setHeader(headerKey, headerValue);
		closeQuietly(in);
		
	}

	private static void sendData(HttpURLConnection con, String data) throws IOException {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(con.getOutputStream());
			dos.writeBytes(data);
			dos.flush();
			dos.close();
		} catch (IOException exception) {
			throw exception;
		} finally {
			closeQuietly(dos);
		}
	}

	private static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException ex) {
		}
	}
}
