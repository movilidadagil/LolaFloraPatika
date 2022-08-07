package training;

import framework.ConfigReader;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class Training {
    public static  void main(String[] args){
        Map<String, Object> data = ConfigReader.initialize_Properties();
    }
}
