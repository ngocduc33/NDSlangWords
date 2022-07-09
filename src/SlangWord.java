import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class SlangWord {
	
	private Map<String, List<String>> slangWords = new LinkedHashMap<>();

    private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String GRAVE_ACCENT_DELIMITER = "`";
	private static final String PIPE_DELIMITER = "|";
    
	/*
	 * Bill Pugh Singleton Implementation
	 */
	private SlangWord() {
		
	}
	
	private static class SingletonHelper {
		static final SlangWord INSTANCE = new SlangWord();
	}
	
	public static SlangWord getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public Map<String, List<String>> getData() {
		return slangWords;
	}
	
	public void writeFile(String fileName) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
            for (Entry<String, List<String>> entry : this.slangWords.entrySet()) {       
            	bw.append(entry.getKey()).append(GRAVE_ACCENT_DELIMITER); // add slang 
            	
            	// add definition
            	List<String> definitions = entry.getValue();
            	StringBuilder defnitionBuilder = new StringBuilder();
            	for(String definition : definitions) {
            		defnitionBuilder.append(definition).append(PIPE_DELIMITER);
				}
            	defnitionBuilder.deleteCharAt(defnitionBuilder.length() - 1);
            	defnitionBuilder.append(NEW_LINE_SEPARATOR);
            	bw.append(defnitionBuilder);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bw.flush();
            bw.close();
        }
    }

	public void readFile(String fileName) throws IOException {
    	Path path = null;
        BufferedReader br = null;
        long startTime = System.currentTimeMillis();
        try {
        	path = Paths.get(fileName);
            br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            slangWords.clear();
            String line;
            
            while ((line = br.readLine()) != null) {
            	String[] data = line.split(GRAVE_ACCENT_DELIMITER, -1);
            	slangWords.put(data[0], Arrays.stream(data[1].split("\\".concat(PIPE_DELIMITER), -1)).map(String::trim).collect(Collectors.toList()));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
	
	public Boolean isExists(String slang) {
		return slangWords.keySet().stream().anyMatch(s -> s.equals(slang));
	}
	
	public void add(String slang, String definition, AddType addType) throws IOException {
		if (addType.equals(AddType.NEW)) {
			slangWords.put(slang, Arrays.asList(definition));
		} else if (addType.equals(AddType.DUPLICATE)) {
			slangWords.get(slang).add(definition);
		} else if (addType.equals(AddType.OVERRIDE)) {
			slangWords.get(slang).clear();
			slangWords.get(slang).add(definition);
		}
		writeFile(FileNameUtils.DEFAULT);
	}
	
	public void update(String slang, String newDefinition, String oldDefinition) throws IOException {
		Optional<Entry<String, List<String>>> entry = slangWords.entrySet().stream().filter(e -> e.getKey().equals(slang)).findFirst();
		if(entry.isPresent()) {
			int index = entry.get().getValue().indexOf(oldDefinition);
			entry.get().getValue().set(index, newDefinition);
		}
		writeFile(FileNameUtils.DEFAULT);
	}
	
	public void delete(String slang, String definition) throws IOException {
		Optional<Entry<String, List<String>>> entry = slangWords.entrySet().stream().filter(e -> e.getKey().equals(slang)).findFirst();
		if(entry.isPresent()) {
			entry.get().getValue().remove(definition);
			if(entry.get().getValue().isEmpty()) {
				slangWords.remove(entry.get().getKey());
			}
		}
		writeFile(FileNameUtils.DEFAULT);
	}
	
	public Map<String, List<String>> findBySlang(String slang) {
		return slangWords.entrySet().stream()
				.filter(entry -> entry.getKey().contains(slang))
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
	}
	
	public Map<String, List<String>> findByDefinition(String definition) {
		return slangWords.entrySet().stream()
				.filter(entry -> entry.getValue().stream().allMatch(def -> def.contains(definition)))
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
	}
	
	public Map<String, String> random() {
		int randomSlangIndex = new Random().nextInt(slangWords.size());
		List<String> slangs = slangWords.keySet().stream().collect(Collectors.toList());
		String slang = slangs.get(randomSlangIndex);
		
		int randomDefinitionIndex = new Random().nextInt(slangWords.get(slang).size());
		List<String> definitions = slangWords.get(slang);
		String definition = definitions.get(randomDefinitionIndex);
		
		return Collections.singletonMap(slang, definition);
	}
	
	public Map<String, String> quizGame() {
		Map<String, String> result = new HashMap<>();
		for(int i = 0; i < 4; ++i) {
			Map<String, String> slangWord = random();
			if(!result.keySet().containsAll(slangWord.keySet())) {
				result.putAll(slangWord);
			}
		}
		return result;
	}
	
}
