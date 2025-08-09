



resource "aws_instance" "instances" {
  ami                    = data.aws_ami.ubuntu.id
  instance_type          = var.instance-type
  key_name               = var.sshkey
  subnet_id              = var.subnet_id
  vpc_security_group_ids = [aws_security_group.sg.id]
  associate_public_ip_address = true

#   user_data = <<EOF
# #!/bin/bash

# # Create unprivileged tomcat user
# useradd -m -d /opt/tomcat -U -s /bin/false tomcat

# # Update apt cache and install openjdk and fontconfig
# apt update
# apt install -y fontconfig openjdk-17-jre

# # Verify java version
# java -version

# cd /tmp

# # Download Tomcat 10.0.23
# wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.0.23/bin/apache-tomcat-10.0.23.tar.gz

# # Extract and set ownership
# mkdir -p /opt/tomcat
# tar xzvf apache-tomcat-10.0.23.tar.gz -C /opt/tomcat --strip-components=1
# chown -R tomcat:tomcat /opt/tomcat
# chmod -R u+x /opt/tomcat/bin

# # Create necessary directories and set permissions
# mkdir -p /opt/tomcat/temp
# chown tomcat:tomcat /opt/tomcat/temp

# # Create systemd service unit
# cat <<SERVICE_EOF > /etc/systemd/system/tomcat.service
# [Unit]
# Description=Tomcat
# After=network.target

# [Service]
# Type=forking
# User=tomcat
# Group=tomcat
# Environment="JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk-amd64"
# Environment="JAVA_OPTS=-Djava.security.egd=file:///dev/urandom"
# Environment="CATALINA_BASE=/opt/tomcat"
# Environment="CATALINA_HOME=/opt/tomcat"
# Environment="CATALINA_PID=/opt/tomcat/temp/tomcat.pid"
# Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"
# ExecStart=/opt/tomcat/bin/startup.sh
# ExecStop=/opt/tomcat/bin/shutdown.sh
# RestartSec=10
# Restart=always

# [Install]
# WantedBy=multi-user.target
# SERVICE_EOF

# systemctl daemon-reload
# systemctl start tomcat
# systemctl enable tomcat

# systemctl status tomcat --no-pager

# EOF

#   metadata_options {
#     http_endpoint = "enabled"
#     http_tokens   = "required"
#   }

#   connection {
#     type        = "ssh"
#     user        = "ubuntu"
#     private_key = file("${var.sshkey}.pem")
#     host        = self.public_ip
#   }

#   # provisioner "file" {
#   #   source      = "scripts/tomcat.sh"
#   #   destination = "/home/ubuntu/tomcat.sh"
#   # }

#   provisioner "file" {
#     source      = "Spring3HibernateApp.war"
#     destination = "/home/ubuntu/ROOT.war"
#   }

#   provisioner "remote-exec" {
#     inline = [
#       # "chmod +x /home/ubuntu/tomcat.sh",
#       # "sudo /home/ubuntu/tomcat.sh",
#       "sudo mv /home/ubuntu/ROOT.war /opt/tomcat/webapps/",
#       "sudo chown tomcat:tomcat /opt/tomcat/webapps/ROOT.war",
#       "sudo systemctl restart tomcat"
#     ]
#   }

  tags = {
    Name        = "tomcat-instance"
    Environment = "Dev"
  }

  depends_on = [aws_key_pair.key-pair, null_resource.fix_key_permissions]
}

resource "null_resource" "fix_key_permissions" {
  provisioner "local-exec" {
    command = "chmod 400 ${var.sshkey}.pem"
  }

  # triggers = {
  #   key_file = filemd5("${var.sshkey}.pem")
  # }
 depends_on = [aws_key_pair.key-pair]
}


 
# resource "aws_instance" "instances" {
#   ami           = data.aws_ami.ubuntu.id
#   instance_type = var.instance-type
#   key_name      = var.sshkey
#   metadata_options {
#     http_endpoint = "enabled"
#     http_tokens   = "required"
#   }
#   subnet_id                   =  var.subnet_id
#   vpc_security_group_ids      = [aws_security_group.sg.id] 
#   associate_public_ip_address = true
#   tags = {
#     "Name" : "bastion-host"
#   }
# }