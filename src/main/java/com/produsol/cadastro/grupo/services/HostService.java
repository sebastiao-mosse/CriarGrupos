package com.produsol.cadastro.grupo.services;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostService {

	public String getIpAddress() {
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip.toString();
	}

	public String getHostName() {
		InetAddress ip = null;
		String hostname = "";
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostname;
	}
}
