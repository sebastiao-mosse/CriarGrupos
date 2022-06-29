package com.produsol.cadastro.grupo.principalAdapter;

import org.springframework.security.core.Authentication;

public class SecurityHolder {
	private static final ThreadLocal<Authentication> tl = new ThreadLocal<Authentication>();

	public static void set(Authentication auth) {
		tl.set(auth);
	}

	public static Authentication get() {
		return tl.get();
	}

	public static void unset() {
		tl.remove();
	}
}