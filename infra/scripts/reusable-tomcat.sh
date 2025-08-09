#!/bin/bash
set -euo pipefail

TOMCAT_VERSION=10.0.20
INSTALL_DIR=/opt/tomcat
JAVA_HOME_PATH=$(dirname $(dirname $(readlink -f $(which java))))

echo "Creating Tomcat user..."
if ! id tomcat &>/dev/null; then
    sudo useradd -m -d "$INSTALL_DIR" -U -s /bin/false tomcat
fi

echo "Updating packages..."
sudo apt update

echo "Installing Java..."
sudo apt install -y fontconfig openjdk-21-jre

echo "Downloading Tomcat ${TOMCAT_VERSION}..."
cd /tmp


echo "Installing Tomcat..."
sudo mkdir -p "$INSTALL_DIR"
sudo tar xzvf "apache-tomcat-${TOMCAT_VERSION}.tar.gz" -C "$INSTALL_DIR" --strip-components=1
sudo chown -R tomcat:tomcat "$INSTALL_DIR"
sudo chmod -R u+x "$INSTALL_DIR/bin"

echo "Creating systemd service..."
cat <<EOF | sudo tee /etc/systemd/system/tomcat.service
[Unit]
Description=Tomcat
After=network.target

[Service]
Type=forking
User=tomcat
Group=tomcat
Environment="JAVA_HOME=${JAVA_HOME_PATH}"
Environment="CATALINA_BASE=${INSTALL_DIR}"
Environment="CATALINA_HOME=${INSTALL_DIR}"
Environment="CATALINA_PID=${INSTALL_DIR}/temp/tomcat.pid"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"
ExecStart=${INSTALL_DIR}/bin/startup.sh
ExecStop=${INSTALL_DIR}/bin/shutdown.sh
SuccessExitStatus=143
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
EOF

echo "Starting Tomcat..."
sudo systemctl daemon-reload
sudo systemctl enable --now tomcat
sudo systemctl status tomcat --no-pager
