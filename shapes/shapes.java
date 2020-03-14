package shapes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class shapes {
      public ArrayList<rectangle> rects = new ArrayList<rectangle>();
      public ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      public static Object deepCopy(Object object){
            try {
                  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                  ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
                  outputStrm.writeObject(object);
                  ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                  ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
                  return objInputStream.readObject();
            } catch(Exception e){
                  e.printStackTrace();
                  return null;
            }
      }
}