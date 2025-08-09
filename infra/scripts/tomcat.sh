#!/bin/bash

# create unprivileged tomcat user(for security purpose)
sudo useradd -m -d /opt/tomcat -U -s /bin/false tomcat

# update the package manager cache
sudo apt update

# install the JDK

sudo apt install -y fontconfig openjdk-17-jre
java -version

# navigate to the /tmp 
cd /tmp


# Download the archive using wget
wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.44/bin/apache-tomcat-10.1.44.tar.gz


#  extract the archive you downloaded
sudo tar xzvf apache-tomcat-10*tar.gz -C /opt/tomcat --strip-components=1

# grant tomcat ownership over the extracted installation
sudo chown -R tomcat:tomcat /opt/tomcat/
sudo chmod -R u+x /opt/tomcat/bin

# fetch where java is located
sudo update-java-alternatives -l

# create a systemd service file for tomcat: to run tomcat as a service
cat <<EOF | sudo tee /etc/systemd/system/tomcat.service
[Unit]
Description=Tomcat
After=network.target

[Service]
Type=forking

User=tomcat
Group=tomcat

Environment="JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk-amd64"
Environment="JAVA_OPTS=-Djava.security.egd=file:///dev/urandom"
Environment="CATALINA_BASE=/opt/tomcat"
Environment="CATALINA_HOME=/opt/tomcat"
Environment="CATALINA_PID=/opt/tomcat/temp/tomcat.pid"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"

ExecStart=/opt/tomcat/bin/startup.sh
ExecStop=/opt/tomcat/bin/shutdown.sh

RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
EOF

# Reload the systemd daemon so that it becomes aware of the new service
sudo systemctl daemon-reload

# start the Tomcat service
sudo systemctl start tomcat

# confirm that it started successfully
sudo systemctl status tomcat

# enable Tomcat starting up with the system
sudo systemctl enable tomcat

