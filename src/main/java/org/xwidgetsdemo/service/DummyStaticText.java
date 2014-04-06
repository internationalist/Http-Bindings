package org.xwidgetsdemo.service;

import java.util.HashMap;

public class DummyStaticText {
	
	private HashMap<String, String> store = new HashMap<String, String>();
	
	DummyStaticText() {
		store.put("1", Page1);
		store.put("2", Page2);
		store.put("3", Page3);
		store.put("4", Page4);
		store.put("5", Page5);
		store.put("6", Page6);
		store.put("7", Page7);		
	}
	
	
	public static String Page1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ac ligula consectetur, dictum lorem a, laoreet leo. Nunc in porttitor erat. Donec non mi nec nisl luctus tincidunt. Suspendisse ultrices euismod diam, a elementum nisl. Donec dignissim, justo eget ultrices ultrices, neque massa iaculis tortor, luctus tempus nisl eros at libero. Pellentesque mi magna, facilisis vitae pharetra at, congue eget tortor. Morbi magna nibh, bibendum sed semper sed, dignissim nec lectus. Phasellus scelerisque, tellus ut egestas hendrerit, neque tortor vehicula est, ac pretium urna nulla ac lorem. Suspendisse aliquam dignissim semper. Curabitur turpis sem, euismod quis pellentesque a, porttitor non dolor.";
	
	public static String Page2 = "Phasellus elementum, nulla sed mattis sagittis, lacus metus fermentum augue, vel feugiat elit dolor eget sapien. Donec eu lectus vitae purus tempor placerat. Ut vel neque massa. Nullam ultricies dolor id ipsum condimentum, ac blandit neque molestie. Donec mattis euismod turpis, at condimentum turpis laoreet in. Suspendisse ac turpis eget massa sodales aliquam. Cras dictum eleifend tristique. Mauris quis purus hendrerit, vulputate est nec, ultrices nisi. ";
	
	public static String Page3 = "Vestibulum at elit ultricies, tempor lectus eget, sollicitudin ligula. Fusce sit amet suscipit ante, et consectetur urna. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec fermentum, ante sed euismod consectetur, erat augue varius sem, in facilisis neque neque vitae arcu. Morbi eu urna mattis, rhoncus dolor viverra, scelerisque ligula. Nam congue erat eget mi euismod eleifend. Sed vel libero tempus, porttitor nisi vel, hendrerit magna. Ut venenatis venenatis erat, et tincidunt erat viverra at. Nullam id adipiscing nulla. Cras nisl dolor, volutpat nec nibh sit amet, accumsan tincidunt lacus.";
	
	public static String Page4 = "Vivamus felis sapien, vulputate elementum dapibus eu, varius eu sapien. In faucibus massa felis, nec ornare augue congue id. Nulla euismod, nulla a viverra elementum, nulla sapien convallis elit, vitae facilisis mauris arcu eu leo. Nunc id urna et neque tincidunt ullamcorper. Quisque quam dui, pharetra a lacinia et, varius at ligula. Aliquam erat volutpat. Cras quis diam non tortor commodo egestas ut ac velit. Integer pulvinar nunc sed euismod cursus.";
	
	public static String Page5 = "In hac habitasse platea dictumst. Cras non tempor lacus, a malesuada lacus. Mauris facilisis augue eget orci laoreet aliquam. Nam molestie ante id eros sollicitudin, id blandit sapien vulputate. Donec ac ante vel nibh scelerisque semper. Integer dolor lectus, facilisis ac pharetra interdum, condimentum vel justo. Cras tempus tortor quis ligula lobortis molestie. Ut pharetra elit risus, et laoreet nisl laoreet nec. Proin sit amet est congue, venenatis lorem ut, suscipit nibh. Integer ultrices vitae justo eu consequat. Proin vitae magna gravida, molestie magna ac, viverra sapien";
	
	public static String Page6 = "In scelerisque eros sit amet ipsum varius, eu suscipit arcu euismod. Praesent lacinia, ipsum aliquam accumsan volutpat, diam mi varius diam, sit amet dapibus erat nulla ut magna. Sed lacinia placerat ante, sit amet rutrum ligula scelerisque et. Etiam id tellus in magna venenatis malesuada. Cras eu scelerisque metus. Mauris eleifend urna nisi, et vestibulum eros vehicula in. Proin aliquet nisl diam, vel dictum libero iaculis in.";
	
	public static String Page7 = "Integer cursus molestie elit at laoreet. Nulla euismod semper gravida. Donec mollis sodales dolor, eu bibendum sem pharetra non. Morbi nec condimentum urna. Vestibulum vel dapibus ante. Proin venenatis ante ante, et mattis risus fermentum et. Nunc congue, odio congue dictum pharetra, nisl arcu suscipit odio, ac interdum erat lorem a lorem. Curabitur consequat velit urna, non tempus augue dignissim non. Quisque blandit purus mi, id molestie massa gravida id. Cras non diam eu ipsum tincidunt hendrerit at ac nunc. Sed nec magna nec sem vehicula adipiscing ac in enim. Aliquam varius mattis metus sed malesuada. Pellentesque eu porta leo. Mauris in commodo leo. Aliquam erat volutpat.";

	public HashMap<String, String> getStore() {
		return store;
	}
	
}
