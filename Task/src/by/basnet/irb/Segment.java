package by.basnet.irb;

public class Segment {
    private int len;
    private int num = 1;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Segment(int len, int num) {
        this.len = len;
        this.num = num;
    }

    public Segment(int len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return String.format("%d;%d",len,num);
    }
}
