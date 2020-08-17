package mx.ipn.upiicsa.poo.pizarron.json;

import java.util.List;
import com.google.gson.Gson;

import mx.ipn.upiicsa.poo.pizarron.model.Shape;

public class Json { 
	
	public void generateJson(List<Shape> shapeList) {
		Gson gson = new Gson();
		String json = gson.toJson(shapeList);
		System.out.println(json);
	}
}
