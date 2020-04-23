compile:
	@echo "Start compiling..."
	@javac shapes/shape.java shapes/ellipse.java shapes/receiver.java shapes/rectangle.java 
	@javac commands/order.java commands/deselectShapeCommand.java commands/placeShapeCommand.java commands/selectShapeCommand.java commands/dragShapeCommand.java
	@javac ui/board.java ui/commandInvoker.java 
	@javac main.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class
	@cd commands && rm -rf *.class
	@cd ui && rm -rf *.class
	@echo "Cleaning done!"


run: compile
	@java main
	@make clean

