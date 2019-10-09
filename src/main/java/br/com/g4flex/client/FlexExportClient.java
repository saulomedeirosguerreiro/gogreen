package br.com.g4flex.client;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class FlexExportClient {

	private final static String BASE_URL = "http://10.8.60.70:3330";
//	private final static String BASE_URL = "http://localhost:3333";

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

	public static void pdf(HttpServletResponse response, String json, String filename) throws Exception {
		HttpURLConnection myUrlConnection = init(BASE_URL + "/exports/pdf");

		sendData(myUrlConnection, json);
		receiveData(myUrlConnection, response, "application/pdf; charset=utf-8", filename);
	}

	public static void excel(HttpServletResponse response, String json, String filename) throws Exception {
		HttpURLConnection myUrlConnection = init(BASE_URL + "/exports/excel");

		sendData(myUrlConnection, json);
		receiveData(myUrlConnection, response, "application/vnd.ms-excel; charset=utf-8", filename);
	}

	private static void receiveData(HttpURLConnection con, HttpServletResponse response, String contentType,
			String filename) throws Exception {
		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			InputStream is = con.getInputStream();
			int byteRead;
			byte[] data = new byte[con.getContentLength() < 1 ? 2048 : con.getContentLength()];

			while ((byteRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, byteRead);
			}

			response.addHeader("Content-Disposition", "attachment; filename=" + filename);

			byte[] bytes = buffer.toByteArray();
			response.setContentType(contentType);
			response.setContentLength(bytes.length);
			buffer.close();

			servletOutputStream = response.getOutputStream();
			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception exception) {
			throw exception;
		} finally {
			closeQuietly(servletOutputStream);
		}

	}

	private static void sendData(HttpURLConnection con, String data) throws Exception {
		DataOutputStream dos = null;
		OutputStreamWriter osw = null;
		try {
			dos = new DataOutputStream(con.getOutputStream());
			osw = new OutputStreamWriter(dos, StandardCharsets.UTF_8);
			osw.write(data);
			osw.flush();
			osw.close();
		} catch (Exception exception) {
			throw exception;
		} finally {
			closeQuietly(osw);
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
