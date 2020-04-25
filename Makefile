compile:
	@echo "Start compiling..."
	@javac shapes/ResizableBorder.java shapes/Rectangle.java
	@javac commands/Order.java commands/PlaceShapeCommand.java
	@javac main.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class
	@cd commands && rm -rf *.class
	@echo "Cleaning done!"


run: compile
	@echo "Running application..."
	@java main
	@echo "Closed application!" 
	@make clean
	@clear

