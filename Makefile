compile:
	@echo "Start compiling..."
	@javac shapes/Shape.java shapes/ellipse.java shapes/receiver.java shapes/rectangle.java 
	@javac commands/order.java commands/deselectShapeCommand.java commands/placeShapeCommand.java commands/selectShapeCommand.java commands/dragShapeCommand.java
	@javac ui/board.java ui/commandInvoker.java 
	@javac strategies/PlaceRectangleStrategy.java strategies/PlaceEllipseStrategy.java
	@javac main.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class
	@cd commands && rm -rf *.class
	@cd strategies && rm -rf *.class
	@cd ui && rm -rf *.class
	@echo "Cleaning done!"


run: compile
	@echo "Running application..."
	@java main
	@echo "Closed application!" 
	@make clean

