package CowsAndBulls.Generators;

import java.util.HashMap;
import java.util.Map;

public class SecretGeneratorFactory {
	public static Map<String, ISecretGenerator> generators;
	
	static
	{
		 generators = new HashMap<String, ISecretGenerator>();
		 generators.put("dumb-number", new DumbNumberGenerator());
		 generators.put("smart-number", new DumbNumberGenerator());
	}
	
	public ISecretGenerator get(String name)
	{
		String lowerName = name.toLowerCase();
		if(generators.containsKey(lowerName))
			return generators.get(lowerName);
		else
			return null;
	}
	
	public String[] getAllNames()
	{
		return generators.keySet().toArray(new String[0]);
	}
}
