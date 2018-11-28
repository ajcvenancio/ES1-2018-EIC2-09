package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;

public class SerializedCredentials {
	
	XStream xs;
	
	public SerializedCredentials() {
		xs = new XStream();
	}
	
	public void writeToXML(Credentials credentials) throws FileNotFoundException {
		xs.alias("credentials", Credentials.class);
		String xml = xs.toXML(credentials);
		File config = new File("config.xml");
		xs.toXML(credentials, new PrintWriter(config));
	}

	public static void main(String[] args) throws FileNotFoundException {
		XStream xStream = new XStream();
		Credentials credentials = new Credentials("abcd@hotmail.com", "abcdefgh");
		// Serializar
		xStream.alias("credentials", Credentials.class);
		String xml = xStream.toXML(credentials);
		File config = new File("config.xml");
		xStream.toXML(credentials, new PrintWriter(config));
		// Deserializar_
		Credentials newCredentials = (Credentials) xStream.fromXML(config);

		System.out.println("Email : " + newCredentials.getEmail());
		System.out.println("Password : " + newCredentials.getPassword());

	}
}
