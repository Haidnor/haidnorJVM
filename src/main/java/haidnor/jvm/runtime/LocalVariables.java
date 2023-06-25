package haidnor.jvm.runtime;

/**
 * 局部变量表
 */
public class LocalVariables {

    private final Slot[] slotArr;

    public LocalVariables(int size) {
        this.slotArr = new Slot[size];
    }

    public LocalVariables(Slot[] slots) {
        this.slotArr = slots;
    }

    public Slot[] getSlots() {
        return this.slotArr;
    }

    public void setInt(int index, int val) {
        slotArr[index] = new Slot(val);
    }

    public int getInt(int index) {
        return slotArr[index].num;
    }

    public void setFloat(int index, float val) {
        int tmp = Float.floatToIntBits(val);
        slotArr[index] = new Slot(tmp);
    }

    public float getFloat(int index) {
        int num = slotArr[index].num;
        return Float.intBitsToFloat(num);
    }

    public long getLong(int index) {
        int high = slotArr[index].num;
        int low = slotArr[index + 1].num;

        long l1 = (high & 0x000000ffffffffL) << 32;
        long l2 = low & 0x00000000ffffffffL;
        return l1 | l2;
    }

    public void setLong(int index, long val) {
        int high = (int) (val >> 32); //高32位
        int low = (int) (val & 0x000000ffffffffL); //低32位

        slotArr[index] = new Slot(high);
        slotArr[index + 1] = new Slot(low);
    }

    public void setDouble(int index, double val) {
        long tmp = Double.doubleToLongBits(val);

        int high = (int) (tmp >> 32); //高32位
        int low = (int) (tmp & 0x000000ffffffffL); //低32位

        slotArr[index] = new Slot(high);
        slotArr[index + 1] = new Slot(low);
    }

    public double getDouble(int index) {
        long tmp = this.getLong(index);
        return Double.longBitsToDouble(tmp);
    }

    public void setRef(int index, Instance ref) {
        slotArr[index] = new Slot(ref);
    }

    public Instance getRef(int index) {
        return slotArr[index].ref;
    }

    public String debug(String space) {
        StringBuilder sb = new StringBuilder();
        sb.append(space).append(String.format("LocalVars: %d", this.slotArr.length)).append("\n");
        for (int i = 0; i < this.slotArr.length; i++) {
            Slot slot = this.slotArr[i];
            if (slot == null) {
                sb.append(space).append(String.format("%d | null | null", i)).append("\n");
                continue;
            }
            if (slot.ref != null) {
                sb.append(space).append(String.format("%d | ref       | %s", i, slot.ref)).append("\n");
                continue;
            }
            sb.append(space).append(String.format("%d | primitive | %s", i, slot.num)).append("\n");
        }
        return sb.append("\n").toString();
    }

    public void set(int i, Slot val) {
        this.slotArr[i] = val;
    }
}
