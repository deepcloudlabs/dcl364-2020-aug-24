package com.example.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.function.Consumer;

import com.example.bbb.service.business.SimpleLotteryService;
import com.example.ccc.service.Quality;
import com.example.ccc.service.QualityLevel;
import com.example.ccc.service.RandomNumberService;

public class LotteryApp {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		var props = new Properties();
		props.load(new FileInputStream(new File("src","application.properties")));
		var level = QualityLevel.valueOf(props.getProperty("random.number.service.level"));
		var sls = new SimpleLotteryService();
		var loader = ServiceLoader.load(RandomNumberService.class);
		sls.setRandomNumberService(extractService(loader,level));
		Consumer<List<Integer>> println = System.err::println;
		sls.draw(10).forEach(println);
	}

	private static RandomNumberService extractService(ServiceLoader<RandomNumberService> loader,QualityLevel level) {
		RandomNumberService randomNumberService = null;
		for (var rns : loader) {
			var clazz = rns.getClass();
			if(clazz.isAnnotationPresent(Quality.class)) {
				var quality = clazz.getAnnotation(Quality.class);
				if (quality.value() == level) {
					randomNumberService = rns;
					break;
				}
			}
		}
		return randomNumberService;
	}

}
