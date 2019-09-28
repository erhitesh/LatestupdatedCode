package com.restAssuredData;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderSet {

	@DataProvider(name = "DataForSeasons")
	public static Object[][] getDriverCountCorresspondToYear() {
		return new Object[][] { { 2000, 23 }, { 2005, 27 }, { 2008, 22 }, { 2010, 27 }, { 2012, 25 }, { 2015, 22 },
				{ 2018, 20 }, { 2019, 20 } };
	}

	@DataProvider(name = "DataForStatus")
	public static Object[][] getStatusIsAndStatus() {
		return new Object[][] { { "Finished", 1 }, { "Disqualified", 2 }, { "Accident", 3 }, { "Collision", 4 },
				{ "Engine", 5 }, { "Gearbox", 6 }, { "+2 Laps", 12 }, { "Suspension", 22 }, { "Overheating", 25 } };
	}

	@DataProvider(name = "DataProviderWithMethod")
	public static Object[][] getDataWithMethod(Method m) {
		Object obj[][] = null;

		if (m.getName().equalsIgnoreCase("TestA")) {
			obj = new Object[][] { { 2000, 23 }, { 2005, 27 }, { 2008, 22 }, { 2010, 27 }, { 2012, 25 }, { 2015, 22 },
					{ 2018, 20 }, { 2019, 20 } };
		} else if (m.getName().equalsIgnoreCase("TestB")) {
			obj = new Object[][] { { "Finished", 1 }, { "Disqualified", 2 }, { "Accident", 3 }, { "Collision", 4 },
					{ "Engine", 5 }, { "Gearbox", 6 }, { "+2 Laps", 12 }, { "Suspension", 22 }, { "Overheating", 25 } };
		}

		return obj;
	}

	@DataProvider(name = "DataProviderWithContext")
	public static Object[][] getDataFromContext(ITestContext con) {
		Object obj[][] = null;

		for (String mm : con.getIncludedGroups()) {
			if (mm.equalsIgnoreCase("GroupA")) {
				obj = new Object[][] { { "Finished", 1 }, { "Disqualified", 2 }, { "Accident", 3 }, { "Collision", 4 },
						{ "Engine", 5 }, { "Gearbox", 6 }, { "+2 Laps", 12 }, { "Suspension", 22 },
						{ "Overheating", 25 } };
				break;
			} else if (mm.equalsIgnoreCase("GroupB")) {
				obj = new Object[][] { { 2000, 23 }, { 2005, 27 }, { 2008, 22 }, { 2010, 27 }, { 2012, 25 },
						{ 2015, 22 }, { 2018, 20 }, { 2019, 20 } };
				break;
			}
		}
		
		return obj;
	}
}
