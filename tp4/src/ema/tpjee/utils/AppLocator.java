package ema.tpjee.utils;



public class AppLocator {

	private static AppLocator instance = new AppLocator();

	public static AppLocator getInstance() {
		return instance;
	}

	public <T> T getComponent(String name) {
			T service = null;
            try {
				service = (T)Class.forName(name).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return service;	
	}

}