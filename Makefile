compile:
	@echo "Start compiling..."
	@javac shapes/ResizableBorder.java shapes/Rectangle.java
	@javac ResizableComponentEx.java
	@echo "Compiling done!"

clean:
	@echo "Cleaning files..."
	@rm -rf *.class
	@cd shapes && rm -rf *.class
	@echo "Cleaning done!"


run: compile
	@echo "Running application..."
	@java ResizableComponentEx
	@echo "Closed application!" 
	@make clean
	@clear

