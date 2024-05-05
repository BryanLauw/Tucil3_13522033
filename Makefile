JC = javac
J = java

# Define the source directory
SOURCE := src
# Define the binary directory
OUT := bin

build:
	@$(JC) -d $(OUT) \
	$(SOURCE)/Node.java \
	$(SOURCE)/PrioQueueNode.java \
	$(SOURCE)/Path.java \
	$(SOURCE)/Dictionary.java \
	$(SOURCE)/Process.java \
	$(SOURCE)/Main.java

run:
	@$(J) -cp $(OUT) Main