compile:
	@echo "Start compiling..."
	@javac shapes/Shape.java shapes/BaseShape.java shapes/Group.java shapes/ResizableBorder.java shapes/Rectangle.java shapes/Ellipse.java shapes/Location.java shapes/Handle.java
	@javac commands/Order.java commands/PlaceShapeCommand.java commands/SelectShapeCommand.java commands/DragShapeCommand.java commands/ResizeShapeCommand.java
	@javac UI/Board.java UI/Invoker.java UI/Layers.java
	@javac strategies/Strategy.java strategies/PlaceRectangleStrategy.java
	@javac io/Parser.java
	@javac visitor/moveVisitor.java visitor/Visitor.java visitor/resizeVisitor.java
	@javac main.java UI/mouse.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class	
	@cd UI && rm -rf *.class
	@cd commands && rm -rf *.class
	@cd strategies && rm -rf *.class
	@cd io && rm -rf *.class
	@cd visitor && rm -rf *.class
	@echo "Cleaning done!"

run: compile
	@echo "Running application..."
	@java main
	@echo "Closed application!" 
	@make clean
	@clear

