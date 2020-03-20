javac main.java
cd io
      javac parser.java
cd ../
cd shapes 
      javac ellipse.java \
            handle.java \
            rectangle.java \
            shape.java \
            shapes.java
cd ../
javac ui/board.java ui/ui.java

java main