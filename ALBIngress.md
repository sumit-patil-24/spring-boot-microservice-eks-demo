# Setting up the AWS ALB as an Ingress Resource for EKS

1. **Download the IAM Policy**: The IAM policy is required to allow the ALB controller to interact with ALB and other AWS resources. Download it using the following command:
   ```bash
   curl -O https://raw.githubusercontent.com/kubernetes-sigs/aws-load-balancer-controller/v2.5.4/docs/install/iam_policy.json

2. **Create the IAM Policy**: This command creates an IAM policy named AWSLoadBalancerControllerIAMPolicy
   ```bash
   aws iam create-policy \
    --policy-name AWSLoadBalancerControllerIAMPolicy \
    --policy-document file://iam_policy.json

3. **Create an IAM Role and Service Account**: Create an IAM role and a Kubernetes service account to associate with the ALB controller. Run the following command, replacing <your-aws-account-id> with your actual AWS Account ID
   ```bash
   eksctl create iamserviceaccount \
    --cluster=demo-cluster \
    --namespace=test \
    --name=aws-load-balancer-controller \
    --role-name AmazonEKSLoadBalancerControllerRole \
    --attach-policy-arn=arn:aws:iam::<your-aws-account-id>:policy/AWSLoadBalancerControllerIAMPolicy \
    --approve \
    --override-existing-serviceaccounts
  This command creates an IAM role named AmazonEKSLoadBalancerControllerRole, attaches the IAM policy to it, and associates it with a Kubernetes service account named aws-load-balancer-controller in the test namespace.

4. **Add the Helm Repository**: Add the EKS chart repository to Helm to access the AWS Load Balancer Controller chart
   ```bash
   helm repo add eks https://aws.github.io/eks-charts
   helm repo update

5. **Install the AWS Load Balancer Controller**: This will use the service account created earlier.
   ```bash
   helm install aws-load-balancer-controller eks/aws-load-balancer-controller -n kube-system \
    --set clusterName=demo-cluster \
    --set serviceAccount.create=false \
    --set serviceAccount.name=aws-load-balancer-controller \
    --set region=ap-south-1 \
    --set vpcId=vpc-0b154a80b7424ef11

After completing these steps, your ALB controller will be configured and able to manage load balancers within your EKS cluster.

