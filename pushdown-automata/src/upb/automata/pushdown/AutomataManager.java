package upb.automata.pushdown;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class AutomataManager {
	public static final String SAVE_FOLDER = "automata";
	
	Gson gson;
	
	public AutomataManager() {
		new File(AutomataManager.SAVE_FOLDER).mkdir();
		this.gson = new Gson();
	}
	
	public boolean saveAutomata(Automata a, String filename) {
		filename = this.formatFilename(filename);
		try {
			Writer writer = new FileWriter(AutomataManager.SAVE_FOLDER.concat("/".concat(filename)));
			this.gson.toJson(a, writer);
			writer.flush();
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Automata loadAutomata(String filename) {
		filename = this.formatFilename(filename);
		Automata a = null;
		try {
			a = gson.fromJson(new FileReader(AutomataManager.SAVE_FOLDER.concat("/".concat(filename))), Automata.class);
		} catch (JsonSyntaxException | JsonIOException e) {
			System.out.println("The JSON automata '" + filename +"' isn't correctly defined");
		} catch(FileNotFoundException e) {
			System.out.println("The JSON automata '" + filename +"' can't be found");
		}
		return a;
	}
	
	private String formatFilename(String filename) {
		if (!filename.endsWith(".json")) {
			filename = filename.concat(".json");
		}
		return filename;
	}
}
