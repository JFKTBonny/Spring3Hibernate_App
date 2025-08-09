

# # Get default VPC if no VPC ID is provided
# data "aws_vpc" "default" {
#   default = true
# }

# # Determine effective VPC ID: user-supplied or default VPC
# locals {
#   effective_vpc_id = var.vpc_id != "" ? var.vpc_id : data.aws_vpc.default.id
# }

# # Create a public subnet in the effective VPC
# resource "aws_subnet" "public_subnet" {
#   vpc_id            = local.effective_vpc_id
#   cidr_block        = var.subnet_cidr
#   availability_zone = var.availability_zone

#   # This makes instance get public IP automatically
#   map_public_ip_on_launch = true

#   tags = {
#     Name = "Public Subnet"
#     environment = "Dev"
#   }
# }

# Create an Internet Gateway attached to the effective VPC
# resource "aws_internet_gateway" "igw" {
#   vpc_id = local.effective_vpc_id

#   tags = {
#     Name = "VPC Internet Gateway"
#     environment = "Dev"
#   }
# }

# Create a route table tied to the VPC
# resource "aws_route_table" "public_rt" {
#   vpc_id = local.effective_vpc_id

#   route {
#     cidr_block = "0.0.0.0/0"
#     gateway_id = aws_internet_gateway.igw.id
#   }

#   tags = {
#     Name = "Public Route Table"
#     environment = "Dev"
#   }
# }

# # Associate the route table with the public subnet
# resource "aws_route_table_association" "public_subnet_assoc" {
#   subnet_id      = aws_subnet.public_subnet.id
#   route_table_id = aws_route_table.public_rt.id
# }
