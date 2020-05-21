package IO;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Factory {
      static Map<String, Operation> operationMap = new HashMap<>();
      static {
            operationMap.put("rectangle", new GetRectangle());
            operationMap.put("ellipse", new GetEllipse());
            operationMap.put("triangle", new GetTriangle());
        }
     
        public static Operation getOperation(String operator) {
            return operationMap.get(operator);
        }
}