compile:
	@echo "Start compiling..."
	@javac shapes/Shape.java shapes/BaseShape.java shapes/Group.java shapes/Rectangle.java shapes/Ellipse.java shapes/Location.java
	@javac commands/Order.java commands/PlaceShapeCommand.java commands/SelectShapeCommand.java commands/DragShapeCommand.java commands/ResizeShapeCommand.java commands/SaveShapeCommand.java
	@javac UI/Board.java UI/Invoker.java UI/Layers.java UI/Item.java
	@javac io/Parser.java
	@javac App.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class	
	@cd UI && rm -rf *.class
	@cd commands && rm -rf *.class
	@cd io && rm -rf *.class
	@echo "Cleaning done!"

run: compile
	@echo "Running application..."
	@java App
	@echo "Closed application!" 
	@make clean
	@clear

