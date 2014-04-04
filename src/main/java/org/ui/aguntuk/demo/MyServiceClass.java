package org.ui.aguntuk.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aguntuk.xwidget.annotations.RequestMeta;
import org.aguntuk.xwidget.annotations.ServiceInterfaceMeta;
import org.aguntuk.xwidget.util.OutputFormat;

@ServiceInterfaceMeta
public class MyServiceClass {
	private Map<String, String[]> carModelsByMaker = null;
	private String[] carmakers =  { "Honda", "Toyota", "Mazda", "Nissan", "Subaru", "Suzuki", "Hyundai"};
	private String[][] carModels = {{"Civic", "Accord", "Fit", "CrossTour", "Crv"},
			{"Corolla", "Camry", "Yaris", "Avalon", "Rav4"}, {"Mazda3", "Mazda6", "Mazda2", "CX-7", "CX-5"},
			{"Sentra", "Altima", "Versa", "Juke", "Murano"}, {"BRZ", "Impreza", "Legacy", "Crosstrek", "Forester"},
			{"SX4", "Kizashi", "SX4 Sedan", "Grand Vitara", "Ritz"}, {"Elantra", "Sonata", "Accent", "Santa Fe", "Tucson"}};
	
	
	public MyServiceClass() {
		carModelsByMaker = new HashMap<String, String[]>();
		int j = 0;
		for(String carMaker:carmakers) {
			carModelsByMaker.put(carMaker, carModels[j]);
			++j;
		}
	}
	@RequestMeta(templateFile="carform.ftl",requestName="main")
	public String[] getAutomobileMakes() {
		return carmakers;
	}
	
	@RequestMeta(templateFile="carmodels.ftl",requestName="modelbymaker", outputType=OutputFormat.HTML, requestKeyName={"make"})
	public String[] getAutomobileModelsByMake(String maker) {
		return carModelsByMaker.get(maker);
	}
	
	@RequestMeta(templateFile="cardetail.ftl",requestName="model", outputType=OutputFormat.HTML, requestKeyName={"model"})
	public String getAutomobileModel(String model) {
		return "images/" + model + ".jpg";
	}
	
	@RequestMeta(templateFile="abc",requestName="TESTOBJECTARGUMENT",
			outputType=OutputFormat.JSON,
			requestObject="org.ui.aguntuk.demo.Person"
	)
	public Person testWithPerson(Person person) {
		System.out.println(person);
		return person;
	}
	@RequestMeta(requestName="getProducts",
			outputType=OutputFormat.JSP,
			jsp="test.jsp")
	public List<Product> getProducts() {
		ArrayList<Product> list = new ArrayList<Product>();
		String[][] data = {{"Playstation 4", "Kicks ass", "399"},{"Xbox one", "Sucks ass.", "499"},{"WiiU", "Good for kids", "249.99"}};
		for(int i = 0; i < 3; i++) {
			list.add(new Product(data[i][0], data[i][1], Float.parseFloat(data[i][2])));
		}
		return list;
	}
	
	
	
	
/*	@RequestMeta(templateFile="abc",requestName="ANOTHERSERVICEMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(Integer  number) {
		System.out.println("Called anotherServiceMethod with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="BYTEMETHODMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(byte  number) {
		System.out.println("Called byte method with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="SHORTMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(short  number) {
		System.out.println("Called short method with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="BOOLEANMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(boolean  number) {
		System.out.println("Called boolean method with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="BYTEARRAYMETHODMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(byte[]  number) {
		System.out.println("Called byte array with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="SHORTARRAYMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(short[]  number) {
		System.out.println("Called short array with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="BOOLEANARRAYMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(boolean[]  number) {
		System.out.println("Called boolean array with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="CHARARRAYMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(char[]  number) {
		System.out.println("Called char array with " + new String(number));
	}
	
	@RequestMeta(templateFile="abc",requestName="LONGARRAYMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(long[]  number) {
		System.out.println("Called long array with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="FLOATARRAYMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(float[]  number) {
		System.out.println("Called float array with " + number);
	}
	
	@RequestMeta(templateFile="abc",requestName="DOUBLEARRAYMETHOD", outputType=OutputFormat.JSON,requestKeyName={"number"})
	public void anotherServiceMethod(double[]  number) {
		System.out.println("Called double array with " + number);
	}
	
	
	@RequestMeta(templateFile="abc",requestName="TESTARRAYREQUEST", outputType=OutputFormat.JSON,requestKeyName={"multiTest", "name"})
	public void testArrayRequest(int[]  treeNames, String firstName) {
		System.out.println("Called int array with array length " + treeNames.length + " and name: " + firstName);
	}
	@RequestMeta(templateFile="abc",requestName="TESTOBJECTARGUMENT",
			outputType=OutputFormat.JSON,
			requestObject="org.ui.aguntuk.demo.Person"
	)
	public void testWithPerson(Person person) {
		System.out.println(person);
	}*/
	
	

}
