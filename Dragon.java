public class Dragon extends Type{
    private double[] weak={1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
                           0.5, 0.5, 0.5, 0.5, 1.0, 2.0, 2.0, 1.0, 2.0};
    @Override
    public double[] getWeak(){
        return weak;
    }
}
