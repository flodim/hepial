JAVA=java
JAVAC=javac
JFLEX=../tools/jflex-1.6.1/bin/jflex
JAVACUP=../tools/cup/java-cup-11b.jar
JASMIN=../tools/jasmin-2.4/jasmin.jar
CLASSPATH=$(JAVACUP):.

FILE_FLEX=./hepial.flex
FILE_CUP=./hepial.cup
FILE_JAVA_NAME=Hepial
FILE_TEST_PRG_NAME=test
FILE_OUTPUT_JASMIN=HepialProgram.j
FILE_OUTPUT_CLASS=HepialProgram

TEST_CLASS=test

FILE=../hepial_files/hepial4_corrected

run: $(FILE_OUTPUT_CLASS).class
	$(JAVA) $(FILE_OUTPUT_CLASS)

$(FILE_OUTPUT_CLASS).class : $(FILE_OUTPUT_JASMIN)
	$(JAVA) -jar $(JASMIN) $(FILE_OUTPUT_JASMIN)

$(FILE_OUTPUT_JASMIN): $(FILE) sym.class parser.class $(FILE_JAVA_NAME).class $(FILE_TEST_PRG_NAME).class
	$(JAVA) -classpath $(CLASSPATH) $(TEST_CLASS) $(FILE)

$(FILE_JAVA_NAME).java : $(FILE_FLEX)
	$(JFLEX) $(FILE_FLEX)

sym.java parser.java : $(FILE_CUP)
	$(JAVA) -jar $(JAVACUP) $(FILE_CUP)

%.class : %.java
	$(JAVAC) -classpath $(CLASSPATH) $<

clean :
	find -type f -name '*.class' -delete
	rm -rf *~ parser.java sym.java $(FILE_JAVA_NAME).java *.j
