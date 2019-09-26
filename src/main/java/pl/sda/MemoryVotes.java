package pl.sda;

public class MemoryVotes implements Votes{

    private int dogs = 0;
    private int cats = 0;

    @Override
    public int getDogs() {
        return dogs;
    }

    @Override
    public void increaseDogs() {
        dogs++;
    }

    @Override
    public int getCats() {
        return cats;
    }

    @Override
    public void increaseCats() {
        cats++;
    }
}
