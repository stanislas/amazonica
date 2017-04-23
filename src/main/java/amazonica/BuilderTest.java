package amazonica;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Vpc;

public final class BuilderTest {

    public static void main(String[] args) {
        AmazonEC2 ec2;
        if (args[0].equals("builder")) {
            ec2 = AmazonEC2ClientBuilder.defaultClient();
        } else {
            ec2 = new AmazonEC2Client();
        }
        for (Vpc vpc: ec2.describeVpcs().getVpcs()) {
            DescribeSubnetsResult subnetsResult = ec2.describeSubnets(new DescribeSubnetsRequest()
                .withFilters(new Filter()
                    .withName("vpc-id")
                    .withValues(vpc.getVpcId())));
            System.out.println(subnetsResult.getSubnets().get(0).getAvailabilityZone());
        }
    }

}
