package com.indracompany.weblogic.ejb.statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indracompany.weblogic.ejb.statistics.to.EJBPoolRuntimeInfo;
import com.indracompany.weblogic.ejb.statistics.to.EJBRuntime;
import com.indracompany.weblogic.ejb.statistics.to.EJBRuntimeItem;
import com.indracompany.weblogic.ejb.statistics.to.EnumLinkRel;
import com.indracompany.weblogic.ejb.statistics.to.Link;

public class ReadEJBStats {

	private static String server = "192.168.1.254";
	private static String port = "9801";

	private static String appName = "ndm-ear-DEV-EAM-V13-SNAPSHOT";
	private static String moduleName = "kernel-ejb-DEV-EAM-V13-SNAPSHOT.jar";

	private static String username = "weblogic";
	private static String password = "weblogic1";

//	public static String POOL_RUNTIME_FIELDS_FILTER = "/poolRuntime?fields=pooledBeansCurrentCount,destroyedTotalCount,accessTotalCount,beansInUseCurrentCount,missTotalCount,timeoutTotalCount,waiterCurrentCount";
	public static String POOL_RUNTIME_FIELDS_FILTER = "/poolRuntime";

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String urlRute) throws IOException, JSONException {
		URL url = new URL(urlRute);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));

		connection.setRequestProperty("Authorization", "Basic " + new String(encodedAuth));

//		int responseCode = connection.getResponseCode();
		InputStream is = connection.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException, JSONException {

		ReadEJBStats readEJBStats = new ReadEJBStats();

		while (true) {
			readEJBStats.readEJBStats();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void readEJBStats() throws JSONException, IOException {

		JSONObject json = readJsonFromUrl("http://" + server + ":" + port
				+ "/management/weblogic/latest/domainRuntime/serverRuntimes/grid-eam-apps-1/applicationRuntimes/"
				+ appName + "/componentRuntimes/" + moduleName + "/EJBRuntimes?fields=EJBName,type");

//		System.out.println(json.toString());
//		System.out.println(json.get("items"));

		ObjectMapper objectMapper = new ObjectMapper();
		EJBRuntime ejbRuntime = objectMapper.readValue(json.toString(), EJBRuntime.class);
		ejbRuntime.setModuleName(moduleName);

		for (EJBRuntimeItem item : ejbRuntime.getItems()) {

			if (item.getLinks() != null) {
				List<Link> linkCanonical = item.getLinks().stream()
						.filter(link -> EnumLinkRel.CANONICAL.equals(link.getRel())).collect(Collectors.toList());

				if (linkCanonical != null && !linkCanonical.isEmpty()) {
					JSONObject JSONPoolRuntimeInfo = readJsonFromUrl(
							linkCanonical.get(0).getHref() + POOL_RUNTIME_FIELDS_FILTER);
					item.setPoolRuntimeInfo(
							objectMapper.readValue(JSONPoolRuntimeInfo.toString(), EJBPoolRuntimeInfo.class));
				}
			}
		}
//		System.out.println(ejbRuntime.toString());
		exportToCsvFile(ejbRuntime);

	}

	private static void exportToCsvFile(EJBRuntime ejbRuntime) {
		if (ejbRuntime != null) {
			System.out.println(ejbRuntime.toCsv());
		}
	}

}
