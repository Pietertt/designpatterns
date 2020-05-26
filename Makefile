compile:
	@echo "Start compiling..."
	@javac io/parser.java
	@javac shapes/ellipse.java shapes/handle.java shapes/rectangle.java shapes/shape.java
	@javac ui/board.java ui/ui.java
	@javac main.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class	
	@cd ui && rm -rf *.class
	@cd io && rm -rf *.class
	@echo "Cleaning done!"

run: compile
	@echo "Running application..."
	@java main
	@echo "Closed application!" 
	@make clean
	@clear