complile:
	javac App.java Board.java Invoker.java
	javac shapes/Shape.java shapes/Rectangle.java shapes/Ellipse.java
	javac commands/Order.java commands/SelectShapeCommand.java commands/DragShapeCommand.java commands/PlaceShapeCommand.java

run: complile
	java App