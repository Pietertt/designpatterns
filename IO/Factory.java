package IO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Factory {
      // A map which contains a keyvaluepair 
      static Map<String, Operation> operationMap = new HashMap<>();
      static {
            // Adds all available shape to the map
            operationMap.put("rectangle", new GetRectangle());
            operationMap.put("ellipse", new GetEllipse());
            operationMap.put("triangle", new GetTriangle());
            operationMap.put("group", new GetGroup());
        }
     
        // Returns a Operation object based on the input
        // Note that to error checking is done, is it quite vulnerable to errors
        public static Operation getOperation(String operator) {
            return operationMap.get(operator);
        }
}