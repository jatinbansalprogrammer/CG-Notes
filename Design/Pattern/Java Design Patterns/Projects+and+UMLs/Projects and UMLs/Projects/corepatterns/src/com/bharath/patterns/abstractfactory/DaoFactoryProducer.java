package com.bharath.patterns.abstractfactory;

public class DaoFactoryProducer {

	public static DaoAbstractFactory produce(String factoryType) {
		DaoAbstractFactory daf = null;

		if (factoryType.equals("xml")) {
			daf = new XMLDaoFactory();
		} else if (factoryType.equals("db")) {
			daf = new DBDaoFactory();
		}

		return daf;

	}
}
