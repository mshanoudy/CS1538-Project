import java.util.Random;

public class DistributionGenerator
{
    Random random;

    public DistributionGenerator()
    {
        this.random = new Random();
    }

    public double[] generateGaussianArray(int arraySize)
    {
        double array[] = new double[arraySize];
        for (int x = 0; x < arraySize; x++)
            array[x] = random.nextGaussian();
        return array;
    }

    public double[] generateNormalArray(int arraySize)
    {
        double array[] = new double[arraySize];
        for (int x = 0; x < arraySize; x++)
            array[x] = random.nextDouble();
        return array;
    }

    public double generateNextUniform()
    {
        return random.nextDouble();
    }
}
