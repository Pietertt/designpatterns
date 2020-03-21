javac \
main.java
cd commands
      javac order.java selectShapeCommand.java
cd ../
cd io
      javac parser.java
cd ../
cd mouse
      javac mouse.java
cd ../
cd shapes
      javac shapes.java \
            shape.java \
            ellipse.java \
            rectangle.java \
            handle.java 
cd ../ 
javac ui/board.java ui/ui.java

java main