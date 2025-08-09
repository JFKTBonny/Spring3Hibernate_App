variable "ingress_ports" {
  description = "List of ingress rules to allow"
  type = list(object({
    from_port   = number
    to_port     = number
    protocol    = string
    cidr_blocks = list(string)
  }))
  default = [
    {
      from_port   = 22
      to_port     = 22
      protocol    = "tcp"
      cidr_blocks = ["0.0.0.0/0"]
    },
    {
      from_port   = 8080
      to_port     = 8080
      protocol    = "tcp"
      cidr_blocks = ["0.0.0.0/0"]
    }
  ]
}

variable "sshkey" {
  description = "ssh key"
  type        = string
  default     = "key"

}

variable "instance-type"{
  default = "t3.micro"
}

variable "instance-tags" {
  description = "instance tag"
  type        = map(any)
  default = {
    public  = "public-instance"
    private = "private-instance"
  }
}

variable "vpc_id" {
  description = "VPC ID to deploy resources to. Leave empty to use the default VPC."
  type        = string
  default     = "vpc-072356b5fdf5d2092"
}

variable "subnet_cidr" {
  description = "CIDR block for the public subnet to create."
  type        = string
  default     = "172.31.16.0/20"
}

variable "availability_zone" {
  description = "Availability Zone for the public subnet."
  type        = string
  default     = "us-east-1a"
}

variable "subnet_id" {
  default = "subnet-08b0c404343b42eb3"
  
}