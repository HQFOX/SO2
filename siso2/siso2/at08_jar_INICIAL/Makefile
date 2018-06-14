# ######################
all: client server

client:	classes/so2/EchoClient.class

classes/so2/EchoClient.class: src/so2/EchoClient.java
	javac -d classes -classpath classes src/so2/EchoClient.java

server:	classes/so2/EchoServer.class

classes/so2/EchoServer.class: src/so2/EchoServer.java
	javac -d classes -classpath classes src/so2/EchoServer.java


runclient: client
	java -classpath classes so2.EchoClient localhost 9001 exemplo-1-2-ok


runserver: server
	java -classpath classes so2.EchoServer 9001


# eliminar temporarios e .class
clean:
	find . -name \*~ | xargs rm -f
	find . -name \*.class | xargs rm -f
	rm -rf classes/so2