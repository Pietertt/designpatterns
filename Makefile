compile:
	@echo "Start compiling..."
	@javac shapes/shape.java shapes/ellipse.java shapes/handle.java shapes/receiver.java shapes/rectangle.java shapes/snapshot.java
	@javac commands/order.java commands/deselectShapeCommand.java commands/placeShapeCommand.java commands/selectShapeCommand.java commands/dragShapeCommand.java
	@javac ui/board.java ui/commandInvoker.java ui/ui.java
	@javac main.java
	@echo "Compiling done"



run: compile
	@java main

