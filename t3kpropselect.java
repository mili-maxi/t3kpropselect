/*
 * Class to select just some from properties, by using a criteria
 * 
 * Made, since I repeatably required this wrapper with this functionality in numberous projects.
 * 
 *  @version 1.0
 *	@author Alen Milincevic
 *
 *	@section LICENSE
 *
 *	Copyright 2013 Alen Milincevic
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */

import java.util.Enumeration;
import java.util.Properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class t3kpropselect {

	final static int BEGIN = 0;
	final static int END = 1;
	final static int CONTAINS = 2;
	final static int REGEX = 3;
	
	static Properties props;
	
	public t3kpropselect(Properties props) {
		this.props = props;
	}

	public static Properties getAllKeyValuesByBegin(String key) {

		Properties allKeys = new Properties();
		Enumeration e = props.propertyNames();

		    while (e.hasMoreElements()) {
		      String ckey = (String) e.nextElement();
		      String value = props.getProperty(ckey);
		      if (ckey.startsWith(key) == true)
		    	  allKeys.setProperty(ckey, value);
		    }
		    
		return allKeys;
	}
	
	public static Properties getAllKeyValuesByEnd(String key) {

		Properties allKeys = new Properties();
		Enumeration e = props.propertyNames();

		    while (e.hasMoreElements()) {
		      String ckey = (String) e.nextElement();
		      String value = props.getProperty(ckey);
		      if (ckey.endsWith(key) == true)
		    	  allKeys.setProperty(ckey, value);
		    }
		    
		return allKeys;
	}

	public static Properties getAllKeyValuesContaining(String key) {

		Properties allKeys = new Properties();
		Enumeration e = props.propertyNames();

		    while (e.hasMoreElements()) {
		      String ckey = (String) e.nextElement();
		      String value = props.getProperty(ckey);
		      if (ckey.contains(key) == true)
		    	  allKeys.setProperty(ckey, value);
		    }
		    
		return allKeys;
	}
	
	public static Properties getAllKeyValuesWithRegex(String regexkey) {

		Properties allKeys = new Properties();
		Enumeration e = props.propertyNames();

		    while (e.hasMoreElements()) {
		      String ckey = (String) e.nextElement();
		      String value = props.getProperty(ckey);
		      if (ckey.matches(regexkey) == true)
		    	  allKeys.setProperty(ckey, value);
		    }
		    
		return allKeys;
	}

	public static Properties getPropertiesByKey(String key, int way) {
		
		if (way == BEGIN) {
			return getAllKeyValuesByBegin(key);
		} else if (way == END) {
			return getAllKeyValuesByEnd(key);
		} else if (way == CONTAINS) {
			return getAllKeyValuesContaining(key);
		} else if (way == REGEX) {
			return getAllKeyValuesWithRegex(key);
		} else {
			return null;
		}
	}
	
	/**
	 * @param args
	 * 
	 * Test function
	 */
	public static void main(String[] args) {
		
		Properties test = new Properties();
		test.setProperty("test1", "value1");
		test.setProperty("nest1", "value1");
		t3kpropselect ps = new t3kpropselect(test);
		System.out.println(ps.getPropertiesByKey("test1", ps.REGEX).size());

	}

}
