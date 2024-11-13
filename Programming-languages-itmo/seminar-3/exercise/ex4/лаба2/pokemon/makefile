# Пути к компиляторам и JAR-утилитам
JAVAC = javac
JAR = jar

# Пути к исходным файлам и директориям
SRC_DIR = src
OUT_DIR = out
JAR_NAME = lab2.jar
MANIFEST = MANIFEST.MF

# Пути к исходным файлам Java
JAVA_SOURCE_MOVES = $(wildcard $(SRC_DIR)/moves/*.java)
JAVA_SOURCE_POKEMONS = $(wildcard $(SRC_DIR)/pokemons/*.java)
JAVA_SOURCE_MAIN = $(SRC_DIR)/Laba2.java

# Зависимости для каждой команды
$(OUT_DIR)/moves: $(JAVA_SOURCE_MOVES)
	$(JAVAC) -d $(OUT_DIR) -cp Pokemon.jar $(JAVA_SOURCE_MOVES)

$(OUT_DIR)/pokemons: $(JAVA_SOURCE_POKEMONS)
	$(JAVAC) -cp $(OUT_DIR):Pokemon.jar -d $(OUT_DIR) $(JAVA_SOURCE_POKEMONS)

$(OUT_DIR)/Laba2.class: $(JAVA_SOURCE_MAIN)
	$(JAVAC) -cp $(OUT_DIR):Pokemon.jar -d $(OUT_DIR) $<

$(JAR_NAME): $(OUT_DIR)/moves $(OUT_DIR)/pokemons $(OUT_DIR)/Laba2.class
	$(JAR) cvfm $(JAR_NAME) $(MANIFEST) -C $(OUT_DIR) .

# Задачи
all: $(JAR_NAME)

clean:
	rm -rf $(OUT_DIR)/*
	rm -f $(JAR_NAME)

.PHONY: all clean



#javac -d out -cp Pokemon.jar src/moves/*.java
#javac -cp out:Pokemon.jar -d out src/pokemons/*.java
#javac -cp out:Pokemon.jar -d / src/Laba2.java
#jar cvfm lab2.jar MANIFEST.MF out/* 

