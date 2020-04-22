complile:
	@javac App.java Board.java Invoker.java
	@javac shapes/Shape.java shapes/Rectangle.java shapes/Ellipse.java
	@javac commands/Order.java commands/SelectShapeCommand.java commands/DragShapeCommand.java commands/PlaceShapeCommand.java

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class
	@cd commands && rm -rf *.class
	@echo "Cleaning done!"


run: complile
	@java App
	@make clean