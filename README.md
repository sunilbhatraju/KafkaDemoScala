#KafkaDemoScala
<h4>Setup Kafka on windows</h4>
Let us understand how to setup Kafka and also validate it.
<ul>
 	<li>Go to https://kafka.apache.org/downloads</li>
 	<li>Choose the version built with Scala 2.11 (2.2.0)</li>
 	<li>Untar the tarball and unzip the file at any location in your system</li>
 	<li>Setup the Kafka path in your environment variables</li>
</ul>
<h4>Starting Kafka</h4>
Kafka requires
<ul>
 	<li>Zookeeper</li>
 	<li>Kafka Server</li>
</ul>
<h4>Setup Zookeeper on windows</h4>
<ul>
 	<li>Go to https://archive.apache.org/dist/zookeeper/zookeeper-3.4.9/</li>
 	<li>Click on the tar.gz file to download</li>
 	<li>Untar the tarball and unzip the file at any location in your system</li>
 	<li>Setup the Zookeeper path in your environment variables</li>
 	<li>Start the Kafka-server and zookeeper-server in the system using the command prompt</li>
 	<li>Go to Kafka-&gt;bin-&gt;windows location in command prompt</li>
 	<li>Open two command prompts and start both the servers separately. If they launched successfully then you were good to go</li>
</ul>
<h4>//start zookeeper</h4>
<pre>zookeeper-server-start.bat /Path/kafka_2.11-2.0.0/config/zookeeper.properties</pre>
<h4>//start Kafka</h4>
<pre>kafka-server-start.bat /Path/kafka_2.11-2.0.0/config/server.properties</pre>
<h4>Setup IntelliJ in your local machine.</h4>
<ul>
 	<li>Open a new project using scala -&gt; sbt with sbt version - 0.13.17 and scala version - 2.11.12</li>
 	<li>Wait for to complete sbt dump its project structure from sbt</li>
 	<li>After the dump right click on src-&gt;main-&gt;scala go to new Scala class</li>
 	<li>Change scala class to Object and give name KafkaProducerExample</li>
 	<li>The same way create one more scala object with the name KafkaConsumerExample</li>
 	<li>Copy and paste the code in both the programs provided in the repository</li>
 	<li>Note:- Remove this.getClass().getClassLoader() in the line val conf = ConfigFactory.load(this.getClass().getClassLoader()) in both the programs</li>
 	<li>Create application.properties file in src-&gt;main-&gt;resources folder and paste below properties</li>
</ul>
<pre>dev.zookeeper = localhost:2181
dev.bootstrap.server = localhost:9092
dev.topic = test

prod.zookeeper = nn01.itversity.com:2181,nn02.itversity.com:2181,rm01.itversity.com:2181
prod.bootstrap.server = nn01.itversity.com:6667,nn02.itversity.com:6667,rm01.itversity.com:6667
prod.topic = test</pre>
<h4>Running the project</h4>
<ul>
 	<li>Right click on the producer program and click on Run</li>
 	<li>For the first time, it will fail because we haven't provided arguments</li>
 	<li>Click on Run -&gt; Edit Configuration on the top left add programs arguments as dev in both the programs</li>
 	<li>First, run the producer program and then run the consumer program</li>
 	<li>Launch another command prompt from Kafka -&gt; bin -&gt; windows and paste the command to start the console producer to push messages into the Kafka consumer</li>
 	<li>
<pre>kafka-console-producer.bat --broker-list localhost:9092 --topic test</pre>
</li>
 	<li>Check whether the messages received into IntelliJ or not</li>
</ul>
<h4>
Build the jar</h4>
<ul>
 	<li>Using sbt package from the project directory we can build the jar file and run it on the cluster.</li>
 	<li>Copy the jar file from local to labs using WinSCP
Run the jar file in itversity labs using below command</li>
</ul>
<pre>scala -cp "/external_jars/*:./kafka.jar" KafkaProducerExample prod
scala -cp "/external_jars/*:./kafka1.jar" KafkaConsumerExample prod</pre>
<ul>
 	<li>Open another console in itversity labs and use below command to push messages</li>
</ul>
<pre>kafka-console-producer.sh \
--broker-list nn01.itversity.com:6667,nn02.itversity.com:6667,rm01.itversity.com:6667 \
--topic test</pre>
Check in the first console whether the messages were received or not.
