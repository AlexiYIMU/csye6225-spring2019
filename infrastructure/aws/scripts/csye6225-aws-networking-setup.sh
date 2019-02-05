set -e
##create VPC
re=$( aws ec2 create-vpc --cidr-block "10.0.0.0/16" )

echo "Successfully creating vpc: "$re
id=$( echo $re | jq '.Vpc.VpcId' | sed 's/\"//g' ) 
if [ -z "$id" ];then
	exit 0
fi
echo $id
echo "Please input the VPC name prefix, the whole name will be PREFIX-csye6225-vpc"
read name
name=$name"-csye6225-"
vpc_name=$name"vpc"
## add VPC tag name
aws ec2 create-tags --resources $id --tags Key=Name,Value=$vpc_name
## create subnet1 
subnet_id1=$( aws ec2 create-subnet --availability-zone us-east-1a --cidr-block 10.0.1.0/24 --vpc-id $id | jq '.Subnet.SubnetId' | sed 's/\"//g' )
if [ -z "$subnet_id1" ];then
	exit 0
fi
echo "Successfully create subnet1 : " $subnet_id1 
## create subnet2
subnet_id2=$( aws ec2 create-subnet --availability-zone us-east-1b --cidr-block 10.0.2.0/24 --vpc-id $id | jq '.Subnet.SubnetId' | sed 's/\"//g' )
if [ -z "$subnet_id2" ];then
	exit 0
fi
echo "Successfully create subnet2 : " $subnet_id2 
##create subnet3
subnet_id3=$( aws ec2 create-subnet --availability-zone us-east-1c --cidr-block 10.0.3.0/24 --vpc-id $id | jq '.Subnet.SubnetId' | sed 's/\"//g' )
if [ -z "$subnet_id3" ];then
	exit 0
fi
echo "Successfully create subnet3 : " $subnet_id3
gateway_id=$( aws ec2 create-internet-gateway | jq '.InternetGateway.InternetGatewayId' | sed 's/\"//g')
if [ -z "$gateway_id" ];then
	exit 0
fi
echo "Successfully create Internet gateway : " $gateway_id 
aws ec2 attach-internet-gateway --internet-gateway-id $gateway_id --vpc-id $id
echo "Successfully attach gateway to vpc "
route_table_id=$( aws ec2 create-route-table --vpc-id $id | jq '.RouteTable.RouteTableId' | sed 's/\"//g')
if [ -z "$route_table_id" ];then
	exit 0
fi
echo "Successfully create route table :" $route_table_id
aws ec2 create-route --route-table-id $route_table_id --destination-cidr-block 0.0.0.0/0 --gateway-id $gateway_id
echo "Successfully create route "