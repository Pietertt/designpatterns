compile:
	@echo "Start compiling..."
	@javac shapes/Shape.java shapes/BaseShape.java shapes/Group.java shapes/Location.java shapes/ShapeDecorator.java shapes/TextShapeDecorator.java
	@javac commands/Order.java commands/PlaceShapeCommand.java commands/SelectShapeCommand.java commands/DragShapeCommand.java commands/ResizeShapeCommand.java commands/SaveShapeCommand.java
	@javac UI/Board.java UI/Invoker.java UI/Layers.java UI/Item.java
	@javac strategies/Strategy.java strategies/PlaceRectangleStrategy.java strategies/PlaceGroupStrategy.java
	@javac IO/fileIO.java IO/Factory.java IO/GetRectangle.java IO/Operation.java IO/GetEllipse.java IO/GetTriangle.java
	@javac visitor/DragVisitor.java visitor/Visitor.java visitor/resizeVisitor.java visitor/ExportVisitor.java
	@javac App/App.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class	
	@cd UI && rm -rf *.class
	@cd commands && rm -rf *.class
	@cd strategies && rm -rf *.class
	@cd IO && rm -rf *.class
	@cd visitor && rm -rf *.class
	@cd App && rm -rf *.class
	@echo "Cleaning done!"

run: compile
	@echo "Running application..."
	@java App/App
	@echo "Closed application!" 
	@make clean
	@clear

