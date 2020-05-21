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
            operationMap.put("group", new GetGroup());
        }
     
        public static Operation getOperation(String operator) {
              System.out.println(operator);
            return operationMap.get(operator);
        }
}