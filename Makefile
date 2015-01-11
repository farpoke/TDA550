
JC = javac
JVM = java

JCFLAGS = -g -sourcepath ./src
JVMFLAGS = -cp ./src

.SUFFIXES: .java .class

SOURCES := $(shell find . -name *.java)
CLASSES := $(SOURCES:.java=.class)

%.class: %.java
	$(JC) $(JCFLAGS) $<

default: classes

classes: $(CLASSES)

run: classes
	$(JVM) $(JVMFLAGS) orig2011.v7.Main

clean:
	$(RM) -rfv *.class

